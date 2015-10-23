package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "verticalPowerJewel")
public class VerticalPowerJewel extends Jewel {
	
	public VerticalPowerJewel() {
		super();
	}
	
	public VerticalPowerJewel(Colour colour) {
		super(colour);
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.addAll(board.getColumn(coord));
		return res;
	}

	@Override
	public Colour getImageColour() {
		return Colour.valueOf(this.colour + "VPower");
	}
}
