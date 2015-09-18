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
	
	private Logger logger;
	
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
		logger = new Logger(Priority.ERROR);
		logger.addWriter(w1);
		logger.addWriter(w2);
		logger.addWriter(w3);
	}
	
	@Test
	public void testSetGetPriority() {
		logger.setPriority(Priority.INFO);
		assertEquals(Priority.INFO, logger.getPriority());
		
		logger.setPriority(Priority.ERROR);
		assertEquals(Priority.ERROR, logger.getPriority());
		
		logger.setPriority(Priority.WARNING);
		assertEquals(Priority.WARNING, logger.getPriority());
	}
	
	@Test
	public void testLog() {
		logger.setPriority(Priority.INFO);
		logger.log(infoMessage);

		verify(w1).write(endsWith(infoLog));
		verify(w2).write(endsWith(infoLog));
		verify(w3).write(endsWith(infoLog));

		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
	
	@Test
	public void testLogInfo() {
		logger.setPriority(Priority.INFO);
		logger.log(Priority.INFO, infoMessage);
		logger.log(Priority.WARNING, warningMessage);
		logger.log(Priority.ERROR, errorMessage);
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
		logger.setPriority(Priority.WARNING);
		logger.log(Priority.INFO, infoMessage);
		logger.log(Priority.WARNING, warningMessage);
		logger.log(Priority.ERROR, errorMessage);
		
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
		logger.setPriority(Priority.ERROR);
		logger.log(Priority.INFO, infoMessage);
		logger.log(Priority.WARNING, warningMessage);
		logger.log(Priority.ERROR, errorMessage);
		
		verify(w1).write(endsWith(errorLog));
		verify(w2).write(endsWith(errorLog));
		verify(w3).write(endsWith(errorLog));
		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
}
