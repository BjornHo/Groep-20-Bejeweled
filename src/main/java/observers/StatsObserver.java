package observers;

public interface StatsObserver {
	
	/**
	 * Updates the score.
	 * 
	 * @param score
	 *     (int) New score.
	 */
	public void scoreChanged(int score);
	
	/**
	 * Updates the level.
	 * 
	 * @param level
	 *     (int) New level
	 */
	public void levelChanged(int level);
	
	/**
	 * Update the time left.
	 * 
	 * @param time
	 * 		(int) Time left
	 */
	public void timeLeftChanged(int time);
	
	/**
	 * Update the goal score
	 * 
	 * @param goalScore
	 * 		(int) New goal score
	 */
	public void goalScoreChanged(int goalScore);
}
