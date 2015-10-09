package gui;

import observers.StatsObserver;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel implements StatsObserver {

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

	@Override
	public void scoreChanged(int score) {
		scoreLabel.setText("<html><font size=\"15\">Score " + score + "</font></html>");
	}
	
	@Override
	public void goalScoreChanged(int goalscore) {
		nextScoreLabel.setText("<html><font size=\"15\">Goal " + goalscore + "</font></html>");
	}

	@Override
	public void levelChanged(int level) {
		levelLabel.setText("<html><font size=\"15\">Level " + level  + "</font></html>");
	}
	
	@Override
	public void timeLeftChanged(int time) {
		timeLabel.setText("<html><font size=\"15\">Time " + time
				+ "</font></html>");
	}
}
