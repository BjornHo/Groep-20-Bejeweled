
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
		return ( (Math.abs(c1.x - c2.x) == 1 && Math.abs(c1.y - c2.y) == 0) || (Math.abs(c1.x - c2.x) == 0 && Math.abs(c1.y - c2.y) == 1) );
	}
}
