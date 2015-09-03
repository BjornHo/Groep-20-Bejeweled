
public class Board {
	
	private Jewel[][] jewelGrid;
	private Jewel selected1 = null;
	private Jewel selected2 = null;
	
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
	}
	
	public void selectJewel(int x, int y) {
		// TODO: If no Jewel selected, select Jewel at (x,y). If a Jewel is already selected, check if both are horizontally/vertically adjacent.
	}
	
	public void swapJewels(int x1, int y1, int x2, int y2) {
		// TODO: Swap the Jewels at (x1,y1) and (x2,y2). If no match is made, swap the Jewels back after a short delay. If a match is made, remove the involved Jewels.
	}
	
	public Jewel[][] createGrid() {
		// TODO: create grid that is playable and does not contain sequences of 3+ Jewels.
	}
	
	
}
