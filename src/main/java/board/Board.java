package board;

import jewel.Colour;
import jewel.Jewel;
import logger.Logger;
import logger.Priority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Group 20
 *     Contains methods able to create and manipulate Bejeweled game boards. 
 *     Class that defines the game board.
 */
public class Board {
	/** 2-Dimensional grid of spaces defining the board's playing field.
	 */
    private Jewel[][] jewelGrid = createGrid();
	
	/**
	 * Coordinate object used to define the currently selected Coordinate.
	 */
	private Coordinate selectedPos = null;
	
	/**
	 * List of Board Listeners, which will respond according to certain inputs from the user.
	 */
	private List<BoardListener> boardListeners;
	
	/**
	 * Board constructor method.
	 */
	public Board() {
		this.boardListeners = new ArrayList<BoardListener>();
	}
	
	/**
	 * Adds a BoardListener to the Board.
	 * 
	 * @param listener
	 *     The BoardListener to be added.
	 */
	public void addBoardListener(BoardListener listener) {
		this.boardListeners.add(listener);
		Logger.log(Priority.INFO, "BoardListener " + listener.getClass().getSimpleName()
				+ " added to Board.");
	}
	
	/**
	 * Returns the list of all current BoardListeners.
	 */
	public List<BoardListener> getBoardListeners() {
		return boardListeners;
	}
	
	/**
	 * Sets the play field to the given grid.
	 * @param Jewel[][] grid - the grid that will become the play field.
	 */
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
		Logger.log(Priority.INFO, "Grid set.");
	}
	
	public Jewel[][] getGrid() {
		return jewelGrid;
	}
	
	public Coordinate getSelectedPos() {
		return selectedPos;
	}
	
	/**
	 * Sets coordinate coord as the selected position.
	 * 
	 * @param coord
	 *     Coordinate (of jewel) to be selected.
	 */
	public void setSelectedPos(Coordinate coord) {
		selectedPos = coord;
	}

	/**
	 * Swaps the jewels at the given coordinates with each other. Also notifies
	 * all BoardListeners about the swap.
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the second jewel.
	 */
	public void swapJewels(Coordinate c1, Coordinate c2) {
		Jewel jewel1 = getJewel(c1);
		Jewel jewel2 = getJewel(c2);
		setJewel(jewel1, c2);
		setJewel(jewel2, c1);
		notifySwap(c1, c2);
	}
	
	/**
	 * Creates the predetermined grid of jewels for the start of the game.
	 * 
	 * @return Jewel[][]
	 *     The 2D array of Jewels that will be the starting play field.
	 */
	private Jewel[][] createGrid() {
		Jewel[][] grid = { 
				{ new Jewel(Colour.Red), new Jewel(Colour.Red),
					new Jewel(Colour.Blue), new Jewel(Colour.Yellow),
					new Jewel(Colour.Blue), new Jewel(Colour.Green),
					new Jewel(Colour.Green), new Jewel(Colour.Red) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow),
					new Jewel(Colour.Green), new Jewel(Colour.Yellow),
					new Jewel(Colour.Blue), new Jewel(Colour.Red),
					new Jewel(Colour.Purple), new Jewel(Colour.Red) },
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange),
					new Jewel(Colour.Blue), new Jewel(Colour.White),
					new Jewel(Colour.Orange), new Jewel(Colour.Purple),
					new Jewel(Colour.Red), new Jewel(Colour.Orange) },
				{ new Jewel(Colour.Yellow), new Jewel(Colour.Red),
					new Jewel(Colour.Purple), new Jewel(Colour.Red),
					new Jewel(Colour.White), new Jewel(Colour.Blue),
					new Jewel(Colour.Purple), new Jewel(Colour.Purple) },
				{ new Jewel(Colour.White), new Jewel(Colour.Orange),
					new Jewel(Colour.Red), new Jewel(Colour.White),
					new Jewel(Colour.Orange), new Jewel(Colour.Orange),
					new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red),
					new Jewel(Colour.Blue), new Jewel(Colour.Yellow),
					new Jewel(Colour.Yellow), new Jewel(Colour.Green),
					new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow),
					new Jewel(Colour.Blue), new Jewel(Colour.Purple),
					new Jewel(Colour.Red), new Jewel(Colour.Red),
					new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple),
					new Jewel(Colour.Green), new Jewel(Colour.Blue),
					new Jewel(Colour.Yellow), new Jewel(Colour.Orange),
					new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		return grid;
	}
	
	/**
	 * Returns the jewel from the given coordinates.
	 * 
	 * @param coord
	 *     Coordinate of the jewel you want.
	 * @return Jewel
	 *     The jewel from the given coordinates.
	 */
	public Jewel getJewel(Coordinate coord) {
		return jewelGrid[coord.getY()][coord.getX()];
	}
	
	/**
	 * Returns whether there is a jewel currently selected.
	 * 
	 * @return boolean
	 *     True/false if there is a selected jewel.
	 */
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	/**
	 * Sets the jewel at the given coordinate to the given jewel.
	 * 
	 * @param jewel
	 *     The jewel that the selected Jewel will become.
	 * @param coord
	 *     The coordinates of the selected jewel.
	 */
	public void setJewel(Jewel jewel, Coordinate coord) {
		jewelGrid[coord.getY()][coord.getX()] = jewel;
	}
	
	/**
	 * Checks the game field for vertically aligned matches (completed sets).
	 * 
	 * @param matched
	 *     The list of matches to be added to.
	 */
	public void checkVerticalMatches(List<Match> matched) {
		int matchcoord;
		// First the x loop, then y loop, because we're checking matches in columns.
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Colour initialc = jewelGrid[y][x].colour;
				Match match = new Match();
				match.add(new Coordinate(x,y));
				//This loop checks east of the initial jewel, one at a time.
				for (matchcoord = (y + 1); matchcoord < 8; matchcoord++) {
					Colour nextc = jewelGrid[matchcoord][x].colour;
					if (initialc.equals(nextc)) {
						match.add(new Coordinate(x,matchcoord));
					} else {
						break;
					}
				}
				if (match.size() >= 3) {
					matched.add(match);
					Logger.log(Priority.INFO, match.size()
						+ "-match found (vertical): "
						+ match.getCoordinates());
				}
				y = matchcoord - 1;
			}
		}
	}
	
	/**
	 * Checks the game field for horizontally alligned matches (completed sets).
	 * @param matched - the list of matches to be added to.
	 */

	public void checkHorizontalMatches(List<Match> matched) {
		int matchcoord;
		// First the y loop, then the x loop, because we're checking matches in rows.
		for (int y = 0; y < 8; y++) {						
			for (int x = 0; x < 8; x++) {						
				Colour initialc = jewelGrid[y][x].colour;
				Match match = new Match();
				match.add(new Coordinate(x,y));
				//This loop checks east of the initial jewel, one at a time.
				for (matchcoord = (x + 1); matchcoord < 8; matchcoord++) {
					Colour nextc = jewelGrid[y][matchcoord].colour;
					if (initialc.equals(nextc)) {
						match.add(new Coordinate(matchcoord,y));
					} else {
					break;
					}
				}
				if (match.size() >= 3) {
					matched.add(match);
					Logger.log(Priority.INFO, match.size()
						+ "-match found (horizontal): "
						+ match.getCoordinates());
				}
				x = matchcoord - 1;
			}
		}
	}
	
	/**
	 * Checks for matches (completed sets) within the game field.
	 * @return List
	 *     (List parameterized with Match) The list of matches found.
	 */
	public List<Match> checkMatches() {
		List<Match> matched = new ArrayList<Match>();
		checkVerticalMatches(matched);
		checkHorizontalMatches(matched);
		Logger.log(Priority.INFO, "checkMatches, " + matched.size() + " matches found.");
		return matched;
	}

	/**
	 * Notifies all BoardListeners of the swap (c1,c2).
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the first jewel.
	 */
	public void notifySwap(Coordinate c1, Coordinate c2) {
		for (BoardListener l : boardListeners) {
			l.jewelsSwapped(c1, c2);
		}
	}

	/**
	 * Notifies all BoardListeners of the selected Jewels.
	 * 
	 * @param c1
	 *     Coordinate of the first jewel.
	 * @param c2
	 *     Coordinate of the first jewel.
	 */
	public void notifySelect(Coordinate c1, Coordinate c2) {
		for (BoardListener l : boardListeners) {
			l.jewelSelected(c1, c2);
		}
	}

	/**
	 * Notifies all BoardListeners of Jewels on the board being removed/added/moved.
	 */
	public void notifyBoardChanged() {
		for (BoardListener l : boardListeners) {
			l.boardChanged();
		}
	}
}
