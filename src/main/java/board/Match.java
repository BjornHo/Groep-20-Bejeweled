package board;

import java.util.ArrayList;
import java.util.List;

public class Match {
	/**
	 * List of a Match Object containing the Coordinate Objects of matching jewels.
	 */
	private List<Coordinate> coordinates;
	
	/**
	 * -1 to show there is no max y-value yet.
	 */
	private int ymax = -1;
	
	public Match() {
		coordinates = new ArrayList<Coordinate>();
	}
	
	/**
	 * Adds a Coordinate to a Match object.
	 * 
	 * @param coord
	 *     Coordinate to be added.
	 */
	public void add(Coordinate coord) {
		coordinates.add(coord);
		if (ymax == -1 || coord.getY() > ymax) {
			ymax = coord.getY();
		}
	}
	
	public Coordinate get(int coord) {
		return coordinates.get(coord);
	}
	
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public int size() {
		return coordinates.size();
	}
	
	/**
	 * Method for returning amount of points certain matches will earn.
	 * 
	 * @return int
	 *     Amount of points.
	 */
	public int getPoints() {
		int size = size();
		switch (size) {
		case 3:
			return 50;
		case 4:
			return 100;
		case 5:
			return 500;
		default:
			return -1;
		}
	}
	
	/**
	 * Checks if a match is vertical.
	 * 
	 * @return boolean
	 *     True if match is vertical.
	 */
	public boolean isVertical() {
		int xcoord = -1;
		for (Coordinate c : coordinates) {
			if (xcoord == -1) {
				xcoord = c.getX();
			} else if (xcoord != c.getX()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if a match is horizontal.
	 * 
	 * @return boolean
	 *     True if match is horizontal.
	 */
	public boolean isHorizontal() {
		int ycoord = -1;
		for (Coordinate c : coordinates) {
			if (ycoord == -1) {
				ycoord = c.getY();
			} else if (ycoord != c.getY()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns minimum y-value of the Coordinates of a Match object.
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
	
	/**
	 * Returns x-value of a Match, if vertical.
	 * 
	 * @return int
	 *     x-value.
	 */
	public int getX() {
		if (isVertical()) {
			return coordinates.get(0).getX();
		}
		return -1;
	}
	
	/**
	 * Returns y-value of a Match, if Horizontal.
	 * 
	 * @return int
	 *     y-value.
	 */
	public int getY() {
		if (isHorizontal()) {
			return coordinates.get(0).getY();
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Match) {
			Match that = (Match)other;
			if (this.size() != that.size()) {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				if (!this.coordinates.contains(that.coordinates.get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ((coordinates.size() + 1) * ymax);
	}

}
