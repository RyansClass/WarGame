/**
 *
 */
package jp.ryans.factory.codecamp.wargame.item;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * トランプ
 */
public class Trump extends ArrayList<Card> {

	/**
	 * ログ出力
	 */
	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * 手札もしくは獲得カード
	 */
	public enum TrumpType {
		HAND, POST
	}

	/**
	 * コンストラクタ
	 */
	public Trump() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param type 手札もしくは獲得カード
	 */
	public Trump(TrumpType type) {
		super();
		// 初期化する
		this.clear();
		switch (type) {
		case HAND:
			// ダイヤ
			this.addAll(Card.getNewInstance(CardMark.Diamond));
			// スペード
			this.addAll(Card.getNewInstance(CardMark.Spade));
			break;
		case POST:
			break;
		}
	}

	/**
	 * トランプをシャッフルする
	 */
	public void shuffle() {
		Collections.shuffle(this);
		logger.trace("トランプをシャッフル");
	}

	/**
	 * トランプからカードを取り出す
	 * @return カード
	 */
	public Card getCard() {
		if (this.isEmpty()) {
			logger.trace("カードがない");
			return null;
		}
		Card c =this.remove(0);
		logger.trace("カードを取り出した [{}]" , c);
		return c;
	}

}
