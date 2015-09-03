import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GUI extends JFrame implements ActionListener{
	private GridBagLayout k = new GridBagLayout();
	private JPanel pane = new JPanel(k);
	private JButton[][] allButtons = new JButton[8][8];
	private BackgroundPanel bgPanel;
	private Image bgImage;
	
	private Board board = new Board();
	private static IMGLoader imgloader;
	
	/**
	 * Main method so we can checkout what the GUI looks like.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		imgloader = new IMGLoader();
		GUI g = new GUI();
		g.setVisible(true);
		
	}
	
	/**
	 * Constructor for the GUI.
	 */
	public GUI(){
		setSize(800,800);
		setResizable(false);
		bgPanel = new BackgroundPanel(getBackgroundImage());
		add(bgPanel, BorderLayout.CENTER);
		createButtons();
		createGridPane();
		bgPanel.add(createScoreBoard(), BorderLayout.NORTH);
    	
	}
	
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
				allButtons[y][x].setBackground(Color.black);
				BufferedImage img = imgloader.getImage(board.getJewel(x,y).colour);
				ImageIcon icon = new ImageIcon(img);
				allButtons[y][x].setIcon(icon);
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
			}
		}
	}
	
	/**
	 * Determining which button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				if(e.getSource().equals(allButtons[y][x])){
					//The coordinates of the button are (x,y)
					System.out.println("(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
//					BufferedImage img = imgloader.getImage(Colour.Selected);
//					ImageIcon icon = new ImageIcon(img);
//					allButtons[y][x].setIcon(icon);
					board.selectJewel(new Coordinate(x,y));
					return;
				}
			}
		}
	}
}
