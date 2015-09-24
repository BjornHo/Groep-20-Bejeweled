package logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TxtFileWriterTest {
	private File file;
	private Writer writer;
	private String loremipsum = "Lorum ipsum dolor sit amet.";
	private BufferedReader reader;
	
	@Before
	public void before() throws FileNotFoundException {
		file =  new File("src/test/java/logger/log.txt");
		file.delete();
		writer = new TxtFileWriter(file);
		reader  = new BufferedReader(new FileReader("src/test/java/logger/log.txt"));
	}
	
	@Test
	public void testWrite() throws IOException {
		assertNull(reader.readLine());
		writer.write(loremipsum);
		assertEquals(loremipsum, reader.readLine());
	}
	
	@After
	public void after() {
		file.delete();
	}
}
