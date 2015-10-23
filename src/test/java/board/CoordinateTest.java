package board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class CoordinateTest {
	
	private Coordinate coord = new Coordinate(2,3);

	/**
	 * Testing the getX() method.
	 */
	@Test
	public void getX() {
		assertEquals(2, coord.getX());
	}
	
	/**
	 * Testing the getY() method.
	 */
	@Test
	public void getY() {
		assertEquals(3, coord.getY());
	}
	
	/**
	 * Testing the setX method.
	 */
	@Test
	public void setX() {
		coord.setX(5);
		assertEquals(5, coord.getX());
	}
	
	/**
	 * Testing the setY method.
	 */
	@Test
	public void setY() {
		coord.setY(5);
		assertEquals(5, coord.getY());
	}
	
	/**
	 * Testing the areAdjacent method with two horizontal adjacent coordinates.
	 */
	@Test
	public void areAdjacentHorizontal() {
		Coordinate coord2 = new Coordinate(3,3);
		assertEquals(true, Coordinate.areAdjacent(coord, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with two vertical adjacent coordinates.
	 */
	@Test
	public void areAdjacentVertical() {
		Coordinate coord2 = new Coordinate(2,2);
		assertEquals(true, Coordinate.areAdjacent(coord, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with two non-adjacent coordinates.
	 */
	@Test
	public void areAdjacentFalse() {
		Coordinate coord2 = new Coordinate(1,2);
		assertEquals(false, Coordinate.areAdjacent(coord, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with using the same Coordinate twice.
	 */
	@Test
	public void areAdjacentSameCoordinate() {
		assertEquals(false, Coordinate.areAdjacent(coord, coord));
	}
	
	/**
	 * Testing the toString method.
	 */
	@Test
	public void toStringTest() {
		String expected = "Coordinate(2,3)";
		assertEquals(expected, coord.toString());
	}
	
	/**
	 * Testing the equals method with a non-Coordinate Object.
	 */
	@Test
	public void equalsNoCoordinate() {
		assertEquals(false, coord.equals(2));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different X and same Y.
	 */
	@Test
	public void equalsDifferentXCoordinate() {
		assertEquals(false, coord.equals(new Coordinate(3,3)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different Y and same X.
	 */
	@Test
	public void equalsDifferentYCoordinate() {
		assertEquals(false, coord.equals(new Coordinate(2,2)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different X and Y.
	 */
	@Test
	public void equalsDifferentCoordinates() {
		assertEquals(false, coord.equals(new Coordinate(6,6)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with the same X and Y.
	 */
	@Test
	public void equalsSameCoordinates() {
		assertEquals(true, coord.equals(new Coordinate(2,3)));
	}
	
}

