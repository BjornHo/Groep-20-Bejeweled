package gui;

import jewel.Colour;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
//
/**
 * Class that loads Image files to use them as Jewel images.
 * 
 * @author Group 20
 */
public class ImgLoader {
	
	private HashMap<Colour, ImageIcon> imagemap;
	
	/**
	 * This ImgLoader is to load every jewel image at once into a hash map.
	 * Every Colour of a jewel is associated with the right image.
	 * 
	 * @throws IOException
	 *     If the input/output is incorrect.
	 */
	public ImgLoader() throws IOException {
		imagemap = new HashMap<Colour, ImageIcon>(64);
		
		String directory = (System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "main" + File.separator
				+ "java" + File.separator + "Jewelimg"); 
		
		File folder = new File(directory);
		File[] allFiles = folder.listFiles();
		for (File file : allFiles) {
			int underScorePos = file.getName().indexOf("_");
			ImageIcon icon = new ImageIcon(file.getPath());
			imagemap.put(Colour.valueOf(file.getName().substring(0, underScorePos)),
					icon);
		}
		
	}
	
	/**
	 * Method to get an Image for a jewel by associating its Colour.
	 * 
	 * @param colour
	 *     The Colour of the jewel
	 * @return BufferedImage
	 *     The image of the jewel.
	 */
	public ImageIcon getImage(Colour colour) {
		return imagemap.get(colour);
	}
}


