package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jewel.Jewel;

public class Match extends MatchComponent {
	/**
	 * List of a Match Object containing the Coordinate Objects of matching jewels.
	 */
	private List<MatchComponent> matchComponents;
	
	/**
	 * -1 to show there is no max y-value yet.
	 */
	
	private Board board;
	
	public Match(Board board) {
		this.board = board;
		matchComponents = new ArrayList<MatchComponent>();
	}
	
	/**
	 * Adds a MatchComponent to a Match object.
	 * 
	 * @param matchComponent
	 *     matchComponent to be added.
	 */
	
	public void add(MatchComponent matchComponent) {
		//matchComponents.removeAll(matchComponent.getMatchComponents());
		matchComponents.add(matchComponent);
	}
	
	public void add(Coordinate matchComponent) {
		Jewel jewel = board.getJewel(matchComponent);
		List<Coordinate> toAdd = jewel.getMatchCoordinates(board, matchComponent);
		if (toAdd.size() > 1) {
			Match match = new Match(board);
			match.addAll(toAdd);
			this.add(match);
		} else {
			this.addAll(toAdd);
		}
		
	}
	
	private void addAll(Collection<Coordinate> coordinates) {
		matchComponents.addAll(coordinates);
	}
	
	public void remove(MatchComponent matchComponent) {
		matchComponents.remove(matchComponent);
	}
	
	public MatchComponent getChild(int location) {
		return matchComponents.get(location);
	}
	
//	public void set(MatchComponent comp, int location) {
//		matchComponents.set(location, comp);
//	}
	
	public List<MatchComponent> getMatchComponents() {
		return matchComponents;
	}
	
	public int size() {
		return matchComponents.size();
	}
	
	public int getTrueSize() {
		int size = 0;
		for (MatchComponent component : matchComponents) {
			size += component.getTrueSize();
		}
		return size;
	}
	
	/**
	 * Method for returning amount of points certain matches will earn.
	 * 
	 * @return int
	 *     Amount of points.
	 */
	public int getPoints() {
		int score = baseScore();
		if (score < 0) {
			return score;
		}
		for (MatchComponent component : matchComponents) {
			score += component.getPoints();
		}
		return score;
	}
	
	/**
	 * Gets the score linked to the size of the match.
	 * 
	 * @return
	 */
	
	public int baseScore() {
		int size = size();
		switch (size) {
		case 3:
			return 200;
		case 4:
			return 300;
		case 5:
			return 750;
		default:
			return -1;
		}
	}
	
	/**
	 * Clears the current Match from a Board.
	 * 
	 * @param board
	 * 	the board to remove the Match from
	 */
	
	public void clear(Board board) {
		for (MatchComponent comp : matchComponents) {
			comp.clear(board);
		}
	}
	
	public void setMatchValue(Board board, int location) {
		for (MatchComponent comp : matchComponents) {
			comp.setMatchValue(board, location);
		}
	}
	
	public int getMatchValue(Board board) {
		return matchComponents.get(0).getMatchValue(board);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Match) {
			Match that = (Match)other;
			if (this.size() != that.size()) {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				if (!this.matchComponents.contains(that.matchComponents.get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ((matchComponents.size() + 1) * 5);
	}

	@Override
	public void getCoordinates(List<Coordinate> coordinates) {
		for (MatchComponent comp : matchComponents) {
			comp.getCoordinates(coordinates);
		}
	}

}
