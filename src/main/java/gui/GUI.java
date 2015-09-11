package gui;
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

import board.Board;
import board.BoardListener;
import board.Coordinate;
import jewel.Colour;


public class GUI extends JFrame implements ActionListener, BoardListener {
	private GridBagLayout k = new GridBagLayout();
	private JPanel pane = new JPanel(k);
	private JButton[][] allButtons = new JButton[8][8];
	private BackgroundPanel bgPanel;
	private Image bgImage;
	
	private Board board;
	private static IMGLoader imgloader;
	public static SoundLoader soundloader;
	
	/**
	 * Main method so we can checkout what the GUI looks like.
	 * @param args
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		GUI g = new GUI(new Board());
		g.setVisible(true);
		
	}
	
	/**
	 * Constructor for the GUI.
	 */
	public GUI(Board b) throws IOException, LineUnavailableException, UnsupportedAudioFileException{
		board = b;
		imgloader = new IMGLoader();
		soundloader = new SoundLoader();
		setSize(700,700);
		setResizable(false);
		bgPanel = new BackgroundPanel(getBackgroundImage());
		add(bgPanel, BorderLayout.CENTER);
		createButtons();
		createGridPane();
		ScoreBoard sc = createScoreBoard();
		bgPanel.add(sc, BorderLayout.NORTH);
		board.addBoardListener(this);
		board.addStatsListener(sc);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	
	}
	
	/**
	 * Gets the background image for the GUI.
	 * @return Image - the background image.
	 */
	private Image getBackgroundImage(){
		try {
			bgImage = ImageIO.read(new File("src/bejeweled background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bgImage;
	}
	
	/**
	 * Creates the score board for the GUI.
	 * Has it's own method in case we make the ScoreBoard more complex.
	 */
	private ScoreBoard createScoreBoard(){
		return new ScoreBoard();
	}
	
	/**
	 * Creates the pane with the button grid for the GUI.
	 */
	private void createGridPane(){
		pane.setLayout(k);
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.BOTH;
    	c.insets = new Insets(5,5,5,5);
		
		for(int y = 0; y < 8; y++){
			c.gridy = y;
			for(int x = 0; x < 8; x++){
				c.gridx = x;
				allButtons[y][x].setPreferredSize(new Dimension(70,70));
				setJewelImage(x,y);
				pane.add(allButtons[y][x], c);
				
			}
		}
		bgPanel.add(pane);
	}
	
	/**
	 * Creates all the buttons and puts them into the button array for the GUI.
	 */
	private void createButtons(){
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				allButtons[x][y] = new JButton();
				allButtons[x][y].addActionListener(this);
				allButtons[x][y].setOpaque(false);
				allButtons[x][y].setContentAreaFilled(false);
				allButtons[x][y].setBorderPainted(false);
			}
		}
	}
	
	/**
	 * Determining which button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				if(e.getSource().equals(allButtons[y][x])){
					Coordinate c = new Coordinate(x,y);
					board.processJewel(c);
					return;
				}
			}
		}
	}
	
	/**
	 * highLightJewel draws 2 images on top of each other. The colour of the jewel and the selectedImage border.
	 * @param x the X coordinate of the jewel on the board.
	 * @param y the Y coordinate of the jewel on the board.
	 */
	public void highLightJewel(int x, int y) {

		Colour colour = board.getJewel(new Coordinate(x, y)).colour;
		BufferedImage jewelImage = imgloader.getImage(colour);
		BufferedImage selectedImage= imgloader.getImage(Colour.Selected);
		
		int width = Math.max(jewelImage.getWidth(), selectedImage.getWidth());
		int height = Math.max(jewelImage.getHeight(), selectedImage.getHeight());
		
		BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = combinedImage.getGraphics();
		graphics.drawImage(jewelImage, 0, 0, null);
		graphics.drawImage(selectedImage, 0, 0, null);
		ImageIcon combinedIcon = new ImageIcon(combinedImage);
		allButtons[y][x].setIcon(combinedIcon);
	}
	
	/**
	 * setJewelImage sets the icon on coordinate (x,y) to the current jewel on that coordinate
	 * @param x the X coordinate of the jewel on the board.
	 * @param y the Y coordinate of the jewel on the board.
	 */
	public void setJewelImage(int x, int y){
		BufferedImage img = imgloader.getImage(board.getJewel(new Coordinate(x,y)).colour);
		ImageIcon icon = new ImageIcon(img);
		allButtons[y][x].setIcon(icon);
		System.out.println(board.getJewel(new Coordinate(x,y)).colour + " was put on coordinate (y,x) -> (" + y + "," + x + ")");
	}

	public void jewelsSwapped(Coordinate a, Coordinate b) {
		setJewelImage(a.getX(), a.getY());
		setJewelImage(b.getX(), b.getY());
	}

	public void boardChanged() {
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				setJewelImage(x, y);
			}
		}
		
			try {
				GUI.soundloader.playSound(Sounds.Match);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void jewelSelected(Coordinate c, Coordinate old) {
		highLightJewel(c.getX(), c.getY());
		if(old != null)
			setJewelImage(old.getX(), old.getY());
		
	}
}
