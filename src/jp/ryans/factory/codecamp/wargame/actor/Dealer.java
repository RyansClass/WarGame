/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import java.util.HashMap;

import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.CardRules;
import jp.ryans.factory.codecamp.wargame.item.Trump;
import jp.ryans.factory.codecamp.wargame.item.Trump.TrumpType;

/**
 * ディーラークラス
 */
public final class Dealer extends Actor {

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
	public int judgement(Player cpu, Player you){
		
		Card c = this.deck.get(cpu.getName());
		
		Card y = this.deck.get(you.getName());
		
		this.getPost().add(c);
		
		this.getPost().add(y);

		int result =  y.compareTo(c);
		switch (result ) {

		case CardRules.WIN:
			
			you.putPost(this.getPost());

			break;

		case CardRules.LOSS:
			
			cpu.putPost(this.getPost());

			break;

		case CardRules.DRAW:
			
			break;

		}
		
		return result;
	}

	@Override
	public void putPost(Trump trmp) {
		// 何もしない
		;
	}



}
