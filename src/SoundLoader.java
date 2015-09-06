import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundLoader {
	
	private HashMap<Sounds, AudioStream> soundMap;
	
	public SoundLoader() throws IOException {
		soundMap = new HashMap(4);
		
		String directory = (System.getProperty("user.dir") + File.separator+ "src" + File.separator + "Sounds");
		File folder = new File(directory);
		File[] allFiles = folder.listFiles();
		for(File file : allFiles) {
			System.out.println(file);
			System.out.println(file.getName());
			int removeExtension = file.getName().indexOf(".");
			String soundName = file.getName().substring(0, removeExtension);
			InputStream inputStream = new FileInputStream(file);
			AudioStream audioStream = new AudioStream(inputStream);
			
			System.out.println(Sounds.valueOf(soundName));
			soundMap.put(Sounds.valueOf(soundName), audioStream);
			
			
		}
	}
	
	public void playSound(Sounds sound) {
		AudioPlayer.player.start(soundMap.get(sound));
	}
	
	public static void main(String[] args) throws IOException {
		SoundLoader test = new SoundLoader();
		test.playSound(Sounds.Match);
	}
}
