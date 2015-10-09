package observers;

import java.util.ArrayList;

public class BoardObserver implements Observer {

	private ArrayList<String> pastStates;
	private String currentState;

	public BoardObserver() {
		pastStates = new ArrayList<String>();
	}
	
	@Override
	public void update(String state) {
		pastStates.add(this.currentState);
		this.currentState = state;
	}

	@Override
	public String getState() {
		return currentState;
	}
	
	
	
}
