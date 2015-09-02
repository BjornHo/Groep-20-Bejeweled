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
				pane.add(allButtons[t][y], c);
				
			}
		}
		add(pane);
	}
}
