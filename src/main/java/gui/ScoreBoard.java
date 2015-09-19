package gui;

import board.StatsListener;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel implements StatsListener {

	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;
	private JLabel levelLabel;
	
	/**
	 * The constructor for the ScoreBoard.
	 */
	public ScoreBoard() {
		super();
		int score = 0;
		int level = 1;
		levelLabel = new JLabel("<html><font size=\"15\">Level " + level 
				+ "</font></html>", JLabel.CENTER);
		scoreLabel = new JLabel("<html><font size=\"15\">Score " + score
				+ "</font></html>", JLabel.CENTER);
		
		setLayout(new GridLayout(2,1));
		add(levelLabel);
		add(scoreLabel);
	}
	
	/**
	 * Gets the scoreLabel of the score board.
	 * 
	 * @return JLabel
	 */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	
	/**
	 * Gets the levelLabel of the score board.
	 * 
	 * @return JLabel
	 */
	public JLabel getLevelLabel() {
		return levelLabel;
	}

	/**
	 * Updates the score.
	 * 
	 * @param score
	 *     (int) New score.
	 */
	public void scoreChanged(int score) {
		scoreLabel.setText("<html><font size=\"15\">Score " + score + "</font></html>");
	}

	/**
	 * Updates the level.
	 * 
	 * @param level
	 *     (int) New level
	 */
	public void levelChanged(int level) {
		levelLabel.setText("<html><font size=\"15\">Level " + level  + "</font></html>");
		
	}
}
