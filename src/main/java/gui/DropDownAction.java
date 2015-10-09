package gui;

import board.Coordinate;

public class DropDownAction implements Runnable {
	
	private Gui gui;
	private Coordinate from;
	private Coordinate to;
	
	public DropDownAction(Gui gui, Coordinate from, Coordinate to) {
		this.gui = gui;
		this.from = from;
		this.to = to;
	}
	
	public boolean isValid() {
		return from.getX() == to.getX() && from.getY() < to.getY();
	}
	
	@Override
	public void run() {
		gui.setJewelImage(to);
		gui.clearJewelImage(from);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
