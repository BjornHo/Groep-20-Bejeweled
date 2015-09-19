package board;

/**
 * Class for constructing Coordinate objects, required to identify each spot on the game board.
 * 
 * @author Group 20
 */
public class Coordinate {
	private int xcoord;
	private int ycoord;
	
	/**
	 * Coordinate Constructor method.
	 * 
	 * @param x value on the board
	 * @param y value on the board
	 */
	public Coordinate(int xcoord, int ycoord) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}
	
	/**
	 * Getter method for returning the x-value of a Coordinate.
	 * 
	 * @return int
	 *     x-value of a Coordinate.
	 */
	public int getX() {
		return xcoord;
	}
	
	/**
	 * Getter method for returning the y-value of a Coordinate.
	 * 
	 * @return int
	 *     y-value of a Coordinate.
	 */
	public int getY() {
		return ycoord;
	}
	
	/**
	 * Method that checks if 2 Jewel coordinates are either horizontally or vertically adjacent.
	 * 
	 * @param c1
	 *     Coordinate of first Jewel.
	 * @param c2
	 *     Coordinate of second Jewel.
	 * @return boolean
	 *     True if c1 and c2 are either horizontally or vertically adjacent, false otherwise.
	 */
	public static boolean areAdjacent(Coordinate c1, Coordinate c2) {
			    //Horizontal adjacency check
		return ((Math.abs(c1.xcoord - c2.xcoord) == 1
				&& c1.ycoord == c2.ycoord)
			    //Vertical adjacency check
				^ (c1.xcoord == c2.xcoord 
				&& Math.abs(c1.ycoord - c2.ycoord) == 1)); 
	}
	
	/**
	 * Getter method for acquiring a Jewel located north of a designated Jewel.
	 * 
	 * @param c
	 *     Coordinate of a Jewel of which the northern jewel is requested.
	 * @return Coordinate
	 *     A Coordinate located north of Coordinate c.
	 */
	public Coordinate getNorth() {
		Coordinate northJewel = new Coordinate(this.xcoord, (this.ycoord - 1));
		return northJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located west of a designated Jewel.
	 * 
	 * @param c
	 *     Coordinate of a Jewel of which the western jewel is requested.
	 * @return Coordinate
	 *     A Coordinate located west of Coordinate c.
	 */
	public Coordinate getWest() {
		Coordinate westJewel = new Coordinate((this.xcoord - 1), this.ycoord);
		return westJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located south of a designated Jewel.
	 * 
	 * @param Coordinate c
	 *     Coordinate of a Jewel of which the southern jewel is requested.
	 * @return Coordinate
	 *     A Coordinate located south of Coordinate c.
	 */
	public Coordinate getSouth() {
		Coordinate southJewel = new Coordinate(this.xcoord, (this.ycoord + 1));
		return southJewel;
	}
	
	/**
	 * Getter method for acquiring a Jewel located east of a designated Jewel.
	 * 
	 * @param Coordinate c 
	 *     Coordinate of a Jewel of which the eastern jewel is requested.
	 * @return Coordinate
	 *     A Coordinate located east of Coordinate c.
	 */
	public Coordinate getEast() {
		Coordinate eastJewel = new Coordinate((this.xcoord + 1), this.ycoord);
		return eastJewel;
	}
	
	/**
	 * Method for checking if a Jewel object has a northern neighbour.
	 * 
	 * @return boolean
	 *     True if Jewel object has northern neighbour.
	 */
	public boolean hasNorth() {
		return ycoord > 0;
	}
	
	/**
	 * Method for checking if a Jewel object has a western neighbour.
	 * 
	 * @return boolean
	 *     True if Jewel object has western neighbour.
	 */
	public boolean hasWest() {
		return this.xcoord >= 1;
	}
	
	/**
	 * Method for checking if a Jewel object has a southern neighbour.
	 * 
	 * @return boolean
	 *     True if Jewel object has southern neighbour.
	 */
	public boolean hasSouth() {
		return this.ycoord < 7;
	}
	
	/**
	 * Method for checking if a Jewel object has a eastern neighbour.
	 * 
	 * @return boolean
	 *     True if Jewel object has eastern neighbour.
	 */
	public boolean hasEast() {
		return this.xcoord <= 6;
	}
	
	/**
	 * Method for replacing a Coordinate's current x-value for the designated x-value.
	 * 
	 * @param xcoord
	 *     (int) Value of x.
	 */
	public void setX(int xcoord) {
		this.xcoord = xcoord;
	}
	
	/**
	 * Method for replacing a Coordinate's current y-value for the designated y-value.
	 * 
	 * @param ycoord
	 *     (int) Value of y.
	 */
	public void setY(int ycoord) {
		this.ycoord = ycoord;
	}
	
	/**
	 * Turns a Coordinate Object's information into a String.
	 * 
	 * @return String
	 *     String of designated Coordinate Object.
	 */
	public String toString() {
		return "Coordinate(" + xcoord + "," + ycoord + ")";
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Coordinate) {
			Coordinate that = (Coordinate)other;
			return (this.xcoord == that.xcoord && this.ycoord == that.ycoord);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (5 * xcoord + xcoord * (xcoord + ycoord));
	}
}
