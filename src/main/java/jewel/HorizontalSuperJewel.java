package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "horizontalSuperJewel")
public class HorizontalSuperJewel extends Jewel {
	
	public HorizontalSuperJewel() {
		
	}
	
	public HorizontalSuperJewel(Colour colour) {
		this.colour = colour;
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.addAll(board.getRow(coord));
		return res;
	}
}
