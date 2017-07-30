/**
 *
 */
package jp.ryans.factory.codecamp.wargame.item;


/**
 * カードの勝敗や制約に関するインターフェイス
 *
 * @author Ryan's Factory
 *
 */
public interface CardRules {

	/**
	 * カード番号の最小値
	 */
	public static final int MIN_NUMBER = 1;
	/**
	 * カード番号の最大値
	 */
	public static final int MAX_NUMBER = 13;

	/**
	 * 絵札　エース
	 */
	public static final String ACE = " A";

	/**
	 * 番号　エース
	 */
	public static final int ACE_NUMBER = 1;

	/**
	 * 絵札　ジャック
	 */
	public static final String JACK= " J";

	/**
	 * 番号　ジャック
	 */
	public static final int JACK_NUMBER = 11;
	/**
	 * 絵札　クィーン
	 */
	public static final String QUEEN = " Q";

	/**
	 * 番号　クィーン
	 */
	public static final int QUEEN_NUMBER = 12;
	/**
	 * 絵札　キング
	 */
	public static final String KING = " K";
	/**
	 * 番号　キング
	 */
	public static final int KING_NUMBER = 13;

	public static final int WIN = 1;

	public static final int LOSS = -1;

	public static final int DRAW = 0;

	/**
	 * カードを比較する
	 * @param card1
	 * @param card2
	 * @return 0:等しい -1:小さい 1:大きい
	 */
	public int compareTo(Card card1,Card card2);

	/**
	 * カードが等しいか検査する
	 * @param card1
	 * @param card2
	 * @return true:等しい false:等しくない
	 */
	public boolean equals(Card card1,Card card2);


}
