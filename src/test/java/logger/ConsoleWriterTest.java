package logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleWriterTest {
	
	private ByteArrayOutputStream sysout = new ByteArrayOutputStream();
	private Writer w;
	private String s = "Lorum ipsum dolor sit amet.";

	@Before
	public void before() {
	    System.setOut(new PrintStream(sysout));
	    w = new ConsoleWriter();
	}
	
	@Test
	public void testWrite() {
		w.write(s);
		assertEquals(s + "\n", sysout.toString());
	}

	@After
	public void after() {
	    System.setOut(null);
	}

}
