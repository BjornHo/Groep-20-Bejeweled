import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel {

	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private int level;
	
	/**
	 * The constructor for the ScoreBoard.
	 */
	public ScoreBoard() {
		super();
		score = 0;
		level = 1;
		setLayout(new GridLayout(2,1));
		add(new JLabel("<html><font size=\"15\">Level " + level  + "</font></html>", JLabel.CENTER));
		add(new JLabel("<html><font size=\"15\">Score " + score + "</font></html>", JLabel.CENTER));
	}
	
	/**
	 * Refreshes the score for the player on the ScoreBoard.
	 */
	private void refreshScore() {
		
	}
	
	private void refreshLevel() {
		
	}
}
