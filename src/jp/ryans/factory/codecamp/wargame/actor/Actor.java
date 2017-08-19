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
	 * 
	 * @param trmp
	 */
	public abstract void putPost(Trump trmp);

	/**
	 * 
	 */
	protected Trump hand;

	/**
	 * 
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
	 * 
	 * @param card
	 */
	public void setPostCard(Card card) {
		this.post.add(card);
	}

	/**
	 * 
	 * @return
	 */
	public Card getHandCard() {
		return this.hand.getCard();
	}

	/**
	 * 
	 * @return
	 */
	public Card getPostCard() {
		return this.post.getCard();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isHandEmpty() {
		return this.hand.isEmpty();
	}


}
