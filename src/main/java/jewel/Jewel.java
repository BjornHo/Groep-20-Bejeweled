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
	
	public abstract List<Coordinate> getMatchCoordinates(Board board, Coordinate coord);
	
}
