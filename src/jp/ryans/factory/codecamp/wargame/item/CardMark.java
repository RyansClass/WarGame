package jp.ryans.factory.codecamp.wargame.item;

/**
 * カードマーク
 * 国際化対応につき使用不可
 * ビルドから除外する
 * @deprecated
 */

public enum CardMark {

	Clubs("クラブ", 0), Diamond("ダイヤ", 1), Heart("ハート", 2), Spade("スペード", 3);

	/**
	 * マークの名前
	 */
	private String markName;
	/**
	 * マークのレベル（強さ)
	 */
	private int level;

	/**
	 * コンストラクタ
	 * @param markName マークの名前
	 * @param level マークのレベル
	 */
	CardMark(String markName, int level) {
		this.markName = markName;
		this.level = level;
	}

	/**
	 * マークの名前を取得
	 * @return
	 */
	public String getMarkName() {
		return markName;
	}

	/**
	 * マークのレベルを取得
	 * @return
	 */
	public int getLevel() {
		return level;
	}
}
