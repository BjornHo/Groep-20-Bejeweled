package logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Logger class which can log actions to different outputs using Writers.
 * 
 * @author Group 20
 *
 */
public class Logger {

	private List<Writer> writers = new ArrayList<Writer>();
	private Priority priority;
	
	public Logger(Priority priority) {
		this.priority = priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public void addWriter(Writer w) {
		if (!writers.contains(w)) {
			writers.add(w);
		}
	}
	
	/**
	 * Logs the String "text", using priority INFO.
	 * @param text String to log.
	 */
	public void log(String text) {
		log(Priority.INFO, text);
	}
	
	/**
	 * Logs the String "text" iff the log has a priority that is equal to or higher than the priority of the Logger.
	 * @param priority The priority of logging "text".
	 * @param text Text to log.
	 */
	public void log(Priority priority, String text) {
		if (priority.ordinal() <= this.priority.ordinal()) {
			for (Writer w : writers) {
				w.write(getDateString() + " [" + priority + "] " + text);
			}
		}
	}
	
	private String getDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public static Logger createLogger(Priority priority) {
		Logger l = new Logger (priority);
		Writer w1 = new ConsoleWriter();
		Writer w2 = new TXTFileWriter(new File("logs/log.txt"));
		l.addWriter(w1);
		l.addWriter(w2);
		return l;
	}

}
