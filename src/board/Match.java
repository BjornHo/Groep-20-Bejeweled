package board;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Match {
	private List<Coordinate> coordinates;
	private int yMax = -1;
	
	public Match() {
		coordinates = new ArrayList<>();
	}
	
	public void add(Coordinate c) {
		coordinates.add(c);
		if(yMax == -1 || c.getY() > yMax) {
			yMax = c.getY();
		}
	}
	
	public Coordinate get(int i) {
		return coordinates.get(i);
	}
	
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public int size(){
		return coordinates.size();
	}
	
	public int getPoints() {
		int s = size();
		switch (s) {
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
	
	public boolean isVertical() {
		int x = -1;
		for(Coordinate c : coordinates) {
			if(x == -1)
				x = c.getX();
			else if(x != c.getX())
				return false;
		}
		return true;
	}
	
	public boolean isHorizontal() {
		int y = -1;
		for(Coordinate c : coordinates) {
			if(y == -1)
				y = c.getY();
			else if(y != c.getY())
				return false;
		}
		return true;
	}
	
	public int getYMin() {
		if(yMax == -1)
			return -1;
		return yMax - size() + 1;
	}
	
	public int getYMax() {
		return yMax;
	}
	
	public int getX() {
		if(isVertical())
			return coordinates.get(0).getX();
		return -1;
	}
	
	public int getY() {
		if(isHorizontal())
			return coordinates.get(0).getY();
		return -1;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Match){
			Match that = (Match)other;
			if(this.size() != that.size()){
				return false;
			}
			for(int i = 0; i < this.size(); i++){
				if(!this.coordinates.contains(that.coordinates.get(i))){
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
