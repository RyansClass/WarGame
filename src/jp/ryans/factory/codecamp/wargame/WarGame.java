/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.io.IOException;

import jp.ryans.factory.codecamp.wargame.data.FileSerialize;
import jp.ryans.factory.codecamp.wargame.data.GameData;
import jp.ryans.factory.codecamp.wargame.data.GameResultFile;
import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.item.CardRules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ゲームコントロールクラス
 */
public class WarGame {

	/**
	 * Windowsの文字コード
	 */
	private static final String CHAR_WINDOWS = "Windows-31j";

	/**
	 * MacもしくはLinuxの文字コード
	 */
	private static final String CHAR_UTF = "UTF-8";
	

	/**
	 * エラーメッセージ
	 */
	private static final String ERROR_GAME_DATA_WRITE = "ゲームの現在データを保存できません。";

	/**
	 * 画面出力メッセージ
	 */
	private static final String TEXT_TUEN_TITLE = "### 第 %2d 回戦 ###";
	private static final String TEXT_TUEN_FIELD = "場に積まれた札: %2d枚";
	private static final String TEXT_TUEN_CPU = "CPUの持ち札:%2d枚, 獲得した札:%2d枚";
	private static final String TEXT_TUEN_USER = "あなたの持ち札:%2d枚, 獲得した札:%2d枚";

	private static final String TEXT_TUEN_CPU_DECK = "CPUが切った札:[%s]";
	private static final String TEXT_TUEN_YOU_DECK = "あなたが切った札:[%s]";

	private static final String TEXT_TUEN_WIN = "あなたが獲得しました!";
	private static final String TEXT_TUEN_LOSS = "CPUが獲得しました。";
	private static final String TEXT_TUEN_DRAW = "引き分けです。";

	private static final String TEXT_RESULT_TITLE = "### 最終結果 ###";
	private static final String TEXT_RESULT_CPU = "CPUが獲得した札:%2d枚";
	private static final String TEXT_RESULT_USER = "あなたが獲得した札:%2d枚";
	private static final String TEXT_RESULT_WIN = "あなたが勝ちました、おめでとう!";
	private static final String TEXT_RESULT_LOSS = "あなたの負けです、残念。";
	private static final String TEXT_RESULT_DRAW = "引き分けです。";

	/**
	 * ログ出力
	 */
	private Logger logger = LogManager.getLogger(this.getClass());

	
	private String os;

	private GameData data;

	private String gameResultFilename;

	private String interruptionFilename;

	/**
	 *
	 */
	public WarGame() {
		os = "";
	}

	public WarGame(String os) {
		this.os = os;
	}

	private boolean isInterruption(Keyboard keyin) {

		FileSerialize<GameData> dataFile = new FileSerialize<GameData>(this.gameResultFilename);

		if (keyin.isInterruption()) {

			try {

				dataFile.write(data);

			} catch (IOException e) {

				logger.error(ERROR_GAME_DATA_WRITE);
			}
			// ゲーム中断
			return true;
		}

		return false;
	}

	private GameData reStart(Keyboard keyin) {

		FileSerialize<GameData> dataFile = new FileSerialize<GameData>(this.gameResultFilename);

		GameData result = null;

		if (dataFile.isFileCheck()) {
			// ゲーム再開するか問い合わせする
			if (keyin.isReStart()) {

				try {

					result = dataFile.read();

				} catch (IOException e) {

				}
			}
		}

		dataFile.delete();

		if (null == result) {

			result = new GameData();

		}

		return result;
	}

	public int run() {

		Keyboard keyin = new Keyboard();
		// ゲームの再開処理
		data = reStart(keyin);
		// カードを配る
		while (!data.getDealer().isHandEmpty()) {

			data.getCpu().setHandCard(data.getDealer().getHandCard());

			data.getYou().setHandCard(data.getDealer().getHandCard());
		}

		do {
			// ターンの表示
			viewTrun();
			//
			if (isInterruption(keyin)) {
				// ゲーム中断
				keyin.close();

				return 0;
			}
			// カードを出す
			Card cCard = data.getCpu().getHandCard();

			Card yCard = data.getYou().getHandCard();

			if (null == cCard || null == yCard) {
				// ゲーム終了
				break;
			}

			viewDeck(cCard, yCard);

			data.getDealer().setHandCard(cCard);

			data.getDealer().setHandCard(yCard);

			// カードの判定
			switch (data.getDealer().judgement()) {

			case CardRules.WIN:
				System.out.println(TEXT_TUEN_WIN);
				data.getYou().putPost(data.getDealer().getPost());

				break;

			case CardRules.LOSS:
				System.out.println(TEXT_TUEN_LOSS);

				data.getCpu().putPost(data.getDealer().getPost());

				break;

			case CardRules.DRAW:
				System.out.println(TEXT_TUEN_DRAW);

				break;

			}

			data.incrementTurn();

		} while (0 != data.getCpu().getHand().size() && 0 != data.getYou().getHand().size());

		viewEnd();

		GameResultFile result = new GameResultFile(this.interruptionFilename);

		result.setCharset(getScvCharset());

		result.readAll();

		result.Upadate(data.getYou().getPost().size());

		result.writeAll();

		keyin.close();

		return 0;

	}

	private void viewDeck(Card cCard, Card yCard) {
		System.out.println(String.format(TEXT_TUEN_CPU_DECK, cCard));
		System.out.println(String.format(TEXT_TUEN_YOU_DECK, yCard));
	}

	private void viewEnd() {
		System.out.println(TEXT_RESULT_TITLE);
		System.out.println(String.format(TEXT_RESULT_CPU, data.getCpu().getPost().size()));
		System.out.println(String.format(TEXT_RESULT_USER, data.getYou().getPost().size()));
		int r = data.getYou().getPost().size() - data.getCpu().getPost().size();
		if (0 == r) {
			System.out.println(TEXT_RESULT_DRAW);
		} else if (0 < r) {
			System.out.println(TEXT_RESULT_WIN);
		} else {
			System.out.println(TEXT_RESULT_LOSS);
		}

	}

	private void viewTrun() {
		System.out.println(String.format(TEXT_TUEN_TITLE, data.getTurn()));
		System.out.println(String.format(TEXT_TUEN_FIELD, data.getDealer().getPost().size()));
		System.out.println(String.format(TEXT_TUEN_CPU, data.getCpu().getHand().size(), data.getCpu().getPost().size()));
		System.out.println(String.format(TEXT_TUEN_USER, data.getYou().getHand().size(), data.getYou().getPost().size()));

	}

	private String getScvCharset() {

		if (os.startsWith("windows")) {

			return CHAR_WINDOWS;

		} else {

			return CHAR_UTF;

		}
	}

	public void setGameResultFilename(String filepath) {

		gameResultFilename = filepath;

	}

	public void setInterruptionFilename(String interruptionFilepath) {

		interruptionFilename = interruptionFilepath;

	}
}
