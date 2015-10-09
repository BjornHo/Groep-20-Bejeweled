package observers;

import java.util.List;

public interface StatsObservable {

	/**
	 * Adds a StatsObserver to the Board.
	 * 
	 * @param listener
	 *     The StatsObserver to be added.
	 */
	public void addStatsObserver(StatsObserver listener);
	
	/**
	 * Returns the list of all current statsObservers.
	 */
	public List<StatsObserver> getstatsObservers();
	
	/**
	 * Notifies the StatsObserver that the score has been changed / needs updating.
	 */
	public void notifyScoreChanged();

	/**
	 * Notifies the StatsObserver that the score has been changed / needs updating.
	 */
	public void notifyGoalScoreChanged();
	
	/**
	 * Notifies the StatsObserver that the level has been changed / needs updating.
	 */
	public void notifyLevelChanged();
	
	/**
	 * Notifies the StatsObserver that the time left has been changed / needs updating.
	 */
	public void notifyTimeLeft();
	
}
