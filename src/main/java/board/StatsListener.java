package board;

public interface StatsListener {
	
	public void scoreChanged(int score);
	
	public void levelChanged(int level);
	
	public void nextLevelChanged(int nextLevelPoints);
	
	public void timeLeftChanged(int time);
}
