package observers;

import board.Coordinate;

public interface Observable {

	/**
	 * Notifies all boardObservers of the swap (c1,c2).
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the first jewel.
	 */
	public void notifySwap(Coordinate c1, Coordinate c2);
	
	/**
	 * Notifies all boardObservers of the selected Jewels.
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the first jewel.
	 */
	public void notifySelect(Coordinate c1, Coordinate c2);
	
	/**
	 * Notifies all boardObservers of Jewels on the board being removed/added/moved.
	 */
	public void notifyBoardChanged();
	
}
