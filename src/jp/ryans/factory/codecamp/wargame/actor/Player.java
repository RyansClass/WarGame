/**
 *
 */
package jp.ryans.factory.codecamp.wargame.actor;

import jp.ryans.factory.codecamp.wargame.item.Trump;

/**
 * @author Ryan
 *
 */
public class Player extends Actor {
	
	
	private String name;

	/**
	 * コンストラクタ
	 */
	public Player() {
		super();
		this.hand = new Trump();
		this.post = new Trump();
	}

	@Override
	public void putPost(Trump trmp) {
		// 獲得したカードを格納する
		this.post.addAll(trmp);
		// 元を削除する
		trmp.clear();
	}

	/**
	 * プレイヤーの名前取得
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * プレイヤーの名前を設定
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}


}
