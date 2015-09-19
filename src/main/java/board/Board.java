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
	private List<StatsListener> statsListeners;
	
	private int score = 0;
	
	/**
	 * Board constructor method.
	 */
	public Board() {
		this.boardListeners = new ArrayList<BoardListener>();
		this.statsListeners = new ArrayList<StatsListener>();
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
	 * Adds a StatsListener to the Board.
	 * 
	 * @param listener
	 *     The StatsListener to be added.
	 */
	public void addStatsListener(StatsListener listener) {
		this.statsListeners.add(listener);
		Logger.log(Priority.INFO, "StatsListener " + listener.getClass().getSimpleName()
				+ " added to Board.");
	}
	
	/**
	 * Returns the list of all current StatsListeners.
	 */
	public List<StatsListener> getStatsListeners() {
		return statsListeners;
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
	
	/**
	 * Processes the selection of the Jewel at coordinate c, taking the previous
	 * selection (if there is one) into account. If a jewel is selected that is
	 * next to the previously selected jewel, swapJewels is called. Also
	 * notifies all BoardListeners of the currently and previously selected
	 * jewel.
	 * 
	 * @param coord
	 *     Coordinate of the jewel to process.
	 */
	public void processJewel(Coordinate coord) {
		if (!hasSelectedJewel() || (!Coordinate.areAdjacent(selectedPos, coord)
				&& !coord.equals(selectedPos)) ) {
			notifySelect(coord, selectedPos);
			selectedPos = coord;
			Logger.log(Priority.INFO, "Selected Position " + coord);
			
		} else if (Coordinate.areAdjacent(selectedPos, coord)) {
			swapJewels(selectedPos,coord);
			Logger.log(Priority.INFO, "Swapped Jewels " + selectedPos + ", " + coord);
			List<Match> matches = checkMatches();
			
			if (matches.isEmpty()) {
				swapJewels(selectedPos,coord);
				Logger.log(Priority.INFO, "Swapped back Jewels "
				+ selectedPos + ", " + coord);
				selectedPos = null;
				return;
			}
			while (!matches.isEmpty()) {
				processMatch(matches.get(0));
				matches = checkMatches();
			}
		}
	}
	
	/**
	 * Updates the score. Checks for completed sets. Refills the board. Updates the board.
	 * @param match
	 *     The Match to be processed.
	 */
	public void processMatch(Match match) {
		score += match.getPoints();
		notifyScoreChanged();
		if (match.isHorizontal()) {
			for (Coordinate c : match.getCoordinates()) {
				while (c.hasNorth()) {
					setJewel(getJewel(c.getNorth()), c);
					c = c.getNorth();
				}
				setJewel(new Jewel(Colour.randomColour()), c);
			}
		} else if (match.isVertical()) {
			//Nr. of elements above the group of jewels that form a vertical match
			int aboveMatch = match.getYMin();  
			
			int xcoord = match.getX();
			//Move all jewels above the cleared ones down.
			for (int delta = 0; delta < aboveMatch; delta++) {
				int temp = match.getYMax() - match.size() - delta;
				setJewel(getJewel(new Coordinate(xcoord, temp)),
						new Coordinate(xcoord, match.getYMax() - delta));
			}
			//Place new Jewels on the newly blanked spaces.
			for (int y = match.getYMax() - aboveMatch; y >= 0; y--) {
				setJewel(new Jewel(Colour.randomColour()),new Coordinate(xcoord,y));
			}
		}
		notifyBoardChanged();
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
	
	/**
	 * Notifies the StatsListener that the score has been changed / needs updating.
	 */
	public void notifyScoreChanged() {
		for (StatsListener l : statsListeners) {
			l.scoreChanged(score);
		}
	}
	
	/**
	 * Notifies the StatsListener that the level has been changed / needs updating.
	 * 
	 * @param level
	 *     (int) The level to be updated to.
	 */
  public void notifyLevelChanged(int level) {
		for (StatsListener l : statsListeners) {
			l.levelChanged(level);
		}
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
	 * Returns the coordinates of the currently selected jewel.
	 * 
	 * @return Coordinate
	 *     Coordinate of the selected jewel.
	 */
	public Coordinate getSelectedJewel() {
		return selectedPos;
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
	 * Sets the jewel at the given coordinate as the selected jewel.
	 * 
	 * @param coord
	 *     Coordinate of jewel to be selected.
	 */
	public void setSelectedJewel(Coordinate coord) {
		selectedPos = coord;
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
}
