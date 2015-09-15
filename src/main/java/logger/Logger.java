package logger;

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
	private Priority p;
	
	public Logger(Priority p) {
		this.p = p;
	}
	
	public void setPriority(Priority p) {
		this.p = p;
	}
	
	public void addWriter(Writer w){
		writers.add(w);
	}
	
	/**
	 * Logs the String "text", using priority INFO.
	 * @param text String to log.
	 */
	public void log(String text){
		log(Priority.INFO, text);
	}
	
	/**
	 * Logs the String "text" iff the log has a priority that is equal to or higher than the priority of the Logger.
	 * @param p The priority of logging "text".
	 * @param text Text to log.
	 */
	public void log(Priority p, String text) {
		if(p.ordinal() <= this.p.ordinal()){
			for(Writer w : writers){
				w.write(getDateString() + " [" + p + "] " + text);
			}
		}
	}
	
	private String getDateString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static Logger createLogger(Priority p) {
		Logger l = new Logger(p);
		Writer w1 = new ConsoleWriter();
		l.addWriter(w1);
		return l;
	}

}
