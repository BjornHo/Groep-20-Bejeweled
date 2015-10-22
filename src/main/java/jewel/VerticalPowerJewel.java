package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "verticalPowerJewel")
public class VerticalPowerJewel extends Jewel {
	public VerticalPowerJewel() {
	
	}
	
	public VerticalPowerJewel(Colour colour) {
		this.colour = colour;
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.addAll(board.getColumn(coord));
		return res;
	}
}
