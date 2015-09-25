package gui;

import jewel.Colour;
import jewel.Jewel;
import board.Board;

public class SupportBoard extends Board {

	public SupportBoard() {
		
	}
	
	private Jewel[][] createGrid() {
		Jewel[][] grid = { 
				{ new Jewel(Colour.Red), new Jewel(Colour.Red),
					new Jewel(Colour.Blue)},
				{ new Jewel(Colour.Orange), new Jewel(Colour.Yellow),
					new Jewel(Colour.Green)},
				{ new Jewel(Colour.Red), new Jewel(Colour.Orange),
					new Jewel(Colour.Blue)}		
		};
		return grid;
	}

}
