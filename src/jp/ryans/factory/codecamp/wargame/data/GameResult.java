/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.Serializable;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;

/**
 *
 */
@CsvBean
public class GameResult implements Serializable {


	@CsvColumn(number=1, label="ゲーム回数")
	private int games;

	@CsvColumn(number=2, label="勝利回数")
	private int wins;

	@CsvColumn(number=3, label="最大獲得カード枚数")
	private int maxPossession;

	public GameResult() {
		this.games = 0;
		this.wins = 0;
		this.maxPossession = 0;

	}

	/**
	 * @param maxPossession
	 * @param wins
	 * @param games
	 *
	 */
	public GameResult(int games, int wins, int maxPossession) {
		this.games = games;
		this.wins = wins;
		this.maxPossession = maxPossession;

	}

	/**
	 * @return games
	 */
	public int getGames() {
		return games;
	}

	/**
	 * @param games セットする games
	 */
	public void setGames(int games) {
		this.games = games;
	}

	/**
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @param wins セットする wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * @return maxPossession
	 */
	public int getMaxPossession() {
		return maxPossession;
	}

	/**
	 * @param maxPossession セットする maxPossession
	 */
	public void setMaxPossession(int maxPossession) {
		this.maxPossession = maxPossession;
	}

}
