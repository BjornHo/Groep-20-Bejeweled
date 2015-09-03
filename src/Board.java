
public class Board {
	
	private Jewel[][] jewelGrid;
	private Coordinate selectedPos = null;
	
	public void setGrid(Jewel[][] grid) {
		this.jewelGrid = grid;
	}
	
	public void selectJewel(Coordinate c) {
		System.out.println(c.toString());
		if ( !hasSelectedJewel() || !Coordinate.areAdjacent(selectedPos, c) ) {
			selectedPos = c;
		}
		else if (Coordinate.areAdjacent(selectedPos, c)) {
			swapJewels(selectedPos,c);
		}
		System.out.println("SelectedPos: " + selectedPos.toString());
	}
	
	public void swapJewels(Coordinate c1, Coordinate c2) {
		// TODO: Swap the Jewels at c1 and c2. If no match is made, swap the Jewels back after a short delay. If a match is made, remove the involved Jewels.
		
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
	
	public boolean hasSelectedJewel() {
		return selectedPos != null;
	}
	
	
}
