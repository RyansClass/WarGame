/**
 * Main.java
 *
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.util.Locale;

import jp.ryans.factory.codecamp.wargame.resource.Strings;

/**
 * ゲーム メインクラス
 *
 */
public class Main {

	/**
	 * デフォルトOS
	 */
	public static final String WINDOWS = "windows";

	/**
	 * 正常終了
	 */
	public static final int SUCCESSFUL = 0;

	/**
	 * 異常終了
	 */
	public static final int ABEND = 1;

	/**
	 * OS名の環境変数名
	 */
	private static final String ENV_OS = "os.name";

	/**
	 * ゲームの結果保存ファイル名
	 */
	private static final String GAMEDATA_FILEPATH = "game_result.csv";

	/**
	 * ゲームの中断データ保存ファイル名
	 */
	private static final String INTERRUPTION_FILEPATH = "wargame.dat";


	/**
	 * 国際化対応リソース
	 */
	public static Strings resource;


	/**
	 * 実行メッソド
	 * @param args 言語指定
	 */
	public static void main(String[] args) {

		// 実行時に引数があれば言語指定
		if ( 0 == args.length ) {
			// デフォルトの言語
			resource = Strings.getInstance(Locale.getDefault().getLanguage());
		} else {
			// 言語指定
			resource = Strings.getInstance(args[0]);
		}

		String os = WINDOWS;

		try{
			// 環境変数からOSの名前を取得する
			os = System.getProperty(ENV_OS).toLowerCase();

		} catch(SecurityException e) {
			// セキュリティー違反
			System.exit(ABEND);

		}
		// ゲームクラスを生成する
		WarGame game = new WarGame(os);
		// ゲームの結果保存ファイルを設定する
		game.setGameResultFilename(GAMEDATA_FILEPATH);
		// ゲームの中断データ保存ファイルを設定する
		game.setInterruptionFilename(INTERRUPTION_FILEPATH);
		// ゲームの実行
		int status = game.run();
		// プログラム終了
		System.exit(status);
	}

}
