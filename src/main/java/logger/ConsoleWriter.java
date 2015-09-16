package logger;

public class ConsoleWriter implements Writer {

	public void write(String text) {
		System.out.println(text);
	}

}
