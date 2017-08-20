/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import java.io.Serializable;

import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.Trump;


/**
 * 動作主体のクラス
 */
public abstract class Actor implements Serializable {

	/**
	 * 獲得したカードを格納する
	 * @param trmp
	 */
	public abstract void putPost(Trump trmp);

	/**
	 * 手札
	 */
	protected Trump hand;

	/**
	 * 獲得カード
	 */
	protected Trump post;

	/**
	 * コンストラクタ
	 */
	public Actor() {

	}

	/**
	 * 手札のトランプを取得
	 * @return hand
	 */
	public Trump getHand() {
		return this.hand;
	}

	/**
	 * 獲得カードのトランプを取得
	 * @return post
	 */
	public Trump getPost() {
		return this.post;
	}

	/**
	 * 手札を追加
	 * @param handCard
	 */
	public void setHandCard(Card card) {
		this.hand.add(card);
		
	}

	/**
	 * 獲得カードを追加
	 * @param card
	 */
	public void setPostCard(Card card) {
		this.post.add(card);
	}

	/**
	 * 手札を取得
	 * @return
	 */
	public Card getHandCard() {
		return this.hand.getCard();
	}

	/**
	 * 獲得カードを取得
	 * @return
	 */
	public Card getPostCard() {
		return this.post.getCard();
	}

	/**
	 * 手札が空か検査
	 * @return
	 */
	public boolean isHandEmpty() {
		return this.hand.isEmpty();
	}


}
