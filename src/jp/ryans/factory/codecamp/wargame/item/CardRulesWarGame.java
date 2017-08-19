/**
 *
 */
package jp.ryans.factory.codecamp.wargame.item;

/**
 * @author Ryan's Factory
 *
 */
public class CardRulesWarGame implements CardRules {

	@Override
	public int compareTo(Card card1, Card card2) {

		if (card1.getNumber() == card2.getNumber()) {
			// カード番号が等しい
			return 0;
		} else if (card1.getNumber() > card2.getNumber()) {
			if (ACE_NUMBER == card2.getNumber()) {
				// しかしカード2がエースなのでカード2が大きい
				return -1;
			}
			// カード1が大きい
			return 1;
		} else {
			if (ACE_NUMBER == card1.getNumber()) {
				// カード1がエースなのでカード1が大きい
				return 1;
			}
		}
		// カード2が大きい
		return -1;
	}

	@Override
	public boolean equals(Card card1, Card card2) {
		if (card1.getNumber() == card2.getNumber() &&
				card1.getMark().equals(card2.getMark())) {
			return true;
		}
		return false;
	}

}
