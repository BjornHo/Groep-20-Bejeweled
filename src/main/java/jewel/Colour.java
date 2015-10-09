package jewel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum Colour { 
	Blue, Green, Orange, Purple, Red, White, Yellow, Empty, Selected;
	
	private static final List<Colour> COLOURS = 
			Collections.unmodifiableList(Arrays.asList(values()));
	
	private static final int SIZE = COLOURS.size() - 2;
	
	private static final Random RANDOM = new Random();
	
	/**
	 * Returns a random Colour, in order to create random new Jewels after matches.
	 * 
	 * @return Colour
	 *     Colour of a Jewel Object.
	 */
	public static Colour randomColour() {
		return COLOURS.get(RANDOM.nextInt(SIZE));
	}
}