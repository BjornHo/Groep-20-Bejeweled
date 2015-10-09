package gui;

import board.Coordinate;

public class SwapJewelAction implements Runnable {
	
	private Gui gui;
	private Coordinate acoord;
	private Coordinate bcoord;

	
	public SwapJewelAction(Gui gui, Coordinate acoord, Coordinate bcoord) {
		this.gui = gui;
		this.acoord = acoord;
		this.bcoord = bcoord;
	}

	@Override
	public void run() {
		gui.setJewelImage(acoord);
		gui.setJewelImage(bcoord);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
