package game;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import board.Coordinate;
import board.Match;
import board.StatsListener;
import jewel.Colour;
import jewel.Jewel;
import logger.Logger;
import logger.Priority;

public class Game {
	private Board board;
	private int score = 0;
	private int level = 1;
	private List<StatsListener> statsListeners;
	
	public Game() {
		board = new Board();
		statsListeners = new ArrayList<StatsListener>();
	}
	
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Adds a StatsListener to the Board.
	 * 
	 * @param listener
	 *     The StatsListener to be added.
	 */
	public void addStatsListener(StatsListener listener) {
		this.statsListeners.add(listener);
		Logger.log(Priority.INFO, "StatsListener " + listener.getClass().getSimpleName()
				+ " added to Board.");
	}
	
	/**
	 * Returns the list of all current StatsListeners.
	 */
	public List<StatsListener> getStatsListeners() {
		return statsListeners;
	}
	
	/**
	 * Processes the selection of the Jewel at coordinate c, taking the previous
	 * selection (if there is one) into account. If a jewel is selected that is
	 * next to the previously selected jewel, swapJewels is called. Also
	 * notifies all BoardListeners of the currently and previously selected
	 * jewel.
	 * 
	 * @param coord
	 *     Coordinate of the jewel to process.
	 */
	public void processJewel(Coordinate coord) {
		if (!board.hasSelectedJewel() || (!Coordinate.areAdjacent(board.getSelectedPos(), coord)
				&& !coord.equals(board.getSelectedPos())) ) {
			board.notifySelect(coord, board.getSelectedPos());
			board.setSelectedPos(coord);
			Logger.log(Priority.INFO, "Selected Position " + coord);
			
		} else if (Coordinate.areAdjacent(board.getSelectedPos(), coord)) {
			board.swapJewels(board.getSelectedPos(),coord);
			Logger.log(Priority.INFO, "Swapped Jewels " + board.getSelectedPos() + ", " + coord);
			List<Match> matches = board.checkMatches();
			if (matches.isEmpty()) {
				board.swapJewels(board.getSelectedPos(),coord);
				Logger.log(Priority.INFO, "Swapped back Jewels "
				+ board.getSelectedPos() + ", " + coord);
				board.setSelectedPos(null);
				return;
			}
			while (!matches.isEmpty()) {
				processMatch(matches.get(0));
				matches = board.checkMatches();
			}
			board.setSelectedPos(null);
			nextLevelCheck();
		}
	}
	
	/**
	 * Updates the score. Checks for completed sets. Refills the board. Updates the board.
	 * @param match
	 *     The Match to be processed.
	 */
	public void processMatch(Match match) {
		score += match.getPoints();
		notifyScoreChanged();
		if (match.isHorizontal()) {
			for (Coordinate c : match.getCoordinates()) {
				while (c.hasNorth()) {
					board.setJewel(board.getJewel(c.getNorth()), c);
					c = c.getNorth();
				}
				board.setJewel(new Jewel(Colour.randomColour()), c);
			}
		} else if (match.isVertical()) {
			//Nr. of elements above the group of jewels that form a vertical match
			int aboveMatch = match.getYMin();  
			
			int xcoord = match.getX();
			//Move all jewels above the cleared ones down.
			for (int delta = 0; delta < aboveMatch; delta++) {
				int temp = match.getYMax() - match.size() - delta;
				board.setJewel(board.getJewel(new Coordinate(xcoord, temp)),
						new Coordinate(xcoord, match.getYMax() - delta));
			}
			//Place new Jewels on the newly blanked spaces.
			for (int y = match.getYMax() - aboveMatch; y >= 0; y--) {
				board.setJewel(new Jewel(Colour.randomColour()),new Coordinate(xcoord,y));
			}
		}
		board.notifyBoardChanged();
	}
	
	/**
	 * Notifies the StatsListener that the score has been changed / needs updating.
	 */
	public void notifyScoreChanged() {
		for (StatsListener l : statsListeners) {
			l.scoreChanged(score);
		}
	}
	
	/**
	 * Notifies the StatsListener that the level has been changed / needs updating.
	 * 
	 * @param level
	 *     (int) The level to be updated to.
	 */
	public void notifyLevelChanged() {
		for (StatsListener l : statsListeners) {
			l.levelChanged(level);
		}
	}
	
	public void notifyNextLevelChanged() {
		for (StatsListener l : statsListeners) {
			l.nextLevelChanged(scoreForNextLevel());;
		}
	}
	
	public void nextLevelCheck() {
		if (score >= scoreForNextLevel()) {
			level++;
			score = 0;
			notifyScoreChanged();
			notifyLevelChanged();
			notifyNextLevelChanged();
		}
	}
	
	public int scoreForNextLevel() {
		return 750 + 250 * level;
	}
	
	
}
