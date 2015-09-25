package game;

import board.Board;
import board.Coordinate;
import board.Match;
import board.StatsListener;
import jewel.Colour;
import jewel.Jewel;
import logger.Logger;
import logger.Priority;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game implements ActionListener {
	
	private Board board;
	private int score = 0;
	private int level = 1;
	private int count = 60;
	
	@XmlTransient
	private Timer timer;
	
	@XmlTransient
	private List<StatsListener> statsListeners;
	
	public Game() {
		timer = new Timer(1000,this);
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
		} else {
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
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public int getTimeLeft() {
		return count;
	}
	
	public void restartGame() {
		timer.stop();
		score = 0;
		level = 1;
		count = 60;
		board.reset();
		notifyLevelChanged();
		notifyScoreChanged();
		notifyNextLevelChanged();
		notifyTimeLeft();
		timer.start();
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
			l.nextLevelChanged(goalScore());
		}
	}
	
	public void notifyTimeLeft() {
		for (StatsListener l : statsListeners) {
			l.timeLeftChanged(count);
		}
	}
	
	public void startTimer() {
		timer.start();
	}
	
	/**
	 * If score limit has been reached, progress to the next level.
	 */
	public void nextLevelCheck() {
		if (score >= goalScore()) {
			level++;
			count = 60;
			score = 0;
			board.reset();
			notifyScoreChanged();
			notifyLevelChanged();
			notifyNextLevelChanged();
			notifyTimeLeft();
		}
	}
	
	public int goalScore() {
		return 750 + 250 * level;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}

	public void setScore(int newScore) {
		score = newScore;
	}
	
	public void setLevel(int newLevel) {
		level = newLevel;
	}
	
	public boolean gameLost() {
		return count == 0 && score < goalScore();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		notifyTimeLeft();
		if (count == 0) {
			System.out.println("Game over!");
			restartGame();
		} else {
			count--;
		}
	}
}
