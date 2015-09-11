package jewel;

import board.Coordinate;

/**
 * @author hvkooijman
 * Jewel class that does store coordinates within it's Jewel Objects.
 */
public class AdvancedJewel extends Jewel {

	private Coordinate coordinate;
	
	/**
	 * AdvancedJewel constructor method.
	 * 
	 * @param colour 
	 * 			Colour of the Jewel.
	 * @param c
	 * 			Coordinate of the Jewel.
	 */
	public AdvancedJewel(Colour colour, Coordinate c) {
		super(colour);
		this.coordinate.setX(c.getX());
		this.coordinate.setY(c.getY());
	}

}
