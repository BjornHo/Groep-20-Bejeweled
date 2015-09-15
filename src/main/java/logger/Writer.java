package logger;

public interface Writer {
	public void write(String text);
	
	public Priority getPriority();
}
