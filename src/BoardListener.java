
public interface BoardListener {

	public void jewelsSwapped(Coordinate a, Coordinate b);
	
	public void jewelSelected(Coordinate n, Coordinate old);
	
	public void boardChanged();
}