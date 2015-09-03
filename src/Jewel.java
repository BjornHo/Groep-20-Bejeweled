
public class Jewel {
	
	protected enum Colour { Blue, Green, Orange, Purple, Red, White, Yellow, Selected}; 
	protected Colour colour;
	protected int xCoord;
	protected int yCoord;
	
	/**
	 * Jewel Constructor
	 * 
	 * @param colour Colour of the jewel.
	 * @param xCoord is the X Coordinate on the board.
	 * @param yCoord is the Y Coordinate on the board.
	 */
	public Jewel(Colour colour, int xCoord, int yCoord) {
		this.colour = colour;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**
	 * isAdjacent method to check whether or not a jewel is adjacent to another jewel.
	 * @param that is the jewel to compare with.
	 * @return returns a boolean.
	 */
	public boolean isAdjacent(Jewel that) {
		if(this.xCoord == that.xCoord && Math.abs(this.yCoord - that.yCoord) == 1){
			return true;
		}
		else if(this.yCoord == that.yCoord && Math.abs(this.xCoord - that.xCoord) == 1){
			return true;
		}
		else
			return false;				
	}
	
	/**
	 * isSameColour method to check whether or not 2 jewels are of the same colour.
	 * @param that is the jewel to compare with.
	 * @return returns a boolean.
	 */
	public boolean isSameColour(Jewel that) {
		if(this.colour == that.colour) {
			return true;
		}
		
		else
			return false;
	}
	
}
