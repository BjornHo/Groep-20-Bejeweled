import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Board {
	
	private Jewel[][] jewelGrid = createGrid();
	private Coordinate selectedPos = null;
	private List<BoardListener> boardListeners;
	
	public Board() {
		this.boardListeners = new ArrayList<>();
	}
	
	public void addBoardListener(BoardListener listener) {
		this.boardListeners.add(listener);
	}
		
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
			Set<Coordinate> matches = checkMatches();
			if(matches.isEmpty()) {
				swapJewels(selectedPos,c);
				selectedPos = null;
				return;
			}
			processMatch(matches);
		}
	}
	
	public void processMatch(Set<Coordinate> match) {
		if(matchIsVertical(match))
			processVerticalMatch(match);
		else if(matchIsHorizontal(match))
			processHorizontalMatch(match);
	}
	
	public void processHorizontalMatch(Set<Coordinate> match) {
		for(Coordinate c : match) {
			//TODO: add points for 1 Jewel.
			for(int y = c.getY(); y > 0; y--){
				setJewel(getJewel(c.getNorth()), c);
			}
			setJewel(new Jewel(Colour.randomColour()), new Coordinate(c.getX(), 0));
		}
		notifyBoardChanged();
	}
	
	public void processVerticalMatch(Set<Coordinate> match) {
		//TODO: implement processVerticalMatch.
//		for(Coordinate c : match) {
//			for(int y = c.getY(); y > 0; y--){
//				setJewel(getJewel(c.getNorth()), c);
//			}
//			setJewel(new Jewel(Colour.Red), new Coordinate(c.getX(), 0));
//		}
//		notifyBoardChanged();
	}
	
	public boolean matchIsVertical(Set<Coordinate> match) {
		int x = -1;
		for(Coordinate c : match) {
			if(x == -1)
				x = c.getX();
			else if(x != c.getX())
				return false;
		}
		return true;
	}
	
	public boolean matchIsHorizontal(Set<Coordinate> match) {
		int y = -1;
		for(Coordinate c : match) {
			if(y == -1)
				y = c.getY();
			else if(y != c.getY())
				return false;
		}
		return true;
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
	
	public void checkVerticalMatches(Set<Coordinate> matched){
		for(int x = 0; x < 8; x++){
			int sameColorCounter = 1;
			Colour prevColour = jewelGrid[0][x].getColour();
			for(int y = 1; y < 8; y++){
				if(prevColour.equals(jewelGrid[y][x].getColour())){
					sameColorCounter++;
					if(sameColorCounter == 3){
						matched.add(new Coordinate(x, y-2));
						matched.add(new Coordinate(x, y-1));
						matched.add(new Coordinate(x, y));
					}
					if(sameColorCounter > 3){
						matched.add(new Coordinate(x, y));
					}
				}
				else{
					prevColour = jewelGrid[y][x].getColour();
					sameColorCounter = 1;
				}
			}
		}
	}
	
	public void checkHorizontalMatches(Set<Coordinate> matched){
		for(int y = 0; y < 8; y++){
			int sameColorCounter = 1;
			Colour prevColour = jewelGrid[y][0].getColour();
			for(int x = 1; x < 8; x++){
				if(prevColour.equals(jewelGrid[y][x].getColour())){
					sameColorCounter++;
					if(sameColorCounter == 3){
						matched.add(new Coordinate(x-2, y));
						matched.add(new Coordinate(x-1, y));
						matched.add(new Coordinate(x, y));
					}
					if(sameColorCounter > 3){
						matched.add(new Coordinate(x, y));
					}
				}
				else{
					prevColour = jewelGrid[y][x].getColour();
					sameColorCounter = 1;
				}
			}
		}
	}
	
	public Set<Coordinate> checkMatches(){
		Set<Coordinate> matched = new HashSet<Coordinate>();
		checkVerticalMatches(matched);
		checkHorizontalMatches(matched);
		System.out.println("Number of jewels matched: " + Integer.toString(matched.size()));
		return matched;
	}
	
		// =========================================================================================================================
		// ==================== Slechst een idee om matching the checken, misschien wat lang? ======================================
		// =========================================================================================================================
		
		/**
		 * Method that checks whether or not 3 or more Jewels are aligned.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked.
		 * @return boolean
		 * 				True if 3 or more Jewels are aligned.
		 */
		public boolean isHorizontalMatch(Coordinate c) {
			return (checkWesternAlignment(c) + checkEasternAlignment(c)) >= 3;
		}
		
		/**
		 * Method that counts how many Jewels with the same colour are aligned west of the designated Jewel coordinate.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked for western alignment.
		 * @return int
		 * 				Amount of aligning Jewels.
		 */
		public int checkWesternAlignment(Coordinate c) {
			int aligncount = 1;
			Jewel C = this.getJewel(c.getX(), c.getY());
			
			if (c.hasWest()) {
				Jewel West = this.getJewel(c.getWest().getX(), c.getWest().getY());
				if (West.isSameColour(C)) {
					aligncount++;
					if (c.getWest().hasWest()) {
						Jewel WestWest = this.getJewel(c.getWest().getWest().getX(), c.getWest().getWest().getY());
						if (WestWest.isSameColour(C)) {
							aligncount++;
						}
					}
				}
			}
			return aligncount;
		}
		
		/**
		 * Method that counts how many Jewels with the same colour are aligned east of the designated Jewel coordinate.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked for eastern alignment.
		 * @return int
		 * 				Amount of aligning Jewels.
		 */
		public int checkEasternAlignment(Coordinate c) {
			int aligncount = 1;
			Jewel C = this.getJewel(c.getX(), c.getY());
			
			if (c.hasEast()) {
				Jewel East = this.getJewel(c.getEast().getX(), c.getEast().getY());
				if (East.isSameColour(C)) {
					aligncount++;
					if (c.getEast().hasEast()) {
						Jewel EastEast = this.getJewel(c.getEast().getEast().getX(), c.getEast().getEast().getY());
						if (EastEast.isSameColour(C)) {
							aligncount++;
						}
					}
				}
			}
			return aligncount;
		}

		
		
		
		/**
		 * Method that checks whether or not 3 or more Jewels are aligned.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked.
		 * @return boolean
		 * 				True if 3 or more Jewels are aligned.
		 */
		public boolean isVerticalMatch(Coordinate c) {
			return (checkNorthernAlignment(c) + checkSouthernAlignment(c)) >= 3;
		}
		
		/**
		 * Method that counts how many Jewels with the same colour are aligned north of the designated Jewel coordinate.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked for northern alignment.
		 * @return int
		 * 				Amount of aligning Jewels.
		 */
		public int checkNorthernAlignment(Coordinate c) {
			int aligncount = 1;
			Jewel C = this.getJewel(c.getX(), c.getY());
			
			if (c.hasNorth()) {
				Jewel North = this.getJewel(c.getNorth().getX(), c.getNorth().getY());
				if (North.isSameColour(C)) {
					aligncount++;
					if (c.getNorth().hasNorth()) {
						Jewel NorthNorth = this.getJewel(c.getNorth().getNorth().getX(), c.getNorth().getNorth().getY());
						if (NorthNorth.isSameColour(C)) {
							aligncount++;
						}
					}
				}
			}
			return aligncount;
		}
		
		/**
		 * Method that counts how many Jewels with the same colour are aligned south of the designated Jewel coordinate.
		 * 
		 * @param Coordinate c
		 * 				Coordinate of the Jewel being checked for southern alignment.
		 * @return int
		 * 				Amount of aligning Jewels.
		 */
		public int checkSouthernAlignment(Coordinate c) {
			int aligncount = 1;
			Jewel C = this.getJewel(c.getX(), c.getY());
			
			if (c.hasSouth()) {
				Jewel South = this.getJewel(c.getSouth().getX(), c.getSouth().getY());
				if (South.isSameColour(C)) {
					aligncount++;
					if (c.getSouth().hasSouth()) {
						Jewel SouthSouth = this.getJewel(c.getSouth().getSouth().getX(), c.getSouth().getSouth().getY());
						if (SouthSouth.isSameColour(C)) {
							aligncount++;
						}
					}
				}
			}
			return aligncount;
		}
	
}
