import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class IMGLoader {
	private HashMap<Jewel.Colour, BufferedImage> imagemap;
	
	
	/**
	 * This IMGLoader is to load every jewel image at once into an hashmap. Every colour of a jewel is
	 * associated with the right image.
	 * @throws IOException
	 */
	public IMGLoader() throws IOException {
		BufferedImage gemImage;
		imagemap = new HashMap<Jewel.Colour, BufferedImage>(8);
		
		String directory = (System.getProperty("user.dir") + File.separator+ "src" + File.separator + "Jewelimg"); 
		
		File folder = new File(directory);
		File[] allFiles = folder.listFiles();
		for(File file : allFiles) {
			int underScorePos = file.getName().indexOf("_");
			gemImage = ImageIO.read(file);
			imagemap.put(Jewel.Colour.valueOf(file.getName().substring(0, underScorePos)), gemImage);
		}
	}
	
	/**
	 * getImage method is to get the right image for a jewel.
	 * @param colour is the colour of the jewel
	 * @return returns the image of the jewel.
	 */
	public BufferedImage getImage(Jewel.Colour colour) {
		return imagemap.get(colour);
	}
	
}


