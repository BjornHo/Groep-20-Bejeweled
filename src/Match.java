import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Match {
	private List<Coordinate> coordinates;
	
	public Match() {
		coordinates = new ArrayList<>();
	}
	
	public Match(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	public void add(Coordinate c) {
		coordinates.add(c);
	}
	
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public int size(){
		return coordinates.size();
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
}
