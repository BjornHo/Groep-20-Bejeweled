package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Daniel
 * TXTFileWriter 
 */
public class TXTFileWriter implements Writer {
	
	private BufferedWriter w;
	
	public TXTFileWriter(File f) {
		try {
			w = new BufferedWriter(new FileWriter(f,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String text) {
		try {
			w.write(text);
			w.newLine();
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
