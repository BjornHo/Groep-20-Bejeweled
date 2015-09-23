 package xmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import game.Game;

public class XmlParser {
	
	public void writeGame(String filename) throws JAXBException {
		File file = new File(filename);
		file.delete();
		Game game = new Game();
		JAXBContext context = JAXBContext.newInstance(Game.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Write to System.out
	    m.marshal(game, System.out);
	    m.marshal(game, file);
	}
	
	public Game readGame(String filename) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Game.class);
	    Unmarshaller um = context.createUnmarshaller();
	    Game res = (Game) um.unmarshal(new FileReader(new File(filename)));
		return res;
	}
	
	//For testing/debugging purposes only, will be removed.
	public static void main( String args[] ) throws JAXBException, FileNotFoundException {
		XmlParser x = new XmlParser();
		x.writeGame("savegame/savegame.xml");
		Game res = x.readGame("savegame/savegame.xml");
		System.out.println("score: " + res.getScore());
	}
	
}
