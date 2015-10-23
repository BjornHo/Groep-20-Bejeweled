package jewel;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import board.Board;
import board.Coordinate;

@XmlTransient
@XmlSeeAlso({NormalJewel.class, HorizontalPowerJewel.class, VerticalPowerJewel.class})
public abstract class Jewel {
	public Colour colour;
	
	public Jewel() {
		
	}
	
	public Jewel(Colour colour) {
		if (colour.isJewelColour()) {
			this.colour = colour;
		} else {
			throw new IllegalArgumentException("Invalid colour specified: "
					+ colour + ". Only basic Colours can be used.");
		}
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
		return this.colour == that.colour;
	}
	
	public Colour getColour() {
		return this.colour;
	}
	
	public abstract List<Coordinate> getMatchCoordinates(Board board, Coordinate coord);
	
	public abstract Colour getImageColour();
	
}
