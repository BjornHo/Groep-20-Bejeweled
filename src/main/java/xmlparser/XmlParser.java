 package xmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import board.Coordinate;
import game.Game;

public class XmlParser {
	
<<<<<<< HEAD
<<<<<<< HEAD
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public XmlParser() {
		try {
			context = JAXBContext.newInstance(Game.class);
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Writes the XML-representation of Game "game" to the file with the given
	 * filename.
	 * 
	 * @param game
	 *            The Game object which is written.
	 * @param filename
	 *            The name of the file to which is written.
	 */
	public void writeGame(Game game, String filename) {
		File file = new File(filename);

		try {
			marshaller.marshal(game, file);
		} catch (JAXBException e) {
			System.err.println("Savegame file " + filename + " could not be written.");
		}

=======
=======
>>>>>>> f64a9fa6e675b77ae9b9d77a29176c9b97801b29
	public void writeGame(String filename, Game game) throws JAXBException {
		File file = new File(filename);
		file.delete();
		JAXBContext context = JAXBContext.newInstance(Game.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Write to System.out
	    m.marshal(game, System.out);
	    m.marshal(game, file);
>>>>>>> Working autosaver
	}
	
	/**
	 * Creates a Game object based on it's XML-representation in the given file.
	 * When the file could not be found or an error occured while reading the
	 * file, a new Game is returned instead.
	 * 
	 * @param filename
	 *            Name of the file which is read.
	 * @return The read (or a new) Game object.
	 */
	public Game readGame(String filename) {
		Game res = null;
		try {
			res = (Game) unmarshaller.unmarshal(new FileReader(new File(
					filename)));
		} catch (FileNotFoundException e) {
			System.err.println("Savegame file " + filename
					+ " not found. Creating new game instead.");
			res = new Game();
		} catch (JAXBException e) {
			System.err.println("Error while reading savegame file " + filename
					+ ". Creating new game instead.");
			res = new Game();
		}
		return res;
	}
	
<<<<<<< HEAD
=======
	//For testing/debugging purposes only, will be removed.
	public static void main( String args[] ) throws JAXBException, FileNotFoundException {
		XmlParser x = new XmlParser();
		x.writeGame("savegame/savegame.xml", new Game());
		Game res = x.readGame("savegame/savegame.xml");
		System.out.println("score: " + res.getScore());
	}
	
>>>>>>> Working autosaver
}
