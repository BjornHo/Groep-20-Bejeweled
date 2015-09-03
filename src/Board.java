
public class Board {
	
	private Jewel[][] jewelGrid;
	private Coordinate selectedPos = null;
	
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
	}
	
	public void selectJewel(Coordinate c) {
		
		if ( !hasSelectedJewel() || !Coordinate.areAdjacent(selectedPos, c) ) {
			selectedPos = c;
		}
		else if (Coordinate.areAdjacent(selectedPos, c)) {
			swapJewels(selectedPos,c);
		}
	}
	
	public void swapJewels(Coordinate c1, Coordinate c2) {
		// TODO: Swap the Jewels at c1 and c2. If no match is made, swap the Jewels back after a short delay. If a match is made, remove the involved Jewels.
	}
	
	public Jewel[][] createGrid() {
		// TODO: create grid that is playable and does not contain sequences of 3+ Jewels.
	}
	
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	
}
