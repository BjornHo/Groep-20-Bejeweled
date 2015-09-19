package board;

/**
 * Listener for the Board class.
 * 
 * @author Group 20
 */
public interface BoardListener {
	
	/**
	 * The Jewels with coordinates a and b are swapped.
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the second jewel.
	 */
	public void jewelsSwapped(Coordinate c1, Coordinate c2);
	
	/**
	 * The jewel at coordinates jewel is currently selected, the jewel with
	 * coordinates old was selected previously.
	 * 
	 * @param jewel
	 *     Coordinates of the currently selected jewel.
	 * @param old
	 *     Coordinates of the previously selected jewel.
	 */
	public void jewelSelected(Coordinate jewel, Coordinate old);
	
	/**
	 * Jewels are either removed from or placed on the board this listener
	 * belongs to.
	 */
	public void boardChanged();
}