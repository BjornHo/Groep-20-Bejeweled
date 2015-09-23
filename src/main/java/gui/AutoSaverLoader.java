package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.xml.bind.JAXBException;

import xmlparser.XmlParser;

public class AutoSaverLoader implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		XmlParser parser = new XmlParser();
		try {
			parser.writeGame("Temp File Name");
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
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
		//This is where the level would get loaded.
	}

}
