package gui;

import board.Coordinate;

public class FillJewelAction implements Runnable {
	
	private Coordinate toFill;
	private Gui gui;
	
	public FillJewelAction(Gui gui, Coordinate toFill) {
		this.toFill = toFill;
		this.gui = gui;
	}
	
	@Override
	public void run() {
		gui.setJewelImage(toFill);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
