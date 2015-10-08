package board;

import java.util.ArrayList;
import java.util.List;

public class Match extends MatchComponent {
	/**
	 * List of a Match Object containing the Coordinate Objects of matching jewels.
	 */
	private List<MatchComponent> matchComponents;
	
	/**
	 * -1 to show there is no max y-value yet.
	 */
	private int ymax = -1;
	
	public Match() {
		matchComponents = new ArrayList<MatchComponent>();
	}
	
	/**
	 * Adds a Coordinate to a Match object.
	 * 
	 * @param coord
	 *     Coordinate to be added.
	 */
	public void add(Coordinate coord) {
		matchComponents.add(coord);
		if (ymax == -1 || coord.getY() > ymax) {
			ymax = coord.getY();
		}
	}
	
	public MatchComponent get(int component) {
		return matchComponents.get(component);
	}
	
	public void set(MatchComponent comp, int location) {
		matchComponents.set(location, comp);
	}
	
	public List<MatchComponent> getMatchComponents() {
		return matchComponents;
	}
	
	public int size() {
		return matchComponents.size();
	}
	
	/**
	 * Method for returning amount of points certain matches will earn.
	 * 
	 * @return int
	 *     Amount of points.
	 */
	public int getPoints() {
		int score = baseScore();
		for (MatchComponent component : matchComponents) {
			score += component.getPoints();
		}
		return score;
	}
	
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
	
	public void clear(Board board) {
		for (MatchComponent comp : matchComponents) {
			comp.clear(board);
		}
	}
	
	/**
	 * Checks if a match is vertical.
	 * 
	 * @return boolean
	 *     True if match is vertical.
	 */
//	public boolean isVertical() {
//		int xcoord = -1;
//		for (MatchComponent m : matchComponents) {
//			if (xcoord == -1) {
//				xcoord = m.getX();
//			} else if (xcoord != c.getX()) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	/**
	 * Checks if a match is horizontal.
	 * 
	 * @return boolean
	 *     True if match is horizontal.
	 */
//	public boolean isHorizontal() {
//		int ycoord = -1;
//		for (Coordinate c : matchComponents) {
//			if (ycoord == -1) {
//				ycoord = c.getY();
//			} else if (ycoord != c.getY()) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	/**
	 * Returns minimum y-value of the matchComponents of a Match object.
	 * 
	 * @return int
	 *     Minimum y-value.
	 */
	public int getYMin() {
		if (ymax == -1) {
			return -1;
		}
		return ymax - size() + 1;
	}
	
	public int getYMax() {
		return ymax;
	}
	
	public void setMatchValue(Board board, int location) {
		for (MatchComponent comp : matchComponents) {
			comp.setMatchValue(board, location);
		}
	}
	
	public int getMatchValue(Board board) {
		return matchComponents.get(0).getMatchValue(board);
	}
	
	/**
	 * Returns x-value of a Match, if vertical.
	 * 
	 * @return int
	 *     x-value.
	 */
//	public int getX() {
//		if (isVertical()) {
//			return matchComponents.get(0).getX();
//		}
//		return -1;
//	}
	
	/**
	 * Returns y-value of a Match, if Horizontal.
	 * 
	 * @return int
	 *     y-value.
	 */
//	public int getY() {
//		if (isHorizontal()) {
//			return matchComponents.get(0).getY();
//		}
//		return -1;
//	}
	
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
		return ((matchComponents.size() + 1) * ymax);
	}

}
