package jewel;

import board.Coordinate;

/**
 * Jewel class that does store coordinates within it's Jewel Objects.
 * 
 * @author hvkooijman
 */
public class AdvancedJewel extends Jewel {

	private Coordinate coordinate;
	
	/**
	 * AdvancedJewel constructor method.
	 * 
	 * @param colour 
	 *     Colour of the Jewel.
	 * @param coord
	 *     Coordinate of the Jewel.
	 */
	public AdvancedJewel(Colour colour, Coordinate coord) {
		super(colour);
		this.coordinate.setX(coord.getX());
		this.coordinate.setY(coord.getY());
	}
}
