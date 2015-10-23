package observers;

import java.util.List;

import board.Coordinate;

public interface BoardObservable {

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
	 * Notifies all BoardObservers of cleared Jewels
	 * @param coordinates
	 */
	public void notifyClear(List<Coordinate> coordinates);
	
	/**
	 * Notifies all BoardObservers of jewels that have dropped down.
	 * @param from Starting position of the dropped jewel.
	 * @param to Destination of the dropped jewel.
	 */
	public void notifyDropped(Coordinate from, Coordinate to);
	
	/**
	 * Notifies all BoardObservers of newly added jewels.
	 * @param coordinate
	 */
	public void notifyFill(Coordinate coordinate);
	
	/**
	 * Notifies all boardObservers of Jewels on the board being removed/added/moved.
	 */
	public void notifyBoardChanged();
	
	/**
	 * Notifies all boardObservers of a jewel that needs to be refreshed (eg, changed
	 * to a supergem)
	 */
	public void notifyCoordinateRefreshed(Coordinate coordinate);
	
	/**
	 * Adds a BoardObserver to the Board.
	 * 
	 * @param listener
	 *     The BoardObserver to be added.
	 */
	public void addBoardObserver(BoardObserver listener);
	
}
