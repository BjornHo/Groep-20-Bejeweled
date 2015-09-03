
public class Jewel {

	protected Colour colour;
	
	/**
	 * Jewel Constructor
	 * 
	 * @param colour Colour of the jewel.
	 */
	public Jewel(Colour colour) {
		this.colour = colour;
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
