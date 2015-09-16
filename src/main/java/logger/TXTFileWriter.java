package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXTFileWriter implements Writer {
	
	private BufferedWriter w;
	private File f;
	
	public TXTFileWriter() {
		try {
			w = new BufferedWriter(new FileWriter("logs/log.txt",true));
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
