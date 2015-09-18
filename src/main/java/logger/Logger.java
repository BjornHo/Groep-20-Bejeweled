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

	private static List<Writer> writers = new ArrayList<Writer>();
	private static Priority priority;
	private static SimpleDateFormat dateLogFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static SimpleDateFormat dateFileNameFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
	
	static {
		priority = Priority.INFO;
		Writer w1 = new ConsoleWriter();
		Writer w2 = new TXTFileWriter(new File("logs/" + getFileNameString() + ".log"));
		writers.add(w1);
		writers.add(w2);
	}
	
	private Logger(Priority p) {
		priority = p;
	}

	public static void addWriter(Writer writer){
		if (!writers.contains(writers)) {
			writers.add(writer);
		}
	}

	/**
	 * Logs the String "text", using priority INFO.
	 * 
	 * @param text
	 *            String to log.
	 */
	public static void log(String text) {
		log(Priority.INFO, text);
	}

	/**
	 * Logs the String "text" iff the log has a priority that is equal to or
	 * higher than the priority of the Logger class.
	 * 
	 * @param priority
	 *            The priority of logging "text".
	 * @param text
	 *            Text to log.
	 */
	public static void log(Priority p, String text) {
		if (p.ordinal() <= priority.ordinal()) {
			for (Writer w : writers) {
				w.write(getDateString() + " [" + p + "] " + text);
			}
		}
	}
	
	/**
	 * Returns the date/time as a String, in a format that is suitable for use
	 * in the logs.
	 * 
	 * @return The date and time as a String.
	 */
	private static String getDateString() {
		Date date = new Date();
		return dateLogFormat.format(date);
	}

	/**
	 * Returns the date/time as a String, in a format that is suitable for use
	 * in the filenames of the log files.
	 * 
	 * @return The date and time as a String.
	 */
	private static String getFileNameString() {
		Date date = new Date();
		return dateFileNameFormat.format(date);
	}

	public static Priority getPriority() {
		return priority;
	}

	public static void setPriority(Priority p) {
		priority = p;
	}

}
