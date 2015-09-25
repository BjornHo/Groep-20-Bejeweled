package xmlparser;

import static org.junit.Assert.*;

import java.io.File;


import org.junit.Before;
import org.junit.Test;


import board.Board;
import board.Coordinate;
import game.Game;
import jewel.Colour;

public class ReadGameTest {
	
	XmlParser xmlParser;
	String xmlPath;
	Game loadGame;
	
	@Before
	public void before() {
		xmlParser = new XmlParser();
		xmlPath = (System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "test" + File.separator
				+ "java" + File.separator + "xmlparser" + File.separator + "LoadGameTest.xml");
		loadGame = xmlParser.readGame(xmlPath);
	}
	
	@Test
	public void invalidFileNewGameTest() {
		String noFileName = "";
		Game newGame = xmlParser.readGame(noFileName);
		assertEquals(0, newGame.getScore());
	}
	
	@Test
	public void invalidFileNewGameTest2() {
		String noFileName = "";
		Game newGame = xmlParser.readGame(noFileName);
		assertEquals(1, newGame.getLevel());
	}
	
	@Test
	public void nullFileNewGameTest() {
		String noFileName = null;
		Game newGame = xmlParser.readGame(noFileName);
		assertEquals(0, newGame.getScore());
	}
	
	@Test
	public void nullFileNewGameTest2() {
		String noFileName = null;
		Game newGame = xmlParser.readGame(noFileName);
		assertEquals(1, newGame.getLevel());
	}
	
	@Test
	public void loadScoreTest() {
		assertEquals(3000, loadGame.getScore());
	}
	
	@Test
	public void loadLevelTest() {
		assertEquals(10, loadGame.getLevel());
	}
	
	@Test
	public void loadJewelTest() {
		Board board = loadGame.getBoard();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				assertEquals(board.getJewel(new Coordinate(x, y)).colour, Colour.Blue);
				
			}
		}
	}
	
	

}
