package logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {
	@Mock private Writer w1;
	@Mock private Writer w2;
	@Mock private Writer w3;
	
	private String errorMessage = "This is an error message.";
	private String warningMessage = "This is a warning.";
	private String infoMessage = "This is some information.";
	private String errorLog = "[ERROR] " + errorMessage;
	private String warningLog = "[WARNING] " + warningMessage;
	private String infoLog = "[INFO] " + infoMessage;
	
	/**
	 * Setting up a new Logger object and adding Writer objects to them for testing.
	 */
	@Before
	public void before() {
		//logger = new Logger(Priority.ERROR);
		Logger.addWriter(w1);
		Logger.addWriter(w2);
		Logger.addWriter(w3);
	}
	
	@Test
	public void testSetGetPriority() {
		Logger.setPriority(Priority.INFO);
		assertEquals(Priority.INFO, Logger.getPriority());
		
		Logger.setPriority(Priority.ERROR);
		assertEquals(Priority.ERROR, Logger.getPriority());
		
		Logger.setPriority(Priority.WARNING);
		assertEquals(Priority.WARNING, Logger.getPriority());
	}
	
	@Test
	public void testLog() {
		Logger.setPriority(Priority.INFO);
		Logger.log(infoMessage);

		verify(w1).write(endsWith(infoLog));
		verify(w2).write(endsWith(infoLog));
		verify(w3).write(endsWith(infoLog));

		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
	
	@Test
	public void testLogInfo() {
		Logger.setPriority(Priority.INFO);
		Logger.log(Priority.INFO, infoMessage);
		Logger.log(Priority.WARNING, warningMessage);
		Logger.log(Priority.ERROR, errorMessage);
		verify(w1).write(endsWith(infoLog));
		verify(w2).write(endsWith(infoLog));
		verify(w3).write(endsWith(infoLog));
		
		verify(w1).write(endsWith(warningLog));
		verify(w2).write(endsWith(warningLog));
		verify(w3).write(endsWith(warningLog));
		
		verify(w1).write(endsWith(errorLog));
		verify(w2).write(endsWith(errorLog));
		verify(w3).write(endsWith(errorLog));
		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
	
	@Test
	public void testLogWarning() {
		Logger.setPriority(Priority.WARNING);
		Logger.log(Priority.INFO, infoMessage);
		Logger.log(Priority.WARNING, warningMessage);
		Logger.log(Priority.ERROR, errorMessage);
		
		verify(w1).write(endsWith(warningLog));
		verify(w2).write(endsWith(warningLog));
		verify(w3).write(endsWith(warningLog));
		
		verify(w1).write(endsWith(errorLog));
		verify(w2).write(endsWith(errorLog));
		verify(w3).write(endsWith(errorLog));
		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
	
	@Test
	public void testLogError() {
		Logger.setPriority(Priority.ERROR);
		Logger.log(Priority.INFO, infoMessage);
		Logger.log(Priority.WARNING, warningMessage);
		Logger.log(Priority.ERROR, errorMessage);
		
		verify(w1).write(endsWith(errorLog));
		verify(w2).write(endsWith(errorLog));
		verify(w3).write(endsWith(errorLog));
		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
}
