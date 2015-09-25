package gui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import board.Board;
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
	
	@Test
	public void SaveGameTest () {
		XmlParser parser = new XmlParser();
		parser.writeGame(game, "Testsavegame/Testsave");
		
		
	}
}
