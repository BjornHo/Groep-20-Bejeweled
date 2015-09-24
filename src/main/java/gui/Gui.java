package gui;

import board.BoardListener;
import board.Coordinate;
import game.Game;
import jewel.Colour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class handles the graphical representation of our Bejeweled game.
 * 
 * @author Group 20
 */
public class Gui extends JFrame implements ActionListener, BoardListener {
	
	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gbl = new GridBagLayout();
	private JPanel pane = new JPanel(gbl);
	private JButton[][] allButtons = new JButton[8][8];
	private BackgroundPanel bgPanel;
	private Image bgImage;
	
	private Game game;
	private static ImgLoader imgloader;
	public static SoundLoader soundloader;
	
	/**
	 * Main method used to verify what the GUI looks like.
	 * 
	 * @param args
	 *     Input Array of Strings (for cmd line).
	 * @throws UnsupportedAudioFileException
	 *     If type of audio file is not supported.
	 * @throws LineUnavailableException
	 *     If an audio line cannot be opened because it is unavailable.
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException,
		UnsupportedAudioFileException {
		Gui gui = new Gui(new Game());
		gui.setVisible(true);
		
	}
	
	/**
	 * GUI Constructor method.
	 * 
	 * @param board
	 *     Board Object the GUI will make a graphical interface for.
	 */
	public Gui(Game game) throws IOException, LineUnavailableException,
		UnsupportedAudioFileException {
		this.game = game;
		this.addWindowListener(new Autosaver(this.game));
		imgloader = new ImgLoader();
		soundloader = new SoundLoader();
		setSize(800,800);
		setResizable(false);
		bgPanel = new BackgroundPanel(getBackgroundImage());
		add(bgPanel, BorderLayout.CENTER);
		createButtons();
		createGridPane();
		ScoreBoard sc = createScoreBoard();
		bgPanel.add(sc, BorderLayout.NORTH);
		this.game.getBoard().addBoardListener(this);
		this.game.addStatsListener(sc);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Gets the background image for the GUI.
	 * 
	 * @return Image
	 *     The background image.
	 */
	private Image getBackgroundImage() {
		try {
			bgImage = ImageIO.read(new File((System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "background.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bgImage;
	}
	
	/**
	 * Creates the score board for the GUI.
	 * Has it's own method in case we make the ScoreBoard more complex.
	 */
	private ScoreBoard createScoreBoard() {
		return new ScoreBoard();
	}
	
	/**
	 * Creates the pane with the button grid for the GUI.
	 */
	private void createGridPane() {
		pane.setLayout(gbl);
    	GridBagConstraints constraint = new GridBagConstraints();
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(5,5,5,5);
		
		for (int y = 0; y < 8; y++) {
			constraint.gridy = y;
			for (int x = 0; x < 8; x++) {
				constraint.gridx = x;
				allButtons[y][x].setPreferredSize(new Dimension(70,70));
				setJewelImage(x,y);
				pane.add(allButtons[y][x], constraint);
				
			}
		}
		bgPanel.add(pane);
	}
	
	/**
	 * Creates all the buttons and puts them into the button array for the GUI.
	 */
	private void createButtons() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				allButtons[x][y] = new JButton();
				allButtons[x][y].addActionListener(this);
				allButtons[x][y].setOpaque(false);
				allButtons[x][y].setContentAreaFilled(false);
				allButtons[x][y].setBorderPainted(false);
			}
		}
	}
	
	/**
	 * Determining which button is pressed.
	 * 
	 * @param event
	 *     ActionEvent to process.
	 */
	public void actionPerformed(ActionEvent event) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (event.getSource().equals(allButtons[y][x])) {
					Coordinate coord = new Coordinate(x,y);
					game.processJewel(coord);
					return;
				}
			}
		}
	}
	
	/**
	 * Draws 2 images on top of each other; the colour of the jewel and
	 * the selectedImage border.
	 * 
	 * @param xvalue 
	 *     The x-coordinate of the jewel on the board.
	 * @param yvalue t
	 *     The y-coordinate of the jewel on the board.
	 */
	public void highLightJewel(int xvalue, int yvalue) {
		Colour colour = game.getBoard().getJewel(new Coordinate(xvalue, yvalue)).colour;
		BufferedImage jewelImage = imgloader.getImage(colour);
		BufferedImage selectedImage = imgloader.getImage(Colour.Selected);
		
		int width = Math.max(jewelImage.getWidth(), selectedImage.getWidth());
		int height = Math.max(jewelImage.getHeight(), selectedImage.getHeight());
		
		BufferedImage combImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = combImage.getGraphics();
		graphics.drawImage(jewelImage, 0, 0, null);
		graphics.drawImage(selectedImage, 0, 0, null);
		ImageIcon combinedIcon = new ImageIcon(combImage);
		allButtons[yvalue][xvalue].setIcon(combinedIcon);
	}
	
	/**
	 * setJewelImage sets the icon on coordinate (x,y) to the current jewel on that coordinate
	 * 
	 * @param xvalue
	 *     The x-coordinate of the jewel on the board.
	 * @param yvalue
	 *     The y-coordinate of the jewel on the board.
	 */
	public void setJewelImage(int xvalue, int yvalue) {
		BufferedImage img = imgloader.getImage(game.getBoard().getJewel(
				new Coordinate(xvalue,yvalue)).colour);
		ImageIcon icon = new ImageIcon(img);
		allButtons[yvalue][xvalue].setIcon(icon);
	}

	public void jewelsSwapped(Coordinate acoord, Coordinate bcoord) {
		setJewelImage(acoord.getX(), acoord.getY());
		setJewelImage(bcoord.getX(), bcoord.getY());
	}

	public void boardChanged() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				setJewelImage(x, y);
			}
		}
			try {
				Gui.soundloader.playSound(Sounds.Match);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
	}

	public void jewelSelected(Coordinate coord, Coordinate old) {
		highLightJewel(coord.getX(), coord.getY());
		if (old != null) {
			setJewelImage(old.getX(), old.getY());
		}
	}
}
