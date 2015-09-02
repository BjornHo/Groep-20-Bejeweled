import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class GUI extends JFrame{
	GridBagLayout k = new GridBagLayout();
	JPanel pane = new JPanel(k);
	JPanel jp = new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUI g = new GUI();
		g.setVisible(true);
		
	}
	
	public GUI(){
		setSize(1080,720);
		JButton[][] allButtons = new JButton[8][8];
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				allButtons[i][j] = new JButton();
		
    	pane.setLayout(k);
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.BOTH;
    	
    	Insets def = new Insets(5,5,5,5);
		
		for(int t = 0; t < 8; t++){
			c.gridx = t;
			for(int y = 0; y < 8; y++){
				c.gridy = y;
				allButtons[t][y].setPreferredSize(new Dimension(70,70));
				setButtonColor(allButtons[t][y]);
				pane.add(allButtons[t][y], c);
				
			}
		}
		add(pane);
	}
	
	public void setButtonColor(JButton button){
		int rand = 1 + (int)(Math.random()*7); 
		switch(rand){
		case 1 : button.setBackground(Color.blue);
		case 2 : button.setBackground(Color.green);
		case 3 : button.setBackground(Color.ORANGE);
		case 4 : button.setBackground(Color.yellow);
		case 5 : button.setBackground(Color.pink);
		case 6 : button.setBackground(Color.magenta);
		case 7 : button.setBackground(Color.yellow);
		}
	}
}
