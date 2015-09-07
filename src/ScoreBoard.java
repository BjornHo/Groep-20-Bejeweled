import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel implements StatsListener {

	/**
	 * Default serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	//private int score;
	//private int level;
	private JLabel scoreLabel;
	private JLabel levelLabel;
	
	/**
	 * The constructor for the ScoreBoard.
	 */
	public ScoreBoard() {
		super();
		int score = 0;
		int level = 1;
		levelLabel = new JLabel("<html><font size=\"15\">Level " + level  + "</font></html>", JLabel.CENTER);
		scoreLabel = new JLabel("<html><font size=\"15\">Score " + score + "</font></html>", JLabel.CENTER);
		setLayout(new GridLayout(2,1));
		add(levelLabel);
		add(scoreLabel);
	}
	
	/**
	 * Gets the scoreLabel of the scoreboard.
	 * @return - JLabel scoreLabel
	 */
	public JLabel getScoreLabel(){
		return scoreLabel;
	}
	
	/**
	 * Gets the levelLabel of the scoreboard.
	 * @return JLabel levelLabel
	 */
	public JLabel getLevelLabel(){
		return levelLabel;
	}
	
//	/**
//	 * Refreshes the score for the player on the ScoreBoard.
//	 */
//	private void refreshScore() {
//		scoreLabel.setText("<html><font size=\"15\">Score " + score + "</font></html>");
//	}
//	
//	/**
//	 * Refreshes the current level of the game.
//	 */
//	private void refreshLevel() {
//		levelLabel.setText("<html><font size=\"15\">Level " + level  + "</font></html>");
//	}
//	
//	/**
//	 * Allows the level to be incremented.
//	 */
//	public void incrementLevel() {
//		level++;
//		refreshLevel();
//	}
//	
//	/**
//	 * Allows the score to be altered.
//	 * @param points
//	 */
//	public void increaseScore(int points) {
//		score += points;
//		refreshScore();
//	}

	@Override
	public void scoreChanged(int score) {
		scoreLabel.setText("<html><font size=\"15\">Score " + score + "</font></html>");
	}

	@Override
	public void levelChanged(int level) {
		levelLabel.setText("<html><font size=\"15\">Level " + level  + "</font></html>");
		
	}
}
