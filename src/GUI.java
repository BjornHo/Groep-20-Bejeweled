import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class GUI extends JFrame{
	private GridBagLayout k = new GridBagLayout();
	private JPanel pane = new JPanel(k);
	private JPanel jp = new JPanel();
	private JButton[][] allButtons = new JButton[8][8];
	
	/**
	 * Main method so we can checkout what the GUI looks like.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUI g = new GUI();
		g.setVisible(true);
		
	}
	
	/**
	 * Constructor for the GUI.
	 */
	public GUI(){
		setSize(720,720);
		setResizable(false);
		createButtons();
		createGridPane();
    	
	}
	
	/**
	 * Creates the score board for the GUI.
	 */
	private void createScoreBoard(){
		
	}
	
	/**
	 * Creates the pane with the button grid for the GUI.
	 */
	private void createGridPane(){
		pane.setLayout(k);
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.BOTH;
    	
    	Insets def = new Insets(5,5,5,5);
		
		for(int x = 0; x < 8; x++){
			c.gridx = x;
			for(int y = 0; y < 8; y++){
				c.gridy = y;
				allButtons[x][y].setPreferredSize(new Dimension(70,70));
				setButtonColor(allButtons[x][y]);
				pane.add(allButtons[x][y], c);
				
			}
		}
		add(pane);
	}
	
	/**
	 * Creates all the buttons and puts them into the button array for the GUI.
	 */
	private void createButtons(){
		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++)
				allButtons[x][y] = new JButton();
	}
	
	/**
	 * Makes the buttons super pretty and beautiful.
	 * @param button
	 */
	private void setButtonColor(JButton button){
		int rand = 1 + (int)(Math.random()*7); 
		switch(rand){
		case 1 : button.setBackground(Color.blue);
				 break;
		case 2 : button.setBackground(Color.green);
		 		 break;
		case 3 : button.setBackground(Color.ORANGE);
		 		 break;
		case 4 : button.setBackground(Color.yellow);
		 		 break;
		case 5 : button.setBackground(Color.pink);
		 		 break;
		case 6 : button.setBackground(Color.magenta);
		 		 break;
		case 7 : button.setBackground(Color.yellow);
		 		 break;
		}
	}
}
