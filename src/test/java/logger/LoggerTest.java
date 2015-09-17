package logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoggerTest {
	@Mock private Writer w1;
	@Mock private Writer w2;
	@Mock private Writer w3;
	
	private Logger l;
	
	private String errorMessage = "This is an error message.";
	private String warningMessage = "This is a warning.";
	private String infoMessage = "This is some information.";
	private String errorLog = "[ERROR] " + errorMessage;
	private String warningLog = "[WARNING] " + warningMessage;
	private String infoLog = "[INFO] " + infoMessage;
	
	@Before
	public void before() {
		l = new Logger(Priority.ERROR);
		l.addWriter(w1);
		l.addWriter(w2);
		l.addWriter(w3);
	}
	
	@Test
	public void testSetGetPriority() {
		l.setPriority(Priority.INFO);
		assertEquals(Priority.INFO, l.getPriority());
		l.setPriority(Priority.ERROR);
		assertEquals(Priority.ERROR, l.getPriority());
	}
	
	@Test
	public void testLog() {
		l.setPriority(Priority.INFO);
		l.log(infoMessage);

		verify(w1).write(endsWith(infoLog));
		verify(w2).write(endsWith(infoLog));
		verify(w3).write(endsWith(infoLog));

		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
	
	@Test
	public void testLogInfo() {
		l.setPriority(Priority.INFO);
		l.log(Priority.INFO, infoMessage);
		l.log(Priority.WARNING, warningMessage);
		l.log(Priority.ERROR, errorMessage);
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
		l.setPriority(Priority.WARNING);
		l.log(Priority.INFO, infoMessage);
		l.log(Priority.WARNING, warningMessage);
		l.log(Priority.ERROR, errorMessage);
		
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
		l.setPriority(Priority.ERROR);
		l.log(Priority.INFO, infoMessage);
		l.log(Priority.WARNING, warningMessage);
		l.log(Priority.ERROR, errorMessage);
		
		verify(w1).write(endsWith(errorLog));
		verify(w2).write(endsWith(errorLog));
		verify(w3).write(endsWith(errorLog));
		verifyNoMoreInteractions(w1);
		verifyNoMoreInteractions(w2);
		verifyNoMoreInteractions(w3);
	}
}
