package board;

import java.util.ArrayList;

public class BoardObserver implements Observer {

	private ArrayList<String> observations;
	private String currentState;

	public BoardObserver() {
		observations = new ArrayList<String>();
	}
	
	@Override
	public void update(String observation) {
		observations.add(observation);
		this.currentState = observation;
	}

	@Override
	public String getState() {
		return currentState;
	}
	
	
	
}
