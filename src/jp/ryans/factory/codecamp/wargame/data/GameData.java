/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.Serializable;

import jp.ryans.factory.codecamp.wargame.actor.Dealer;
import jp.ryans.factory.codecamp.wargame.actor.Player;

/**
 * ゲームデータクラス
 *
 */
public class GameData implements Serializable {

	/**
	 * ディーラー
	 */
	private Dealer dealer;

	/**
	 * プレイヤー　CPU
	 */
	private Player cpu;

	/**
	 * プレイヤー　あなた
	 */
	private Player you;

	/**
	 * ゲームターン
	 */
	private int turn;

	/**
	 * コンストラクタ
	 * @param cpuName CPUの名前
	 * @param youName あなたの名前
	 *
	 */
	public GameData(String cpuName, String youName) {

		dealer = new Dealer();

		cpu = new Player();

		you = new Player();

		cpu.setName(cpuName);

		you.setName(youName);

		turn = 1;
	}

	/**
	 * ディーラーを取得
	 * @return dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * プレイヤー　CPUを取得
	 * @return cpu
	 */
	public Player getCpu() {
		return cpu;
	}

	/**
	 * プレイヤー　あなたを取得
	 * @return you
	 */
	public Player getYou() {
		return you;
	}

	/**
	 * 現在のターンを取得
	 * @return
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * 次のターンにする
	 */
	public void incrementTurn() {
		turn++;
	}

}
