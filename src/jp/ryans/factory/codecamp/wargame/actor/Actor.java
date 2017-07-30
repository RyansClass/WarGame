/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import java.io.Serializable;

import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.Trump;


/**
 *
 */
public abstract class Actor implements Serializable {

	public abstract void putPost(Trump trmp);

	protected Trump hand;

	protected Trump post;

	/**
	 *
	 */
	public Actor() {

	}

	/**
	 * @return hand
	 */
	public Trump getHand() {
		return this.hand;
	}

	/**
	 * @return post
	 */
	public Trump getPost() {
		return this.post;
	}


	public void setHandCard(Card card) {
		this.hand.add(card);
	}

	public void setPostCard(Card card) {
		this.post.add(card);
	}

	public Card getHandCard() {
		return this.hand.getCard();
	}

	public Card getPostCard() {
		return this.post.getCard();
	}


}
