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
import jp.ryans.factory.codecamp.wargame.resource.R;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ゲームコントロールクラス
 */
public class WarGame {

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
	protected WarGame() {
		this.os = GameConst.WINDOWS;
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
	public void setInterruptionFilename(String filepath) {

		this.interruptionFilename = filepath;

	}

	/**
	 * ゲーム実行
	 * @return 終了コード SUCCESSFUL:正常終了 ABEND:異常終了
	 */
	public int run() {

		int result = GameConst.SUCCESSFUL;
		
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
					System.out.println(Main.resource.findByStringsId(R.TEXT_INTERRRUPT_MESSAGE));
					return result;
				}
				// カードを出す
				Card cCard = data.getCpu().getHandCard();
				Card yCard = data.getYou().getHandCard();
				// デッキの表示
				viewDeck(cCard, yCard);
				// ディーラーへCPUのカードを渡す
				data.getDealer().setHandCard(data.getCpu(),cCard);
				// ディーラーへあなたのカードを渡す
				data.getDealer().setHandCard(data.getYou(),yCard);
				// カードの判定
				viewJudgement( data.getDealer().judgement(data.getCpu(), data.getYou()) );
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
			result = GameConst.ABEND;

		} finally {
			keyin.close();
		}

		return result;

	}


	/**
	 * カードを配る
	 */
	private void dealCards() {
		
		while (!data.getDealer().isHandEmpty()) {

			data.getCpu().setHandCard(data.getDealer().getHandCard());

			data.getYou().setHandCard(data.getDealer().getHandCard());
		}
	}

	/**
	 * 手札を切るか中断か検査
	 * @param keyin
	 * @return
	 */
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

	/**
	 * ゲームをリスタートするか検査
	 * @param keyin
	 * @return
	 */
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

	/**
	 * デッキの表示
	 * @param cCard
	 * @param yCard
	 */
	private void viewDeck(Card cCard, Card yCard) {
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_DECK), data.getCpu().getName(),cCard));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_DECK), data.getYou().getName(),yCard));
	}

	/**
	 * ゲームの終了表示
	 */
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

	/**
	 * ゲームのターン表示
	 */
	private void viewTrun() {
		System.out.println("");
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_TITLE), data.getTurn()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_FIELD), data.getDealer().getPost().size()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_PLAYER), data.getCpu().getName(),data.getCpu().getHand().size(), data.getCpu().getPost().size()));
		System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_PLAYER), data.getYou().getName(),data.getYou().getHand().size(), data.getYou().getPost().size()));

	}
	
	/**
	 * ゲームのターン結果表示
	 * @param judgement
	 */
	private void viewJudgement(int judgement) {
		switch (judgement ) {

		case CardRules.WIN:
			
			System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_JUDG),data.getYou().getName()));

			break;

		case CardRules.LOSS:
			
			System.out.println(String.format(Main.resource.findByStringsId(R.TEXT_TUEN_JUDG),data.getCpu().getName()));

			break;

		case CardRules.DRAW:
			
			System.out.println(Main.resource.findByStringsId(R.TEXT_TUEN_DRAW));

			break;

		}
		
	}


	/**
	 * ゲームの結果保存
	 */
	private void writeGameResult() {

		GameResultFile result = new GameResultFile(this.gameResultFilename);

		if (os.startsWith(GameConst.WINDOWS)) {

			result.setCharset(GameConst.CHAR_WINDOWS);

		} else {

			result.setCharset(GameConst.CHAR_UTF);

		}

		result.readAll();

		result.Upadate(data.getYou().getPost().size());

		result.writeAll();

	}

}
