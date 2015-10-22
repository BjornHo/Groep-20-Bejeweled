package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "horizontalPowerJewel")
public class HorizontalPowerJewel extends Jewel {
	
	public HorizontalPowerJewel() {
		
	}
	
	public HorizontalPowerJewel(Colour colour) {
		this.colour = colour;
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.addAll(board.getRow(coord));
		return res;
	}
}
