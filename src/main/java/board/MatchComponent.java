package board;

import java.util.List;

public abstract class MatchComponent {
	
	public void add(MatchComponent matchComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MatchComponent matchComponent) {
		throw new UnsupportedOperationException();
	}
	
	public MatchComponent getChild(int location) {
		throw new UnsupportedOperationException();
	}
	
	public int getPoints() {
		throw new UnsupportedOperationException();
	}
	
	public void clear(Board board) {
		throw new UnsupportedOperationException();
	}
	
	public void setMatchValue(Board board, int location) {
		throw new UnsupportedOperationException();
	}
	
	public int getMatchValue(Board board) {
		throw new UnsupportedOperationException();
	}
	
	public abstract void getCoordinates(List<Coordinate> coordinates);
}
