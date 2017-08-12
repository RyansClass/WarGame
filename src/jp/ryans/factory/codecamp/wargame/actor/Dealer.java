/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import jp.ryans.factory.codecamp.wargame.WarGame;
import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.CardRules;
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

	public void judgement(Player cpu, Player you) {

		switch (judgement()) {

		case CardRules.WIN:
			System.out.println(WarGame.TEXT_TUEN_WIN);
			you.putPost(this.getPost());

			break;

		case CardRules.LOSS:
			System.out.println(WarGame.TEXT_TUEN_LOSS);

			cpu.putPost(this.getPost());

			break;

		case CardRules.DRAW:
			System.out.println(WarGame.TEXT_TUEN_DRAW);

			break;

		}
	}



}
