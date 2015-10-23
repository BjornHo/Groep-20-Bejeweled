package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "horizontalPowerJewel")
public class HorizontalPowerJewel extends Jewel {
	
	public HorizontalPowerJewel(Colour colour) {
		super(colour);
	}
	
	public HorizontalPowerJewel() {
		super();
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.addAll(board.getRow(coord));
		return res;
	}
	
	@Override
	public Colour getImageColour() {
		return Colour.valueOf(this.colour + "HPower");
	}
}
