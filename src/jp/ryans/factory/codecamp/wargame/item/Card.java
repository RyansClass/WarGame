package jp.ryans.factory.codecamp.wargame.item;

import java.io.Serializable;

import jp.ryans.factory.codecamp.wargame.Main;
import jp.ryans.factory.codecamp.wargame.item.CardRules.CardMark;
import jp.ryans.factory.codecamp.wargame.resource.R;

/**
 * カード
 */
public class Card implements Serializable, Comparable<Card> {
	
	/**
	 * 指定のカードマークのカードを生成する
	 * @param mark カードマーク
	 * @return カードをトランプで返却
	 */
	public static Trump getNewInstance(CardRules.CardMark mark) {
		
		Trump result = new Trump();
		
		for (int i = CardRules.MIN_NUMBER; i <= CardRules.MAX_NUMBER; i++) {
			
			result.add(new Card(i, mark));
			
		}
		
		return result;
	}
	/**
	 * カード番号
	 */
	private int number;
	/**
	 * カードマーク
	 */
	private CardRules.CardMark mark;


	/**
	 * コンストラクタ
	 * @param number カード番号
	 * @param markIndex カードマーク
	 */
	public Card(int number, CardMark mark) {
		this.number = number;
		this.mark = mark;
	}

	@Override
	public int compareTo(Card o) {
		return (new CardRulesWarGame()).compareTo(this, o);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			return (new CardRulesWarGame()).equals(this, (Card) obj);
		}
		return false;
	}

	/**
	 * カードマークを取得
	 * @return
	 */
	public CardMark getMark() {
		return this.mark;
	}

	/**
	 * カード番号を取得
	 * @return
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * カード番号を文字列で取得
	 * @return
	 */
	public String getNumberString() {
		switch (this.number) {
		case CardRules.ACE_NUMBER:
			return CardRules.ACE;
		case CardRules.JACK_NUMBER:
			return CardRules.JACK;
		case CardRules.QUEEN_NUMBER:
			return CardRules.QUEEN;
		case CardRules.KING_NUMBER:
			return CardRules.KING;
		}
		return String.format("%2d", this.number);
	}

	@Override
	public String toString() {
		// 存在しないマークの場合は例外が発生する
		int id = -1;
		
		switch(this.mark) {
		
		case Clubs:
			id = R.CLUBS;
			break;
		case Diamond:
			id = R.DIAMOND;
			break;
			
		case Heart:
			id = R.HEART;
			break;
			
		case Spade:
			id = R.SPADE;
			break;
			
		}
		
		return Main.resource.findByStringsId(id) + this.getNumberString();
	}

}
