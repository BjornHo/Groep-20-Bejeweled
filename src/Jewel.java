
public class Jewel {
	
	protected enum Colour { Blue, Green, Orange, Purple, Red, White, Yellow, Selected}; 
	protected Colour colour;
	protected int xCoord;
	protected int yCoord;
	
	public Jewel(Colour colour, int xCoord, int yCoord) {
		this.colour = colour;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
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
	
	public boolean isSameColour(Jewel that) {
		if(this.colour == that.colour) {
			return true;
		}
		
		else
			return false;
	}
	
}
