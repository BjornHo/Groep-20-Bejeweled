package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.xml.bind.JAXBException;

import game.Game;
import xmlparser.XmlParser;

public class AutoSaverLoader implements WindowListener {

	private Game game;
	
	public AutoSaverLoader(Game game) {
		super();
		this.game = game;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		XmlParser parser = new XmlParser();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		XmlParser parser = new XmlParser();
		
	}

}
