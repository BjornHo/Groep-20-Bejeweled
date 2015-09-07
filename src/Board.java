import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	/**
	 * Board constructor method.
	 */
	public Board() {
		this.boardListeners = new ArrayList<>();
	}
	
	/**
	 * Method that adds a board listener to the "boardListeners" list.
	 * 
	 * @param BoardListener listener
	 * 				The Board Listener added.	
	 */
	public void addBoardListener(BoardListener listener) {
		this.boardListeners.add(listener);
	}
	
	/**
	 * Method that sets a specific grid as the game board.
	 * 
	 * @param Jewel[][] grid
	 * 				The grid of the game board.
	 */
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
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
		}

		else if (Coordinate.areAdjacent(selectedPos, c)) {
			swapJewels(selectedPos,c);
			List<Match> matches = checkMatches();
			if(matches.isEmpty()) {
				swapJewels(selectedPos,c);
				selectedPos = null;
				return;
			}
			while(!matches.isEmpty()){
				processMatch(matches.get(0));
				matches=checkMatches();
			}
		}
	}
	
	public void processMatch(Match m) {

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
				setJewel(getJewel(x,m.getYMax()-m.size()-delta), new Coordinate(x, m.getYMax()-delta));
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
	
	public Jewel getJewel(int x, int y) {
		return jewelGrid[y][x];
	}
	
	public Jewel getJewel(Coordinate c){
		return jewelGrid[c.getY()][c.getX()];
	}
	
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	public Coordinate getSelectedJewel(){
		return selectedPos;
	}
	
	public void setJewel(Jewel jewel, Coordinate c){
		jewelGrid[c.getY()][c.getX()] = jewel;
	}
	
	public void setSelectedJewel(Coordinate c){
		selectedPos = c;
	}
	
	public void checkVerticalMatches(List<Match> matched){
		Match m = new Match();
		for(int x = 0; x < 8; x++){
			
			int sameColorCounter = 1;
			Colour prevColour = jewelGrid[0][x].getColour();
			for(int y = 1; y < 8; y++){
				if(prevColour.equals(jewelGrid[y][x].getColour())){
					sameColorCounter++;
					if(sameColorCounter == 3){
						m.add(new Coordinate(x, y-2));
						m.add(new Coordinate(x, y-1));
						m.add(new Coordinate(x, y));		
					}
					if(sameColorCounter > 3){
						m.add(new Coordinate(x, y));
					}
				}
				else{
					prevColour = jewelGrid[y][x].getColour();
					sameColorCounter = 1;
					if(m.size() > 2){
						matched.add(m);
					}
					m = new Match();
				}
			}
		}
	}
	
	public void checkHorizontalMatches(List<Match> matched){
		Match m = new Match();
		for(int y = 0; y < 8; y++){
			
			int sameColorCounter = 1;
			Colour prevColour = jewelGrid[y][0].getColour();
			for(int x = 1; x < 8; x++){
				if(prevColour.equals(jewelGrid[y][x].getColour())){
					sameColorCounter++;
					System.out.println(sameColorCounter + ", (" + x + "," + y + ")" );
					if(sameColorCounter == 3){
						m.add(new Coordinate(x-2, y));
						m.add(new Coordinate(x-1, y));
						m.add(new Coordinate(x, y));
					}
					if(sameColorCounter > 3){
						m.add(new Coordinate(x, y));
					}
				}
				else{
					prevColour = jewelGrid[y][x].getColour();
					sameColorCounter = 1;
					if(m.size() > 2){
						matched.add(m);
					}
					m = new Match();
				}
			}
		}
	}
	
	public List<Match> checkMatches(){
		List<Match> matched = new ArrayList<Match>();
		checkVerticalMatches(matched);
		checkHorizontalMatches(matched);
		System.out.println("Number of Matches: " + matched.size());
		return matched;
	}
	
}
