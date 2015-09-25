package gui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.File;

import game.Game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import xmlparser.XmlParser;


public class SaveGameTest {
	
	@Mock private SupportGame game;
	
	@Before
	public void setup () {
		game = new SupportGame();
	}
	
	/**
	 * The Gui has listeners reporting if the Autosaver() is called.
	 * Since we don't test interfaces, this method tests if the
	 * contents of the board are indeed saved to an mxl file.
	 */
	@Test
	public void SaveGameTest () {
		XmlParser parser = new XmlParser();
		parser.writeGame(game, "Testsavegame/Testsave");
		
		parser.readGame("Testsave");
		
		
	}
}
