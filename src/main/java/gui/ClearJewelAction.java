package gui;

import java.util.List;

import board.Coordinate;

public class ClearJewelAction implements Runnable {
	
	private Gui gui;
	private List<Coordinate> toClear;
	
	public ClearJewelAction(Gui gui, List<Coordinate> coordinates) {
		this.gui = gui;
		this.toClear = coordinates;
	}
	
	@Override
	public void run() {
		gui.playMatchSound();

		for (Coordinate coord : toClear) {
			gui.setJewelImage(coord);
		}
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}