
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * Returns true iff c1 and c2 are either horizontally or vertically adjacent.
	 * @param c1 Coordinate 1
	 * @param c2 Coordinate 2
	 * @return True if c1 and c2 are horizontally or vertically adjacent, false otherwise.
	 */
	public static boolean areAdjacent(Coordinate c1, Coordinate c2) {
		return ( (Math.abs(c1.x - c2.x) == 1 && c1.y == c2.y) //Horizontal adjacency check
				 || 
				 (c1.x == c2.x && Math.abs(c1.y - c2.y) == 1) ); //Vertical adjacency check
	}
	
	/**
	 * Getter method for acquiring a Jewel located north of a designated Jewel.
	 * 
	 * @param c Coordinate of a Jewel of which the northern jewel is requested.
	 * @return A Coordinate located north of Coordinate c.
	 */
	public Coordinate getNorth(Coordinate c) {
		if((c.y == 7)) {
			System.out.println("This jewel is in the top row, there are none north of this one");
			return null;
		}
		Coordinate NorthJewel = new Coordinate(c.x, (c.y + 1));
		return NorthJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located west of a designated Jewel.
	 * 
	 * @param c Coordinate of a Jewel of which the western jewel is requested.
	 * @return A Coordinate located west of Coordinate c.
	 */
	public Coordinate getWest(Coordinate c) {
		if((c.x == 0)) {
			System.out.println("This jewel is in the westernmost column, there are none west of this one");
			return null;
		}
		Coordinate WestJewel = new Coordinate((c.x - 1), c.y);
		return WestJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located south of a designated Jewel.
	 * 
	 * @param c Coordinate of a Jewel of which the southern jewel is requested.
	 * @return A Coordinate located south of Coordinate c.
	 */
	public Coordinate getSouth(Coordinate c) {
		if((c.y == 0)) {
			System.out.println("This jewel is in the bottom row, there are none south of this one");
			return null;
		}
		Coordinate SouthJewel = new Coordinate(c.x, (c.y - 1));
		return SouthJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located east of a designated Jewel.
	 * 
	 * @param c Coordinate of a Jewel of which the eastern jewel is requested.
	 * @return A Coordinate located east of Coordinate c.
	 */
	public Coordinate getEast(Coordinate c) {
		if((c.x == 7)) {
			System.out.println("This jewel is in the easternmost column, there are none east of this one");
			return null;
		}
		Coordinate EastJewel = new Coordinate((c.x + 1), c.y);
		return EastJewel;
	}
	
	/**
	 * Turns a Coordinate Object's information into a String.
	 * 
	 * @return String of designated Coordinate Object.
	 */
	public String toString() {
		return "Coordinate(" + x + "," + y + ")";
	}
}
