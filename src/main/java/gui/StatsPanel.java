package gui;

import board.StatsListener;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel implements StatsListener {

	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;
	private JLabel levelLabel;
	private JLabel timeLabel;
	private JLabel nextScoreLabel;
	
	/**
	 * The constructor for the ScoreBoard.
	 */
	public StatsPanel(int level, int score, int time, int goalscore) {
		super();
		levelLabel = new JLabel("<html><font size=\"15\">Level " + level 
				+ "</font></html>", JLabel.CENTER);
		scoreLabel = new JLabel("<html><font size=\"15\">Score " + score
				+ "</font></html>", JLabel.CENTER);
		timeLabel = new JLabel("<html><font size=\"15\">Time " + time
				+ "</font></html>", JLabel.CENTER);
		nextScoreLabel = new JLabel("<html><font size=\"15\">Goal " + goalscore 
				+ "</font></html>", JLabel.CENTER);
		setLayout(new GridLayout(2,2));
		
		add(levelLabel);
		add(scoreLabel);
		add(timeLabel);
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
	
	public void timeLeftChanged(int time) {
		timeLabel.setText("<html><font size=\"15\">Time " + time
				+ "</font></html>");
	}
}
