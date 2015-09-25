package jewel;

import static org.junit.Assert.assertEquals;

import jewel.Colour;
import jewel.Jewel;

import org.junit.Before;
import org.junit.Test;

public class JewelTest {

	private Jewel jewel;
	
	@Before
	public void before() {	
		jewel = new Jewel(Colour.Red);
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
		Jewel compared = new Jewel(Colour.Red);
		assertEquals(true, jewel.isSameColour(compared));
	}
	
	/**
	 * Testing the isSameColour method with a jewel of a different Colour.
	 */
	@Test
	public void isSameColourFalse() {
		Jewel compared = new Jewel(Colour.Blue);
		assertEquals(false, jewel.isSameColour(compared));
	}
}
