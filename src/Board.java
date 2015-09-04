import java.util.ArrayList;


public class Board {
	
	private Jewel[][] jewelGrid = createGrid();
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
		// Check if c1 makes a match with c2's neighbours and check if c2 makes a match with c1's neighbours.
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
	
	public Jewel getJewel(int x, int y) {
		return jewelGrid[y][x];
	}
	
	public boolean hasSelectedJewel() {
		return selectedPos != null;
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
