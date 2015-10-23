package xmlparser;

import static org.junit.Assert.assertEquals;

import board.Board;
import board.Coordinate;
import game.Game;
import jewel.Colour;
import jewel.Jewel;
import jewel.NormalJewel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WriteGameTest {
	@Mock private Board board;
	@Mock private Game game;
	@Mock private Game savedgame;
	XmlParser xmlParser;
	String xmlPath;
	
	/**
	 * The Gui has listeners reporting if the Autosaver() is called.
	 * Since we don't test interfaces, this method tests if the
	 * contents of the board are indeed saved to an mxl file.
	 */
	@Before
	public void setup() {
		game = new Game();
		board = game.getBoard();
		
		Jewel[][] grid = { 
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.White),
					new NormalJewel(Colour.Blue) },
				{ new NormalJewel(Colour.Orange), new NormalJewel(Colour.Yellow),
					new NormalJewel(Colour.Green) },
				{ new NormalJewel(Colour.Red), new NormalJewel(Colour.Orange),
					new NormalJewel(Colour.Purple) }
		};
		board.setGrid(grid);
		
		xmlParser = new XmlParser();
		xmlPath = (System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "test" + File.separator
				+ "java" + File.separator + "xmlparser" + File.separator + "SaveGameTest.xml");
		
		xmlParser.writeGame(game, xmlPath);
		savedgame = xmlParser.readGame(xmlPath);
	}
	
	@Test
	public void XmlWritingTest() throws IOException {
		File savefile = new File(xmlPath);
		BufferedReader reader = new BufferedReader(new FileReader(savefile));
		
		reader.readLine();
		assertEquals(reader.readLine(), "<game>");
		assertEquals(reader.readLine(), "    <board>");
		assertEquals(reader.readLine(), "        <grid>");
		assertEquals(reader.readLine(), "            <row>");
		assertEquals(reader.readLine(), "                <item xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"normalJewel\">");
		assertEquals(reader.readLine(), "                    <colour>Red</colour>");
		assertEquals(reader.readLine(), "                </item>");
		assertEquals(reader.readLine(), "                <item xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"normalJewel\">");
		assertEquals(reader.readLine(), "                    <colour>White</colour>");
		assertEquals(reader.readLine(), "                </item>");
		assertEquals(reader.readLine(), "                <item xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"normalJewel\">");
		assertEquals(reader.readLine(), "                    <colour>Blue</colour>");
		assertEquals(reader.readLine(), "                </item>");
		reader.close();
	}
	
	@Test
	public void SaveGameTestScore() {
		assertEquals(0, savedgame.getScore());
	}
	
	@Test
	public void SaveGameTestLevel() {
		assertEquals(1, savedgame.getLevel());
	}
	
	@Test
	public void SaveGameTestGrid() {
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(0, 0)).colour, Colour.Red);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(1, 0)).colour, Colour.White);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(2, 0)).colour, Colour.Blue);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(0, 1)).colour, Colour.Orange);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(1, 1)).colour, Colour.Yellow);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(2, 1)).colour, Colour.Green);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(0, 2)).colour, Colour.Red);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(1, 2)).colour, Colour.Orange);
		assertEquals(savedgame.getBoard().getJewel(new Coordinate(2, 2)).colour, Colour.Purple);
	}
}
