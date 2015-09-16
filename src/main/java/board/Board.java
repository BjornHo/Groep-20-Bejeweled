package board;
import java.util.ArrayList;
import java.util.List;
import logger.Logger;

import jewel.Colour;
import jewel.Jewel;
import logger.Priority;


/**
 * @author Group 20
 *
 * Class that defines the game board. Contains methods able to create and manipulate Bejeweled game boards.
 */

public class Board {
	
	/**
	 * 2-Dimensional grid of spaces defining the board's playing field.
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

	private Logger l;
	
	/**
	 * Board constructor method.
	 */
	public Board() {
		this.boardListeners = new ArrayList<BoardListener>();
		this.statsListeners = new ArrayList<StatsListener>();
		l = Logger.createLogger(Priority.INFO);
	}
	
	/**
	 * Adds a BoardListener to the Board.
	 * @param BoardListener listener - the listener to be added.
	 */
	public void addBoardListener(BoardListener listener) {
		this.boardListeners.add(listener);
		l.log(Priority.INFO, "BoardListener " + listener.getClass().getSimpleName() + " added to Board.");
	}
	
	/**
	 * Returns the list of all current BoardListeners.
	 */
	public List<BoardListener> getBoardListeners(){
		return boardListeners;
	}
	
	
	/**
	 * Adds a StatsListener to the Board.
	 * @param StatsListener listener - the listener to be added.
	 */
	public void addStatsListener(StatsListener listener) {
		this.statsListeners.add(listener);
		l.log(Priority.INFO, "StatsListener " + listener.getClass().getSimpleName() + " added to Board.");
	}
	
	/**
	 * Returns the list of all current StatsListeners.
	 */
	public List<StatsListener> getStatsListeners(){
		return statsListeners;
	}
	
	/**
	 * Sets the play field to the given grid.
	 * @param Jewel[][] grid - the grid that will become the play field.
	 */
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
		l.log(Priority.INFO, "Grid set.");
	}
	
	public Jewel[][] getGrid(){
		return jewelGrid;
	}
	
	/**
	 * Processes the selection of the Jewel at coordinate c, taking the previous
	 * selection (if there is one) into account. If a jewel is selected that is
	 * next to the previously selected jewel, swapJewels is called. Also
	 * notifies all BoardListeners of the currently and previously selected
	 * jewel.
	 * 
	 * @param c
	 *            Coordinate of the jewel to process.
	 */
	public void processJewel(Coordinate c) {
		
		if ( !hasSelectedJewel() || (!Coordinate.areAdjacent(selectedPos, c) && !c.equals(selectedPos)) ) {
			notifySelect(c, selectedPos);
			selectedPos = c;
			l.log(Priority.INFO, "Selected Position " + c);
		}

		else if (Coordinate.areAdjacent(selectedPos, c)) {
			swapJewels(selectedPos,c);
			l.log(Priority.INFO, "Swapped Jewels " + selectedPos + ", " + c);
			List<Match> matches = checkMatches();
			if(matches.isEmpty()) {
				swapJewels(selectedPos,c);
				l.log(Priority.INFO, "Swapped back Jewels " + selectedPos + ", " + c);
				selectedPos = null;
				return;
			}
			while(!matches.isEmpty()){
				processMatch(matches.get(0));
				matches=checkMatches();
			}
		}
	}
	
	/**
	 * Updates the score. Checks for completed sets. Refills the board. Updates the board.
	 * @param Match m - the match to be processed.
	 */
	public void processMatch(Match m) {
		score += m.getPoints();
		notifyScoreChanged();
		if(m.isHorizontal()) {
			for(Coordinate c : m.getCoordinates()) {

				while(c.hasNorth()){
					setJewel(getJewel(c.getNorth()), c);
					c = c.getNorth();
				}
				setJewel(new Jewel(Colour.randomColour()), c);
			}
		}
		else if(m.isVertical()) {
			int aboveMatch = m.getYMin();  //nr of elements above the group of jewels that form a vertical match
			
			int x = m.getX();
			//move all jewels above the cleared ones down.
			for(int delta = 0; delta < aboveMatch; delta++) {
				
				setJewel(getJewel(new Coordinate(x,m.getYMax()-m.size()-delta)), new Coordinate(x, m.getYMax()-delta));
			}
			//place new Jewels on the newly blanked spaces.
			for(int y=m.getYMax() - aboveMatch; y >= 0; y--) {
				setJewel(new Jewel(Colour.randomColour()), new Coordinate(x,y));
			}
		}
		notifyBoardChanged();
	}
	
	/**
	 * Swaps the jewels at the given coordinates with each other. Also notifies
	 * all BoardListeners about the swap.
	 * 
	 * @param c1
	 *            Coordinate of the first jewel.
	 * @param c2
	 *            Coordinate of the second jewel.
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
	 * @return Jewel[][] - The 2d array of Jewels that will be the starting play field.
	 */
	private Jewel[][] createGrid() {
		Jewel[][] grid = { 
				{ new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Green), new Jewel(Colour.Green), new Jewel(Colour.Red) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Red), new Jewel(Colour.Purple), new Jewel(Colour.Red) },
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange), new Jewel(Colour.Blue), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Orange) },
				{ new Jewel(Colour.Yellow), new Jewel(Colour.Red), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Purple) },
				{ new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.White), new Jewel(Colour.Orange), new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.White) },
				{ new Jewel(Colour.White), new Jewel(Colour.Red), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Yellow), new Jewel(Colour.Green), new Jewel(Colour.Red), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow), new Jewel(Colour.Blue), new Jewel(Colour.Purple), new Jewel(Colour.Red), new Jewel(Colour.Red), new Jewel(Colour.Green), new Jewel(Colour.Yellow) },
				{ new Jewel(Colour.White), new Jewel(Colour.Purple), new Jewel(Colour.Green), new Jewel(Colour.Blue), new Jewel(Colour.Yellow), new Jewel(Colour.Orange), new Jewel(Colour.Red), new Jewel(Colour.Green) }
		};
		return grid;
	}
	
	/**
	 * Notifies all BoardListeners of the swap (c1,c2).
	 * @param c1
	 * @param c2
	 */
	public void notifySwap(Coordinate c1, Coordinate c2) {
		for(BoardListener l : boardListeners) {
			l.jewelsSwapped(c1, c2);
		}
	}
	
	/**
	 * Notifies all BoardListeners of the selected Jewels.
	 * @param c1
	 * @param c2
	 */
	public void notifySelect(Coordinate c1, Coordinate c2) {
		for(BoardListener l : boardListeners) {
			l.jewelSelected(c1, c2);
		}
	}
	
	/**
	 * Notifies all BoardListeners of Jewels on the board being removed/added/moved.
	 */
	public void notifyBoardChanged() {
		for(BoardListener l : boardListeners) {
			l.boardChanged();
		}
	}
	
	/**
	 * Notifies the StatsListener that the score has been changed / needs updating.
	 */
	public void notifyScoreChanged() {
		for(StatsListener l : statsListeners){
			l.scoreChanged(score);
		}
	}
	
	/**
	 * Notifies the StatsListener that the level has been changed / needs updating.
	 * @param level - int - the level to be updated to.
	 */
	public void notifyLevelChanged(int level) {
		for(StatsListener l : statsListeners){
			l.levelChanged(level);
		}
	}
	
	
	/**
	 * Returns the jewel from the given coordinateds.
	 * @param c - coordinates of the jewel you want.
	 * @return Jewel - the jewel from the given coordinates.
	 */
	public Jewel getJewel(Coordinate c){
		return jewelGrid[c.getY()][c.getX()];
	}
	
	/**
	 * Returns whether there is a jewel currently selected.
	 * @return boolean - true/false if there is a selected jewel.
	 */
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	/**
	 * Returns the coordinates of the currently selected jewel.
	 * @return Coordinate - coordinate of the selected jewel.
	 */
	public Coordinate getSelectedJewel(){
		return selectedPos;
	}
	
	/**
	 * Sets the jewel at the given coordinate to the given jewel.
	 * @param jewel - the jewel that the selected jewel will become.
	 * @param c - the coordinates of the selected jewel.
	 */
	public void setJewel(Jewel jewel, Coordinate c){
		jewelGrid[c.getY()][c.getX()] = jewel;
	}
	
	/**
	 * Checks the game field for vertically aligned matches (completed sets).
	 * @param matched - the list of matches to be added to.
	 */

	public void checkVerticalMatches(List<Match> matched) {
		int n;
		for (int x = 0; x < 8; x++){						// First the x loop, then y loop,
			for(int y = 0; y < 8; y++){						// because we're checking matches in columns.
				Colour initialc = jewelGrid[y][x].colour;
				Match m = new Match();
				m.add(new Coordinate(x,y));
				for (n = (y+1); n < 8; n++) {				//This loop checks east of the initial jewel, one at a time.
					Colour nextc = jewelGrid[n][x].colour;
					if (initialc.equals(nextc))
						m.add(new Coordinate(x,n));
					else
						break;
				}
				if (m.size() >= 3)	{						// If 3 or more Jewels are in a "match" object, 
					matched.add(m);							// the object is stored in a list contain all current matches.
					l.log(Priority.INFO, m.size() + "-match found (vertical): " + m.getCoordinates());
				}
				y = n - 1;
			}
		}
	}
	
	/**
	 * Checks the game field for horizontally alligned matches (completed sets).
	 * @param matched - the list of matches to be added to.
	 */

	public void checkHorizontalMatches(List<Match> matched) {
		int n;
		for (int y = 0; y < 8; y++){						// First the y loop, then the x loop,
			for(int x = 0; x < 8; x++){						// because we're checking matches in rows.
				Colour initialc = jewelGrid[y][x].colour;
				Match m = new Match();
				m.add(new Coordinate(x,y));
				for (n = (x+1); n < 8; n++) {				//This loop checks east of the initial jewel, one at a time.
					Colour nextc = jewelGrid[y][n].colour;
					if (initialc.equals(nextc))
						m.add(new Coordinate(n,y));
					else
						break;
				}
				if (m.size() >= 3)	{						// If 3 or more Jewels are in a "match" object, 
					matched.add(m);							// the object is stored in a list contain all current matches.
					l.log(Priority.INFO, m.size() + "-match found (horizontal): " + m.getCoordinates());
				}
				x = n - 1;
			}
		}
	}
	
	/**
	 * Checks for matches (completed sets) within the game field.
	 * @return List<Match> - The list of matches found.
	 */
	public List<Match> checkMatches(){
		List<Match> matched = new ArrayList<Match>();
		checkVerticalMatches(matched);
		checkHorizontalMatches(matched);
		l.log(Priority.INFO, "checkMatches, " + matched.size() + " matches found.");
		return matched;
	}
}
