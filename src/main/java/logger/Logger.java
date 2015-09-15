package logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Logger {

	private List<Writer> writers;
	
	public void addWriter(Writer w){
		writers.add(w);
	}
	
	public void log(String text){
		for(Writer w : writers){
			w.write(getDateString() + text);
		}
	}
	
	private String getDateString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss ");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
