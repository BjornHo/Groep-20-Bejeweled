package observers;

import board.Coordinate;

import java.util.List;

/**
 * Listener for the Board class.
 * 
 * @author Group 20
 */
public interface BoardObserver {
	
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
	 * All jewels in the list 'coordinates' are cleared from the board,
	 * because they were part of a match.
	 * @param coordinates
	 */
	public void jewelsCleared(List<Coordinate> coordinates);
	
	/**
	 * Coordinate 'from' is dropped down to Coordinate 'to'.
	 * @param from
	 * @param to
	 */
	public void jewelDropped(Coordinate from, Coordinate to);
	
	/**
	 * A new Jewel is placed on Coordinate 'coordinate'
	 * @param coordinate
	 */
	public void coordinateFilled(Coordinate coordinate);
	
	/**
	 * The grid of the board is replaced by a new one.
	 */
	public void boardChanged();
}