package board;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import board.Coordinate;
import board.Match;

public class MatchTest {
	
	private Match match;

	/**
	 * Initializing a match for every test
	 */
	
	@Before
	public void before(){
		match = new Match();
	}
	
	/**
	 * Testing the getCoordinates method with an empty list.
	 */
	
	@Test
	public void getCoordinatesEmptyList(){
		assertEquals(0, match.getCoordinates().size());
	}
	
	/**
	 * Testing the addCoordinates method to see if the size of the list increases.
	 */
	
	@Test
	public void addCoordinates(){
		match.add(new Coordinate(0,0));
		assertEquals(1, match.getCoordinates().size());
	}
	
	/**
	 * Testing the get() method.
	 */
	
	@Test
	public void get(){
		Coordinate expected = new Coordinate(0,0);
		match.add(expected);
		assertEquals(expected, match.get(0));
	}
	
	/**
	 * Testing the size() method
	 */
	
	@Test
	public void size(){
		match.add(new Coordinate(0,0));
		assertEquals(1, match.size());
	}
	
	/**
	 * Testing the getPoints() method with a 3 match.
	 */
	
	@Test
	public void getPoints3Match(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(50, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with a 4 match.
	 */
	
	@Test
	public void getPoints4Match(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		assertEquals(100, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with a 5 match.
	 */
	
	@Test
	public void getPoints5Match(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		match.add(new Coordinate(0,4));
		assertEquals(500, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with an invalid (no 3-, 4- or 5-) match.
	 */
	
	@Test
	public void getPointsInvalidMatch(){
		match.add(new Coordinate(0,0));
		assertEquals(-1, match.getPoints());
	}

	/**
	 * Testing the isVertical() method with a vertical match.
	 */
	
	@Test
	public void isVerticalTrue(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(true, match.isVertical());
	}
	
	/**
	 * Testing the isVertical() method with a horizontal match.
	 */
	
	@Test
	public void isVerticalFalse(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(1,0));
		match.add(new Coordinate(2,0));
		assertEquals(false, match.isVertical());
	}
	
	/**
	 * Testing the isHorizontal() method with a horizontaL match.
	 */
	
	@Test
	public void isHorizontalTrue(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(1,0));
		match.add(new Coordinate(2,0));
		assertEquals(true, match.isHorizontal());
	}
	
	/**
	 * Testing the isHorizontal() method with a vertical match.
	 */
	
	@Test
	public void isHorizontalFalse(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(false, match.isHorizontal());
	}
	
	/**
	 * Testing the getYMin() method with an empty list.
	 */
	
	@Test
	public void getYMinEmptyList(){
		assertEquals(-1, match.getYMin());
	}
	
	/**
	 * Testing the getYMin() method with a list of 3 elements.
	 */
	
	@Test
	public void getYMin3Elements(){
		match.add(new Coordinate(0,4));
		match.add(new Coordinate(1,2));
		match.add(new Coordinate(6,3));
		assertEquals(2, match.getYMin());
	}
	
	/**
	 * Testing the getYMax() method with a list of 3 elements.
	 */
	
	@Test
	public void getYMax(){
		match.add(new Coordinate(0,4));
		match.add(new Coordinate(1,2));
		match.add(new Coordinate(6,3));
		assertEquals(4, match.getYMax());
	}
	
	/**
	 * Testing the getX() method on a horizontal match.
	 */
	
	@Test
	public void getXHorizontal(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(1,0));
		match.add(new Coordinate(2,0));
		assertEquals(-1, match.getX());
	}
	
	/**
	 * Testing the getX() method on a vertical match.
	 */
	
	@Test
	public void getXVertical(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(0, match.getX());
	}
	
	/**
	 * Testing the getY() method on a horizontal match.
	 */
	
	@Test
	public void getYHorizontal(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(1,0));
		match.add(new Coordinate(2,0));
		assertEquals(0, match.getY());
	}
	
	/**
	 * Testing the getY() method on a vertical match.
	 */
	
	@Test
	public void getYVertical(){
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(-1, match.getY());
	}
	
	/**
	 * Testing the equals(Object other) method with other being a non-Match Object.
	 */
	
	@Test
	public void equalsDiffertentObject(){
		assertEquals(false, match.equals(new Coordinate(0,0)));
	}
	
	/**
	 * Testing the equals(Object other) method with other being a different sized match.
	 */
	
	@Test
	public void equalsDifferentSize(){
		match.add(new Coordinate(0,0));
		assertEquals(false, match.equals(new Match()));
	}
	
	/**
	 * Testing the equals(Object other) method with other being a same sized match with different elements.
	 */
	
	@Test
	public void equalsDifferentElements(){
		match.add(new Coordinate(0,0));
		Match other = new Match();
		other.add(new Coordinate(1,1));
		assertEquals(false, match.equals(other));
	}
	
	/**
	 * Testing the equals(Object other) method with other being a same sized match with the same elements in a different order.
	 */
	
	@Test
	public void equalsDifferentOrder(){
		Match other = new Match();
		Coordinate c1 = new Coordinate(0,0);
		Coordinate c2 = new Coordinate(1,1);
		
		match.add(c1);
		match.add(c2);
		other.add(c2);
		other.add(c1);
		
		assertEquals(true, match.equals(other));
	}
}
