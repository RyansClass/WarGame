/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.io.IOException;

import jp.ryans.factory.codecamp.wargame.data.FileSerialize;
import jp.ryans.factory.codecamp.wargame.data.GameData;
import jp.ryans.factory.codecamp.wargame.data.GameResultFile;
import jp.ryans.factory.codecamp.wargame.item.Card;
import jp.ryans.factory.codecamp.wargame.resource.R;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ゲームコントロールクラス
 */
public class WarGame {

	/**
	 * デフォルトOS
	 */
	private static final String WINDOWS = GameConst.WINDOWS;

	/**
	 * 正常終了
	 */
	private static final int SUCCESSFUL = GameConst.SUCCESSFUL;

	/**
	 * 異常終了
	 */
	private static final int ABEND = GameConst.ABEND;



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
		this.os = WINDOWS;
	}

	/**
	 * コンストラクタ
	 * @param os 実行環境のOS名
	 * @param resource
	 */
	public WarGame(String os) {
		this.os = os;
	}

	/**
	 * ゲームの結果ファイルのパスを設定
	 * @param filepath ファイルパス
	 */
	public void setGameResultFilename(String filepath) {

		this.gameResultFilename = filepath;

	}

	/**
	 * ゲームの中断データのファイルパスの設定
	 * @param interruptionFilepath ファイルパス
	 */
	public void setInterruptionFilename(String interruptionFilepath) {

		this.interruptionFilename = interruptionFilepath;

	}

	/**
	 * ゲーム実行
	 * @return 終了コード SUCCESSFUL:正常終了 ABEND:異常終了
	 */
	public int run() {

		int result = SUCCESSFUL;
		Keyboard keyin = new Keyboard();

		try {
			// ゲームの再開処理
			data = reStart(keyin);
			// カードを配る
			dealCards();

			do {
				// ターンの表示
				viewTrun();
				//
				if (isInterruption(keyin)) {
					// ゲーム中断
					return result;
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
				data.getDealer().judgement(data.getCpu(), data.getYou());
				// 次のターン
				data.incrementTurn();
				// ゲームの終了判定 手持ちのカードをプレイヤーは持っている場合は繰り返す
			} while (!data.getCpu().isHandEmpty() && !data.getYou().isHandEmpty());
			// ゲームの終了
			viewEnd();
			// ゲームの結果を保存する
			writeGameResult();

		} catch (Exception e) {
			logger.error(e.toString());
			result = ABEND;

		} finally {
			keyin.close();
		}

		return result;

	}

	private void dealCards() {
		while (!data.getDealer().isHandEmpty()) {

			data.getCpu().setHandCard(data.getDealer().getHandCard());

			data.getYou().setHandCard(data.getDealer().getHandCard());
		}
	}

	private boolean isInterruption(Keyboard keyin) {

		FileSerialize<GameData> dataFile = new FileSerialize<GameData>(Main.resource,this.interruptionFilename);

		if (keyin.isInterruption()) {

			try {

				dataFile.write(data);

			} catch (IOException e) {

				logger.error(Main.resource.findByErrorsId( R.ERROR_GAME_DATA_WRITE));
			}
			// ゲーム中断
			return true;
		}

		return false;
	}

	private GameData reStart(Keyboard keyin) {

		FileSerialize<GameData> dataFile = new FileSerialize<GameData>(Main.resource,this.interruptionFilename);

		GameData result = null;

		if (dataFile.isFileCheck()) {
			// ゲーム再開するか問い合わせする
			if (keyin.isReStart()) {

				try {

					result = dataFile.read();

				} catch (IOException e) {
					System.out.println(Main.resource.findByStringsId(R.TEXT_START_RENEW));
				}
			} else {
				System.out.println(Main.resource.findByStringsId(R.TEXT_START_NEW));
			}
		}

		dataFile.delete();

		if (null == result) {

			result = new GameData();

		}

		return result;
	}

	private void viewDeck(Card cCard, Card yCard) {
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_CPU_DECK), cCard));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_YOU_DECK), yCard));
	}

	private void viewEnd() {
		System.out.println(Main.resource.findByStringsId(R.TEXT_RESULT_TITLE));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_RESULT_CPU), data.getCpu().getPost().size()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_RESULT_USER), data.getYou().getPost().size()));

		int r = data.getYou().getPost().size() - data.getCpu().getPost().size();
		if (0 == r) {
			System.out.println(Main.resource.findByStringsId(R.TEXT_RESULT_DRAW));
		} else if (0 < r) {
			System.out.println(Main.resource.findByStringsId(R.TEXT_RESULT_WIN));
		} else {
			System.out.println(Main.resource.findByStringsId(R.TEXT_RESULT_LOSS));
		}

	}

	private void viewTrun() {
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_TITLE), data.getTurn()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_FIELD), data.getDealer().getPost().size()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_CPU), data.getCpu().getHand().size(), data.getCpu().getPost().size()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_USER), data.getYou().getHand().size(), data.getYou().getPost().size()));

	}

	private void writeGameResult() {

		String charSet;

		if (os.startsWith(WINDOWS)) {

			charSet = GameConst.CHAR_WINDOWS;

		} else {

			charSet = GameConst.CHAR_UTF;

		}

		GameResultFile result = new GameResultFile(this.gameResultFilename);

		result.setCharset(charSet);

		result.readAll();

		result.Upadate(data.getYou().getPost().size());

		result.writeAll();

	}

}
