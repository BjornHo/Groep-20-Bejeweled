package board;

import java.util.ArrayList;
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
	
	public void clear(Board board) {
		throw new UnsupportedOperationException();
	}
	
	public void setMatchValue(Board board, int location) {
		throw new UnsupportedOperationException();
	}
	
	public int getMatchValue(Board board) {
		throw new UnsupportedOperationException();
	}
	
	public abstract int numberOfJewels();
	
	public abstract List<MatchComponent> getMatchComponents();
	
	protected abstract boolean isLeaf();
	
	protected abstract void addCoordinates(List<Coordinate> coordinates);
	
	public List<Coordinate> getCoordinates() {
		List<Coordinate> res = new ArrayList<>();
		addCoordinates(res);
		return res;
	}
	
	public int baseSize() {
		return getExternal().size();
	}
	
	public abstract int baseScore();
	
	public int getPoints() {
		int score = baseScore();
		for (MatchComponent comp : getInternal()) {
			score += comp.baseScore() + 50;
		}
		return score;
	}
	
	public List<MatchComponent> getInternal() {
		List<MatchComponent> leafs = getExternal();
		List<MatchComponent> res = getMatchComponents();
		res.removeAll(leafs);
		return res;
	}
	
	public List<MatchComponent> getExternal() {
		List<MatchComponent> res = new ArrayList<>();
		for (MatchComponent comp : this.getMatchComponents()) {
			if (comp.isLeaf()) {
				res.add(comp);
			}
		}
		return res;
	}
	
	public boolean inSameRow(MatchComponent that) {
		return false;
	}
	
	public boolean inSameColumn(MatchComponent that) {
		return false;
	}
	
}
