package board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatchTest {
	
	private Board board = new Board();
	private Match match = new Match(board);
	
	/**
	 * Testing the getCoordinates method with an empty list.
	 */
	@Test
	public void getCoordinatesEmptyList() {
		assertEquals(0, match.getMatchComponents().size());
	}
	
	/**
	 * Testing the addCoordinates method to see if the size of the list increases.
	 */
	@Test
	public void addCoordinates() {
		match.add(new Coordinate(0,0));
		assertEquals(1, match.getMatchComponents().size());
	}
	
	/**
	 * Testing the get() method.
	 */
	@Test
	public void get() {
		Coordinate expected = new Coordinate(0,0);
		match.add(expected);
		assertEquals(expected, match.getChild(0));
	}
	
	/**
	 * Testing the size() method.
	 */
	@Test
	public void size() {
		match.add(new Coordinate(0,0));
		assertEquals(1, match.size());
	}
	
	/**
	 * Testing the getPoints() method with a 3 match.
	 */
	@Test
	public void getPoints3Match() {
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		assertEquals(50, match.getPoints());
	}
	
	/**
	 * Testing the getPoints() method with a 4 match.
	 */
	@Test
	public void getPoints4Match() {
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
	public void getPoints5Match() {
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(0,1));
		match.add(new Coordinate(0,2));
		match.add(new Coordinate(0,3));
		match.add(new Coordinate(0,4));
		assertEquals(500, match.getPoints());
	}
	
	@Test
	public void getLMatch() {
		Match innerMatch = new Match(board);
		innerMatch.add(new Coordinate(0,0));
		innerMatch.add(new Coordinate(0,1));
		innerMatch.add(new Coordinate(0,2));
		match.add(innerMatch);
		match.add(new Coordinate(0,0));
		match.add(new Coordinate(1,0));
		match.add(new Coordinate(2,0));
		assertEquals(150, match.getPoints());
	}
	
	/**
	 * Testing the equals(Object other) method with other being a non-Match Object.
	 */
	@Test
	public void equalsDiffertentObject() {
		assertEquals(false, match.equals(new Coordinate(0,0)));
	}
	
	/**
	 * Testing the equals(Object other) method with other being a different sized match.
	 */
	@Test
	public void equalsDifferentSize() {
		match.add(new Coordinate(0,0));
		assertEquals(false, match.equals(new Match(board)));
	}
	
	/**
	 * Testing the equals(Object other) method with other being
	 * a same sized match with different elements.
	 */
	@Test
	public void equalsDifferentElements() {
		match.add(new Coordinate(0,0));
		Match other = new Match(board);
		other.add(new Coordinate(1,1));
		assertEquals(false, match.equals(other));
	}
	
	/**
	 * Testing the equals(Object other) method with other being
	 * a same sized match with the same elements in a different order.
	 */
	@Test
	public void equalsDifferentOrder() {
		Match other = new Match(board);
		Coordinate c1 = new Coordinate(0,0);
		Coordinate c2 = new Coordinate(1,1);
		
		match.add(c1);
		match.add(c2);
		other.add(c2);
		other.add(c1);
		
		assertEquals(true, match.equals(other));
	}
}
