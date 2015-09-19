package logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TXTFileWriterTest {
	private File f;
	private Writer w;
	private String s = "Lorum ipsum dolor sit amet.";
	private BufferedReader reader;
	
	@Before
	public void before() throws FileNotFoundException {
		f =  new File("src/test/java/logger/log.txt");
		f.delete();
		w = new TXTFileWriter(f);
		reader  = new BufferedReader(new FileReader("src/test/java/logger/log.txt"));
	}
	
	@Test
	public void testWrite() throws IOException {
		assertNull(reader.readLine());
		w.write(s);
		assertEquals(s, reader.readLine());
	}
	
	@After
	public void after() {
		f.delete();
	}
}
