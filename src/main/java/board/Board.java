package board;

import jewel.Colour;
import jewel.HorizontalPowerJewel;
import jewel.Jewel;
import jewel.NormalJewel;
import jewel.VerticalPowerJewel;
import logger.Logger;
import logger.Priority;
import observers.BoardObservable;
import observers.BoardObserver;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Group 20
 *     Contains methods able to create and manipulate Bejeweled game boards. 
 *     Class that defines the game board.
 */
@XmlRootElement(name = "board")
@XmlAccessorType(XmlAccessType.FIELD)
public class Board implements BoardObservable{
	/** 
	 * 2-Dimensional grid of spaces defining the board's playing field.
	 */
	@XmlElement(name = "row")
	@XmlElementWrapper(name = "grid")
    private Jewel[][] jewelGrid;
	
	private int width = -1;
	private int height = -1;
	
	private int[][] matchLocations;
	/**
	 * Coordinate object used to define the currently selected Coordinate.
	 */
	@XmlTransient
	private Coordinate selectedPos = null;
	
	/**
	 * List of Board Listeners, which will respond according to certain inputs from the user.
	 */
	@XmlTransient
	private List<BoardObserver> boardObservers;
	
	public Board() {
		this.boardObservers = new ArrayList<BoardObserver>();
		Jewel[][] grid = createGrid();
		setGrid(grid);
		matchLocations =  new int[width][height];
	}
	
	@Override
	public void addBoardObserver(BoardObserver listener) {
		this.boardObservers.add(listener);
		Logger.log(Priority.INFO, "BoardObserver " + listener.getClass().getSimpleName()
				+ " added to Board.");
	}
	
	public List<BoardObserver> getboardObservers() {
		return boardObservers;
	}
	
	/**
	 * Sets the play field to the given grid.
	 * @param Jewel[][] grid - the grid that will become the play field.
	 */
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
		this.height = grid.length;
		this.width = grid[0].length;
		Logger.log(Priority.INFO, "Grid set.");
	}
	
	public Jewel[][] getGrid() {
		return jewelGrid;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Coordinate getSelectedPos() {
		return selectedPos;
	}
	
	public void setSelectedPos(Coordinate coord) {
		selectedPos = coord;
	}
	
	public List<Coordinate> getRow(Coordinate coord) {
		int ycoord = coord.getY();
		List<Coordinate> res = new ArrayList<>();
		for (int xcoord = 0; xcoord < width; xcoord++) {
			res.add(new Coordinate(xcoord,ycoord));
		}
		return res;
	}
	
	public List<Coordinate> getColumn(Coordinate coord) {
		int xcoord = coord.getX();
		List<Coordinate> res = new ArrayList<>();
		for (int ycoord = 0; ycoord < height; ycoord++) {
			res.add(new Coordinate(xcoord,ycoord));
		}
		return res;
	}
	
	public void reset() {
		jewelGrid = createGrid();
		selectedPos = null;
		notifyBoardChanged();
	}

	/**
	 * Swaps the jewels at the given coordinates with each other. Also notifies
	 * all boardObservers about the swap.
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
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.Green),
					new NormalJewel(Colour.Green), new NormalJewel(Colour.Red) },
				{ new NormalJewel(Colour.Orange), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Green), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.Purple), new NormalJewel(Colour.Red) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.White),
					new NormalJewel(Colour.Orange), new HorizontalPowerJewel(Colour.Purple),
					new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange) },
				{ new NormalJewel(Colour.Yellow), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.Purple), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.White), new NormalJewel(Colour.Blue),
					new NormalJewel(Colour.Purple), new NormalJewel(Colour.Purple) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Orange),
					new NormalJewel(Colour.Red), new NormalJewel(Colour.White),
					new NormalJewel(Colour.Orange), new NormalJewel(Colour.Orange),
					new NormalJewel(Colour.Yellow), new NormalJewel(Colour.White) },
				{ new NormalJewel(Colour.White), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Yellow), new NormalJewel(Colour.Green),
					new NormalJewel(Colour.Red), new NormalJewel(Colour.Yellow) },
				{ new NormalJewel(Colour.Orange), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Blue), new NormalJewel(Colour.Purple),
					new NormalJewel(Colour.Red), new NormalJewel(Colour.Red),
					new NormalJewel(Colour.Green), new NormalJewel(Colour.Red) },
				{ new VerticalPowerJewel(Colour.White), new NormalJewel(Colour.Purple),
					new NormalJewel(Colour.Green), new NormalJewel(Colour.Blue),
					new NormalJewel(Colour.Yellow), new NormalJewel(Colour.Orange),
					new NormalJewel(Colour.Red), new NormalJewel(Colour.Green) }
		};
		return grid;
	}
	
	public Jewel getJewel(Coordinate coord) {
		return jewelGrid[coord.getY()][coord.getX()];
	}
	
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	public void setJewel(Jewel jewel, Coordinate coord) {
		jewelGrid[coord.getY()][coord.getX()] = jewel;
	}
	
	/**
	 * Removes the given match from the board (replaces all jewels of the match by 'null')
	 * @param match The match to remove from the board.
	 */
	private void clearMatch(Match match) {
		Coordinate first = match.getCoordinates().get(0);
		Jewel jewel = getJewel(first);
		for (MatchComponent comp : match.getMatchComponents()) {
			comp.clear(this);
		}
		
		if (match.size() == 4) {
			if (match.outerHorizontal()) {
				this.setJewel(new HorizontalPowerJewel(jewel.colour), first);
			} else if (match.outerVertical()) {
				this.setJewel(new VerticalPowerJewel(jewel.colour), first);
			}
		}
		
	}
	
	public int clearMatches(List<Match> matches) {
		List<Coordinate> coordinates = new ArrayList<>();
		int totalPoints = 0;
		for (Match match : matches) {
			clearMatch(match);
			coordinates.addAll(match.getCoordinates());
			totalPoints += match.getPoints();
		}
		notifyClear(coordinates);
		return totalPoints;
	}
	
	public void setMatchValue(Coordinate coord, int location) {
		matchLocations[coord.getY()][coord.getX()] = location;
	}
	
	public int getMatchValue(Coordinate coord) {
		return matchLocations[coord.getY()][coord.getX()];
	}
	
	public void resetMatchLocations() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++ ) {
				matchLocations[y][x] = 0;
			}
		}
	}
	
	/**
	 * Makes the existing jewels above empty spaces drop down.
	 */
	public void applyGravity() {
		for (int row = height - 1; row >= 1; row--) {
			List<Coordinate> holes = getHolesInRow(row);
			for (Coordinate coord : holes) {
				applyGravityCoordinate(coord);
			}
		}
	}
	
	/**
	 * Fills the empty space at the given coordinate with the nearest jewel
	 * above it, iff the space at the given coordinate is actually empty.
	 * 
	 * @param coord
	 */
	private void applyGravityCoordinate(Coordinate coord) {
		if (!coordinateExists(coord) || coord.getY() < 1 || getJewel(coord) != null) {
			return;
		}
		Coordinate above = new Coordinate(coord.getX(),coord.getY() - 1);
		while (above.getY() > -1 && getJewel(above) == null ) {
			above.setY(above.getY() - 1);
		}
		if (coordinateExists(above)) {
			setJewel(getJewel(above), coord);
			setJewel(null, above);
			notifyDropped(above,coord);
		}

	}
	
	/**
	 * Returns true iff Coordinate coord is within the bounds of the board.
	 * Returns false otherwise.
	 * 
	 * @param coord
	 * @return
	 */
	public boolean coordinateExists(Coordinate coord) {
		return coord.getX() > -1 && coord.getX() < width 
				&& coord.getY() > -1 && coord.getY() < height;
	}

	/**
	 * Returns an array with all coordinates from the given row where no jewel
	 * is located.
	 * 
	 * @param row
	 * @return
	 */
	public List<Coordinate> getHolesInRow(int row) {
		if (row >= height - 1 && row <= 0) {
			return null;
		}
		List<Coordinate> list = new ArrayList<>();
		for (int column = 0; column < width; column++) {
			Coordinate coord = new Coordinate(column, row);
			if (getJewel(coord) == null) {
				list.add(coord);
			}
		}
		return list;
	}
	
	/**
	 * Places new Jewels in the empty spaces in the top rows.
	 */
	public void refillGrid() {
		int row = 0;
		List<Coordinate> holes = getHolesInRow(row);
		while (!holes.isEmpty()) {
			for (Coordinate coord : holes) {
				setJewel(new NormalJewel(Colour.randomColour()), coord);
				notifyFill(coord);
			}
			row++;
			if (row >= height) {
				break;
			}
			holes = getHolesInRow(row);
		}
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
				Match match = new Match(this);
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
					for (MatchComponent comp : match.getMatchComponents()) {
						comp.setMatchValue(this, matched.size() + 1);
					}
					matched.add(match);
//					Logger.log(Priority.INFO, match.size()
//						+ "-match found (vertical): "
//						+ match.getCoordinates());
				}
				y = matchcoord - 1;
			}
		}
	}
	
	/**
	 * Checks the game field for horizontally aligned matches (completed sets).
	 * 
	 * @param matched
	 * 		The list of matches to be added to.
	 */
	public void checkHorizontalMatches(List<Match> matched) {
		int matchcoord;
		// First the y loop, then the x loop, because we're checking matches in rows.
		for (int y = 0; y < 8; y++) {						
			for (int x = 0; x < 8; x++) {						
				Colour initialc = jewelGrid[y][x].colour;
				Match match = new Match(this);
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
					boolean partOfAnotherMatch = false;
					for (int i = 0; i < match.getMatchComponents().size() 
						&& !partOfAnotherMatch; i++) {
						MatchComponent comp = match.getMatchComponents().get(i);
						int matchValue = comp.getMatchValue(this);
						if (matchValue > 0) {
							match.add(matched.get(matchValue - 1));
							matched.set(matchValue - 1, match);
							match.setMatchValue(this, matchValue);
							partOfAnotherMatch = true;
						}
					}
					if (!partOfAnotherMatch) {
						matched.add(match);
					}
//					Logger.log(Priority.INFO, match.size()
//						+ "-match found (horizontal): "
//						+ match.getCoordinates());
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
		resetMatchLocations();
		List<Match> matched = new ArrayList<Match>();
		checkVerticalMatches(matched);
		checkHorizontalMatches(matched);
		Logger.log(Priority.INFO, "checkMatches, " + matched.size() + " matches found.");
		return matched;
	}

	@Override
	public void notifySwap(Coordinate c1, Coordinate c2) {
		for (BoardObserver l : boardObservers) {
			l.jewelsSwapped(c1, c2);
		}
	}

	@Override
	public void notifySelect(Coordinate c1, Coordinate c2) {
		for (BoardObserver l : boardObservers) {
			l.jewelSelected(c1, c2);
		}
	}


	@Override
	public void notifyClear(List<Coordinate> coordinates) {
		for (BoardObserver l : boardObservers) {
			l.jewelsCleared(coordinates);
		}
	}
	
	@Override
	public void notifyDropped(Coordinate from, Coordinate to) {
		for (BoardObserver l : boardObservers) {
			l.jewelDropped(from, to);
		}		
	}
	
	@Override
	public void notifyFill(Coordinate coordinate) {
		for (BoardObserver l : boardObservers) {
			l.coordinateFilled(coordinate);
		}			
	}
	
	/**
	 * Notifies all BoardListeners of Jewels on the board being removed/added/moved.
	 */
	@Override
	public void notifyBoardChanged() {
		for (BoardObserver l : boardObservers) {
			l.boardChanged();
		}
	}
}
