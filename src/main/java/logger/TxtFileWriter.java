package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that outputs log messages to a (text) file.
 * 
 * @author Group 20
 */
public class TxtFileWriter implements Writer {
	
	private BufferedWriter writer;
	
	public TxtFileWriter(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String text) {
		try {
			writer.write(text);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
