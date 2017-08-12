/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.io.IOException;

import jp.ryans.factory.codecamp.wargame.data.FileSerialize;
import jp.ryans.factory.codecamp.wargame.data.GameData;
import jp.ryans.factory.codecamp.wargame.data.GameResultFile;
import jp.ryans.factory.codecamp.wargame.item.Card;

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

	public static final String TEXT_TUEN_WIN = "あなたが獲得しました!";
	public static final String TEXT_TUEN_LOSS = "CPUが獲得しました。";
	public static final String TEXT_TUEN_DRAW = "引き分けです。";

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

	/**
	 * OS名
	 */
	private String os;

	/**
	 * ゲームデータ
	 */
	private GameData data;

	/**
	 * ゲームの結果ファイルのパス
	 */
	private String gameResultFilename;

	/**
	 * ゲームの中断データのファイルパス
	 */
	private String interruptionFilename;

	/**
	 * コンストラクタ
	 */
	public WarGame() {
		os = "";
	}

	/**
	 * コンストラクタ
	 * @param os 実行環境のOS名
	 */
	public WarGame(String os) {
		this.os = os;
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
			// デッキの表示
			viewDeck(cCard, yCard);
			// ディーラーへCPUのカードを渡す
			data.getDealer().setHandCard(cCard);
			// ディーラーへあなたのカードを渡す
			data.getDealer().setHandCard(yCard);
			// カードの判定
			data.getDealer().judgement(data.getCpu(),data.getYou());
			// 次のターン
			data.incrementTurn();
			// ゲームの終了判定
		} while (0 != data.getCpu().getHand().size() && 0 != data.getYou().getHand().size());
		// ゲームの終了
		viewEnd();
		// ゲームの結果を保存する
		writeGameResult();

		keyin.close();

		return 0;

	}

	private void writeGameResult() {
		GameResultFile result = new GameResultFile(this.interruptionFilename);

		result.setCharset(getScvCharset());

		result.readAll();

		result.Upadate(data.getYou().getPost().size());

		result.writeAll();

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

}
