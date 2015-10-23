package jewel;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import board.Board;
import board.Coordinate;

public class PowerJewelTest {

	@Test
	public void getMatchCoordinatesHorizontalPowerJewelTest() {
		Board board = new Board();
		Jewel horizontalPower = new HorizontalPowerJewel();
		List<Coordinate> result = horizontalPower.getMatchCoordinates(board, new Coordinate(0,0));
		Coordinate expected = new Coordinate(5,0);
		assertEquals(8, result.size());
		assertEquals(expected, result.get(5));
	}
	
	@Test
	public void getMatchCoordinatesVerticalPowerJewelTest() {
		Board board = new Board();
		Jewel horizontalPower = new VerticalPowerJewel();
		List<Coordinate> result = horizontalPower.getMatchCoordinates(board, new Coordinate(0,0));
		Coordinate expected = new Coordinate(0,5);
		assertEquals(8, result.size());
		assertEquals(expected, result.get(5));
	}

}
