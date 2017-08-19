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

		String os = GameConst.WINDOWS;

		try{
			// 環境変数からOSの名前を取得する
			os = System.getProperty(GameConst.ENV_OS).toLowerCase();

		} catch(SecurityException e) {
			// セキュリティー違反
			System.exit(GameConst.ABEND);

		}
		// ゲームクラスを生成する
		WarGame game = new WarGame(os);
		// ゲームの結果保存ファイルを設定する
		game.setGameResultFilename(GameConst.GAMEDATA_FILEPATH);
		// ゲームの中断データ保存ファイルを設定する
		game.setInterruptionFilename(GameConst.INTERRUPTION_FILEPATH);
		// ゲームの実行
		int status = game.run();
		// プログラム終了
		System.exit(status);
	}

}
