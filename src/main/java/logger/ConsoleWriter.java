package logger;

/**
 * @author Daniel
 *
 * Class that outputs log text into the console.
 */
public class ConsoleWriter implements Writer {

	public void write(String text) {
		System.out.println(text);
	}

}
