package board;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for constructing Coordinate objects, required to identify each spot on the game board.
 * 
 * @author Group 20
 */
public class Coordinate extends MatchComponent{
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
	
	public void clear(Board board) {
		board.setJewel(null, this);
	}
	
	public void setMatchValue(Board board, int location) {
		board.setMatchValue(this, location);
	}
	
	public int getMatchValue(Board board) {
		return board.getMatchValue(this);
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

	@Override
	protected void addCoordinates(List<Coordinate> coordinates) {
		coordinates.add(this);
	}

	@Override
	public int numberOfJewels() {
		return 1;
	}

	@Override
	protected boolean isLeaf() {
		return true;
	}

	@Override
	public List<MatchComponent> getMatchComponents() {
		List<MatchComponent> res = new ArrayList<>();
		res.add(this);
		return res;
	}

	@Override
	public int baseScore() {
		return 50;
	}
	
	@Override
	public boolean inSameRow(MatchComponent that) {
		if (that instanceof Coordinate ) {
			return ((Coordinate) that).getY() == this.getY();
		}
		return false;
	}
	
	@Override
	public boolean inSameColumn(MatchComponent that) {
		if (that instanceof Coordinate ) {
			return ((Coordinate) that).getX() == this.getX();
		}
		return false;
	}

}
