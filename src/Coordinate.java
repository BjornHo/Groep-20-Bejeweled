/**
 * @author Group 20
 *
 * Class for constructing Coordinate objects, required to identify each spot on the game board.
 * 
 */
public class Coordinate {
	private int x;
	private int y;
	
	/**
	 * Coordinate Constructor method.
	 * 
	 * @param x value on the board
	 * @param y value on the board
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter method for returning the x-value of a Coordinate.
	 * 
	 * @return int
	 * 				x-value of a Coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter method for returning the y-value of a Coordinate.
	 * 
	 * @return y-value of a Coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Method for checking if 2 Jewel coordinates are either horizontally or vertically adjacent.
	 * 
	 * @param Coordinate c1
	 * 				Coordinate 1.
	 * @param Coordinate c2
	 * 				Coordinate 2.
	 * @return boolean
	 * 				True if c1 and c2 are either horizontally or vertically adjacent, false otherwise.
	 */
	public static boolean areAdjacent(Coordinate c1, Coordinate c2) {
		return ( (Math.abs(c1.x - c2.x) == 1 && c1.y == c2.y) //Horizontal adjacency check
				 || 
				 (c1.x == c2.x && Math.abs(c1.y - c2.y) == 1) ); //Vertical adjacency check
	}
	
	/**
	 * Getter method for acquiring a Jewel located north of a designated Jewel.
	 * 
	 * @param Coordinate c
	 * 				Coordinate of a Jewel of which the northern jewel is requested.
	 * @return Coordinate
	 * 				A Coordinate located north of Coordinate c.
	 */
	public Coordinate getNorth() {
		Coordinate NorthJewel = new Coordinate(this.x, (this.y + 1));
		return NorthJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located west of a designated Jewel.
	 * 
	 * @param Coordinate c
	 * 				Coordinate of a Jewel of which the western jewel is requested.
	 * @return Coordinate
	 * 				A Coordinate located west of Coordinate c.
	 */
	public Coordinate getWest() {
		Coordinate WestJewel = new Coordinate((this.x - 1), this.y);
		return WestJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located south of a designated Jewel.
	 * 
	 * @param Coordinate c
	 * 				Coordinate of a Jewel of which the southern jewel is requested.
	 * @return Coordinate
	 * 				A Coordinate located south of Coordinate c.
	 */
	public Coordinate getSouth() {
		Coordinate SouthJewel = new Coordinate(this.x, (this.y - 1));
		return SouthJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located east of a designated Jewel.
	 * 
	 * @param Coordinate c 
	 * 				Coordinate of a Jewel of which the eastern jewel is requested.
	 * @return Coordinate
	 * 				A Coordinate located east of Coordinate c.
	 */
	public Coordinate getEast() {
		Coordinate EastJewel = new Coordinate((this.x + 1), this.y);
		return EastJewel;
	}
	
	/**
	 * Method for checking if a Jewel object has a northern neighbour.
	 * 
	 * @return boolean
	 * 				True if Jewel object has northern neighbour.
	 */
	public boolean hasNorth() {
		return this.y <= 6;
	}
	
	/**
	 * Method for checking if a Jewel object has a western neighbour.
	 * 
	 * @return boolean
	 * 				True if Jewel object has western neighbour.
	 */
	public boolean hasWest() {
		return this.x >= 1;
	}
	
	/**
	 * Method for checking if a Jewel object has a southern neighbour.
	 * 
	 * @return boolean
	 * 				True if Jewel object has southern neighbour.
	 */
	public boolean hasSouth() {
		return this.y >= 1;
	}
	
	/**
	 * Method for checking if a Jewel object has a eastern neighbour.
	 * 
	 * @return boolean
	 * 				True if Jewel object has eastern neighbour.
	 */
	public boolean hasEast() {
		return this.x <= 6;
	}
	
	/**
	 * Method for replacing a Coordinate's current x-value for the designated x-value.
	 * 
	 * @param int x
	 * 				Value of x.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Method for replacing a Coordinate's current y-value for the designated y-value.
	 * 
	 * @param int y
	 * 				Value of y.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Turns a Coordinate Object's information into a String.
	 * 
	 * @return String
	 * 				String of designated Coordinate Object.
	 */
	public String toString() {
		return "Coordinate(" + x + "," + y + ")";
	}
}
