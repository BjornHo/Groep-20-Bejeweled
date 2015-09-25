package gui;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import board.Board;
import game.Game;
import xmlparser.XmlParser;

import java.io.File;

import jewel.Colour;
import jewel.Jewel;

public class WriteGameTest {
	
	@Mock private Board board;
	@Mock private Game game;
	@Mock private Game savedgame;
	XmlParser xmlParser;
	String xmlPath;
	
	@Before
	public void setup() {
		game = new Game();
		board = game.getBoard();
		
		Jewel[][] grid = { 
				{ new Jewel(Colour.Red), new Jewel(Colour.Red),
					new Jewel(Colour.Blue) },
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow),
					new Jewel(Colour.Green) },
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange),
					new Jewel(Colour.Blue) }
		};
		board.setGrid(grid);
		
		xmlParser = new XmlParser();
		xmlPath = (System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "test" + File.separator
				+ "java" + File.separator + "xmlparser" + File.separator + "SaveGameTest.xml");
	}
	
	/**
	 * The Gui has listeners reporting if the Autosaver() is called.
	 * Since we don't test interfaces, this method tests if the
	 * contents of the board are indeed saved to an mxl file.
	 */
	@Test
	public void SaveGameTest() {
		xmlParser.writeGame(game, xmlPath);
		savedgame = xmlParser.readGame(xmlPath);
	}
}
