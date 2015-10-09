package observers;

public interface StatsListener {
	
	public void scoreChanged(int score);
	
	public void levelChanged(int level);
	
	public void timeLeftChanged(int time);
	
	public void goalScoreChanged(int goalScore);
}
