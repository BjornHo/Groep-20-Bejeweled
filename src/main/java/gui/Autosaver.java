package gui;

import game.Game;
import xmlparser.XmlParser;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Autosaver implements WindowListener {

	private Game game;
	
	public Autosaver(Game game) {
		super();
		this.game = game;
	}
	
	@Override
	public void windowActivated(WindowEvent event) {
		
	}

	@Override
	public void windowClosed(WindowEvent event) {
		
	}

	@Override
	public void windowClosing(WindowEvent event) {
		XmlParser parser = new XmlParser();
		parser.writeGame(game, "savegame/Autosave.xml");
	}

	@Override
	public void windowDeactivated(WindowEvent event) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent event) {
		
	}

	@Override
	public void windowIconified(WindowEvent event) {
		
	}

	@Override
	public void windowOpened(WindowEvent event) {
		
	}

}
