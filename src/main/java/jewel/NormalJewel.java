package jewel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import board.Board;
import board.Coordinate;

@XmlRootElement(name = "normalJewel")
public class NormalJewel extends Jewel {
	
	public NormalJewel(Colour colour) {
		this.colour = colour;
	}
	
	public NormalJewel() {
	}

	@Override
	public List<Coordinate> getMatchCoordinates(Board board, Coordinate coord) {
		List<Coordinate> res = new ArrayList<>();
		res.add(coord);
		return res;
	}
	
	@Override
	public Colour getImageColour() {
		return this.colour;
	}
}
