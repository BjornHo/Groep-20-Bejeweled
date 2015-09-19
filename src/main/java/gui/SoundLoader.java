package gui;

import java.io.File;

import java.io.IOException;

import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLoader {

	private HashMap<Sounds, Clip> soundMap;
	private HashMap<Sounds, AudioInputStream> streamMap;

	/**
	 * Method that loads all sound files from a certain directory.
	 * 
	 * @throws IOException
	 *     If input/output is incorrect.
	 * @throws LineUnavailableException
	 *     If an audio line cannot be opened because it is unavailable.
	 * @throws UnsupportedAudioFileException
	 *     If type of audio file is not supported.
	 */
	public SoundLoader() throws IOException, LineUnavailableException,
		UnsupportedAudioFileException {

		String directory = (System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "main" + File.separator + "java"
				+ File.separator + "Sounds");
		File folder = new File(directory);
		File[] allFiles = folder.listFiles();

		soundMap = new HashMap<Sounds, Clip>(allFiles.length);
		streamMap = new HashMap<Sounds, AudioInputStream>(allFiles.length);

		for (File file : allFiles) {
			int removeExtension = file.getName().indexOf(".");
			String soundName = file.getName().substring(0, removeExtension);
			
			AudioInputStream audioinputStream = AudioSystem.getAudioInputStream(file);
			AudioFormat audioFormat = audioinputStream.getFormat();
			
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			
			soundMap.put(Sounds.valueOf(soundName), audioClip);
			streamMap.put(Sounds.valueOf(soundName), audioinputStream);
		}
	}

	/**
	 * Method that plays a certain Sound file.
	 * 
	 * @param sound
	 *     Sound to be played.
	 * @throws IOException
	 *     If input/output is incorrect.
	 * @throws LineUnavailableException
	 *     If an audio line cannot be opened because it is unavailable.
	 */
	public void playSound(Sounds sound) throws IOException, LineUnavailableException {
		Clip clip = soundMap.get(sound);
		AudioInputStream stream = streamMap.get(sound);
		
		if (clip.isOpen()) {
		clip.setFramePosition(0);
		clip.start();
		} else {
			clip.open(stream);
			clip.start();
		}	
	}
}
