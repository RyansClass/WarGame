/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.Serializable;

import jp.ryans.factory.codecamp.wargame.GameConst;
import jp.ryans.factory.codecamp.wargame.actor.Dealer;
import jp.ryans.factory.codecamp.wargame.actor.Player;

/**
 * @author Ryan
 *
 */
public class GameData  implements Serializable {

	private Dealer dealer;

	private Player cpu;

	private Player you;

	private int turn;

	/**
	 *
	 */
	public GameData() {
		
		dealer = new Dealer();
		
		cpu = new Player();
		
		you = new Player();

		cpu.setName(GameConst.CPU);
		
		you.setName(GameConst.YOU);
		
		turn = 1;
	}


	/**
	 * @return dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}


	/**
	 * @return cpu
	 */
	public Player getCpu() {
		return cpu;
	}


	/**
	 * @return you
	 */
	public Player getYou() {
		return you;
	}

	public int getTurn() {
		return turn;
	}

	public void incrementTurn() {
		turn++;
	}

}
