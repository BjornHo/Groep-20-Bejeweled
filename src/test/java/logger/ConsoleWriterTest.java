package logger;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleWriterTest {
	private ByteArrayOutputStream sysout = new ByteArrayOutputStream();
	private Writer writer;
	private String lorumipsum = "Lorum ipsum dolor sit amet.";

	@Before
	public void before() {
	    System.setOut(new PrintStream(sysout));
	    writer = new ConsoleWriter();
	}
	
	@Test
	public void testWrite() {
		writer.write(lorumipsum);
		assertEquals(lorumipsum, sysout.toString().trim());
	}

	@After
	public void after() {
	    System.setOut(null);
	}
}
