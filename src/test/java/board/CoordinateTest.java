package board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class CoordinateTest {
	private Coordinate coord1;

	@Before
	public void before() {
		coord1 = new Coordinate(2,3);
	}
	
	/**
	 * Testing the getX() method.
	 */
	@Test
	public void getX() {
		assertEquals(2, coord1.getX());
	}
	
	/**
	 * Testing the getY() method.
	 */
	@Test
	public void getY() {
		assertEquals(3, coord1.getY());
	}
	
	/**
	 * Testing the setX method.
	 */
	@Test
	public void setX() {
		coord1.setX(5);
		assertEquals(5, coord1.getX());
	}
	
	/**
	 * Testing the setY method.
	 */
	@Test
	public void setY() {
		coord1.setY(5);
		assertEquals(5, coord1.getY());
	}
	
	/**
	 * Testing the areAdjacent method with two horizontal adjacent coordinates.
	 */
	@Test
	public void areAdjacentHorizontal() {
		Coordinate coord2 = new Coordinate(3,3);
		assertEquals(true, Coordinate.areAdjacent(coord1, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with two vertical adjacent coordinates.
	 */
	@Test
	public void areAdjacentVertical() {
		Coordinate coord2 = new Coordinate(2,2);
		assertEquals(true, Coordinate.areAdjacent(coord1, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with two non-adjacent coordinates.
	 */
	@Test
	public void areAdjacentFalse() {
		Coordinate coord2 = new Coordinate(1,2);
		assertEquals(false, Coordinate.areAdjacent(coord1, coord2));
	}
	
	/**
	 * Testing the areAdjacent method with using the same Coordinate twice.
	 */
	@Test
	public void areAdjacentSameCoordinate() {
		assertEquals(false, Coordinate.areAdjacent(coord1, coord1));
	}
	
	/**
	 * Testing the getNorth() method.
	 */
	@Test
	public void getNorth() {
		Coordinate expected = new Coordinate(2,2);
		assertEquals(expected, coord1.getNorth());
	}
	
	/**
	 * Testing the getNorth() method.
	 */
	@Test
	public void getEast() {
		Coordinate expected = new Coordinate(3,3);
		assertEquals(expected, coord1.getEast());
	}
	
	/**
	 * Testing the getNorth() method.
	 */
	@Test
	public void getSouth() {
		Coordinate expected = new Coordinate(2,4);
		assertEquals(expected, coord1.getSouth());
	}
	
	/**
	 * Testing the getNorth() method.
	 */
	@Test
	public void getWest() {
		Coordinate expected = new Coordinate(1,3);
		assertEquals(expected, coord1.getWest());
	}
	
	/**
	 * Testing the hasNorth() method in case it should be false.
	 */
	@Test
	public void hasNorthFalse() {
		coord1 = new Coordinate(7,0);
		assertEquals(false, coord1.hasNorth());
	}
	
	/**
	 * Testing the hasEast() method in case it should be false.
	 */
	@Test
	public void hasEastFalse() {
		coord1 = new Coordinate(7,0);
		assertEquals(false, coord1.hasEast());
	}
	
	/**
	 * Testing the hasSouth() method in case it should be false.
	 */
	@Test
	public void hasSouthFalse() {
		coord1 = new Coordinate(0,7);
		assertEquals(false, coord1.hasSouth());
	}
	
	/**
	 * Testing the hasWest() method in case it should be false.
	 */
	@Test
	public void hasWestFalse() {
		coord1 = new Coordinate(0,7);
		assertEquals(false, coord1.hasWest());
	}
	
	/**
	 * Testing the hasNorth() method in case it should be true.
	 */
	@Test
	public void hasNorthTrue() {
		coord1 = new Coordinate(0,7);
		assertEquals(true, coord1.hasNorth());
	}
	
	/**
	 * Testing the hasEast() method in case it should be true.
	 */
	@Test
	public void hasEastTrue() {
		coord1 = new Coordinate(0,7);
		assertEquals(true, coord1.hasEast());
	}
	
	/**
	 * Testing the hasSouth() method in case it should be true.
	 */
	@Test
	public void hasSouthTrue() {
		coord1 = new Coordinate(7,0);
		assertEquals(true, coord1.hasSouth());
	}
	
	/**
	 * Testing the hasWest() method in case it should be true.
	 */
	@Test
	public void hasWestTrue() {
		coord1 = new Coordinate(7,0);
		assertEquals(true, coord1.hasWest());
	}
	
	/**
	 * Testing the toString method.
	 */
	@Test
	public void toStringTest() {
		String expected = "Coordinate(2,3)";
		assertEquals(expected, coord1.toString());
	}
	
	/**
	 * Testing the equals method with a non-Coordinate Object.
	 */
	@Test
	public void equalsNoCoordinate() {
		assertEquals(false, coord1.equals(2));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different X and same Y.
	 */
	@Test
	public void equalsDifferentXCoordinate() {
		assertEquals(false, coord1.equals(new Coordinate(3,3)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different Y and same X.
	 */
	@Test
	public void equalsDifferentYCoordinate() {
		assertEquals(false, coord1.equals(new Coordinate(2,2)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with a different X and Y.
	 */
	@Test
	public void equalsDifferentCoordinates() {
		assertEquals(false, coord1.equals(new Coordinate(6,6)));
	}
	
	/**
	 * Testing the equals method with a Coordinate with the same X and Y.
	 */
	@Test
	public void equalsSameCoordinates() {
		assertEquals(true, coord1.equals(new Coordinate(2,3)));
	}
	
}

