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
	private JLabel nextLevelLabel;
	private JLabel nextScoreLabel;
	
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
		nextLevelLabel = new JLabel(
				"<html><font size=\"6\">Points needed for next level: </font></html>", 
				JLabel.RIGHT);
		nextScoreLabel = new JLabel("<html><font size=\"15\">" + Integer.toString(1000) 
				+ "</font></html>", JLabel.CENTER);
		setLayout(new GridLayout(2,2));
		
		add(levelLabel);
		add(scoreLabel);
		add(nextLevelLabel);
		add(nextScoreLabel);
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
	
	public JLabel getNextLevelLabel() {
		return nextLevelLabel;
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
	
	public void nextLevelChanged(int nextLevelPoints) {
		nextScoreLabel.setText("<html><font size=\"12\">" + nextLevelPoints + "</font></html>");
	}
}
