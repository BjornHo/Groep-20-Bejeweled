package observers;

public interface StatsObserver {
	
	public void scoreChanged(int score);
	
	public void levelChanged(int level);
	
	public void timeLeftChanged(int time);
	
	public void goalScoreChanged(int goalScore);
}
