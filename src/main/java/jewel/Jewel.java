package jewel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jewel")
public class Jewel {
	public Colour colour;
	
	public Jewel(Colour colour) {
		this.colour = colour;
	}
	
	public Jewel() {
	}
	
	/**
	 * Method to check whether or not 2 jewels are of the same Colour.
	 * 
	 * @param that
	 *     The jewel to compare with.
	 * @return boolean
	 *     True if colour is the same, false if not.
	 */
	public boolean isSameColour(Jewel that) {
		if (this.colour == that.colour) {
			return true;
		} else {
			return false;
		}
	}
	
	public Colour getColour() {
		return this.colour;
	}
	
}
