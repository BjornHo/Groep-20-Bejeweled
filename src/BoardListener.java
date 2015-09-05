/**
 * Listener for the Board class.
 * 
 * @author Group 20
 *
 */
public interface BoardListener {
	
	/**
	 * The Jewels with coordinates a and b are swapped.
	 * 
	 * @param a
	 *            Coordinates of the first jewel.
	 * @param b
	 *            Coordinates of the second jewel.
	 */
	public void jewelsSwapped(Coordinate a, Coordinate b);
	
	/**
	 * The jewel at coordinates jewel is currently selected, the jewel with
	 * coordinates old was selected previously.
	 * 
	 * @param jewel
	 *            Coordinates of the currently selected jewel.
	 * @param old
	 *            Coordinates of the previously selected jewel.
	 */
	public void jewelSelected(Coordinate jewel, Coordinate old);
	
	/**
	 * Jewels are either removed from or placed on the board this listener
	 * belongs to.
	 */
	public void boardChanged();
}