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
		JButton[] allButtons = new JButton[64];
		for(int i = 0; i < 64; i++){
			allButtons[i] = new JButton();
		}
		
    	pane.setLayout(k);
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.BOTH;
    	
    	Insets def = new Insets(5,5,5,5);
		
		for(int j = 0; j < 8; j++){
			c.gridx = j;
			for(int k = 0; k < 8; k++){
				c.gridy = k;
				allButtons[8*j + k].setPreferredSize(new Dimension(70,70));
				pane.add(allButtons[8*j + k], c);
				
			}
		}
		add(pane);
	}
}
