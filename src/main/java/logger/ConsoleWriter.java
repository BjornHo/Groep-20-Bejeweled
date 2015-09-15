package logger;

public class ConsoleWriter extends Writer {

	public void write(String text) {
		System.out.println(text);
	}

}
