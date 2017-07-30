/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.Trump;
import jp.ryans.factory.codecamp.wargame.item.Trump.TrumpType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ryan
 *
 */
public class Dealer extends Actor {

	/**
	 * ログ出力
	 */
	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 *
	 */
	public Dealer() {
		super();
		this.hand = new Trump(TrumpType.HAND);
		this.post = new Trump(TrumpType.POST);

		this.hand.shuffle();
	}

	public boolean isHandEmpty() {
		return this.hand.isEmpty();
	}

	@Override
	public void putPost(Trump trmp) {
		// 何もしない
		;
	}

	public int judgement() {
		Card c = this.getHandCard();
		Card y = this.getHandCard();
		logger.trace("CPU={} USER={} 判定={}",c,y,y.compareTo(c));
		this.getPost().add(c);
		this.getPost().add(y);
		return y.compareTo(c);
	}

}
