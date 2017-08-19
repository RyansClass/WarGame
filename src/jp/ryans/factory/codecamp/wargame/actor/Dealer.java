/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import java.util.HashMap;

import jp.ryans.factory.codecamp.wargame.Main;
import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.CardRules;
import jp.ryans.factory.codecamp.wargame.item.Trump;
import jp.ryans.factory.codecamp.wargame.item.Trump.TrumpType;
import jp.ryans.factory.codecamp.wargame.resource.R;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ディーラークラス
 */
public final class Dealer extends Actor {

	/**
	 * ログ出力
	 */
	private Logger logger = LogManager.getLogger(this.getClass());
	
	/**
	 * 判定用のカードを格納する
	 */
	private HashMap<String,Card> deck;
	
	/**
	 * コンストラクタ
	 */
	public Dealer() {
		
		super();
		
		this.hand = new Trump(TrumpType.HAND);
		
		this.post = new Trump(TrumpType.POST);
		
		this.deck = new HashMap<String,Card>();

		this.hand.shuffle();
	}

	public void setHandCard(Player player, Card card) {
		
		this.deck.put(player.getName(), card);
	}
	
	/**
	 * プレイヤーの手札を判定する
	 * @param cpu
	 * @param you
	 */
	public void judgement(Player cpu, Player you){
		
		Card c = this.deck.get(cpu.getName());
		
		Card y = this.deck.get(you.getName());
		
		this.getPost().add(c);
		
		this.getPost().add(y);

		switch ( y.compareTo(c) ) {

		case CardRules.WIN:
			
			System.out.println(Main.resource.findByStringsId(R.TEXT_TUEN_WIN));
			
			you.putPost(this.getPost());

			break;

		case CardRules.LOSS:
			
			System.out.println(Main.resource.findByStringsId(R.TEXT_TUEN_LOSS));

			cpu.putPost(this.getPost());

			break;

		case CardRules.DRAW:
			
			System.out.println(Main.resource.findByStringsId(R.TEXT_TUEN_DRAW));

			break;

		}
	}

	@Override
	public void putPost(Trump trmp) {
		// 何もしない
		;
	}



}
