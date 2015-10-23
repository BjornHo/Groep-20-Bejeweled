package jewel;

import static org.junit.Assert.assertEquals;

import java.util.List;

import jewel.Colour;
import jewel.Jewel;

import org.junit.Before;
import org.junit.Test;

import board.Board;
import board.Coordinate;

public class JewelTest {

	private Jewel jewel;
	
	@Before
	public void before() {	
		jewel = new NormalJewel(Colour.Red);
	}
	
	/**
	 * Testing the getColour method.
	 */
	@Test
	public void getColour() {
		assertEquals(Colour.Red, jewel.getColour());
	}
	
	/**
	 * Testing the isSameColour method with a jewel of the same Colour.
	 */
	@Test
	public void isSameColourTrue() {
		Jewel compared = new NormalJewel(Colour.Red);
		assertEquals(true, jewel.isSameColour(compared));
	}
	
	/**
	 * Testing the isSameColour method with a jewel of a different Colour.
	 */
	@Test
	public void isSameColourFalse() {
		Jewel compared = new NormalJewel(Colour.Blue);
		assertEquals(false, jewel.isSameColour(compared));
	}
	
	@Test
	public void getMatchCoordinatesNormalJewelTest() {
		Board board = new Board();
		Jewel horizontalPower = new NormalJewel();
		Coordinate expected = new Coordinate(0,0);
		List<Coordinate> result = horizontalPower.getMatchCoordinates(board, expected);
		assertEquals(1, result.size());
		assertEquals(expected, result.get(0));
	}
}
