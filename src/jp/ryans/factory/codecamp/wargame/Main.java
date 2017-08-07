/**
 * Main.java
 */
package jp.ryans.factory.codecamp.wargame;

/**
 * ゲーム メインクラス
 */
public class Main {

	/**
	 * OS名の環境変数名
	 */
	private static final String ENV_OS = "os.name";

	/**
	 * ゲームの結果保存ファイル名
	 */
	private static final String GAMEDATA_FILEPATH = "wargame.dat";

	/**
	 * ゲームの中断データ保存ファイル名
	 */
	private static final String INTERRUPTION_FILEPATH = "wargame.dat";

	/**
	 * 実行メッソド
	 * @param args 実行時引数
	 */
	public static void main(String[] args) {

		String os = "windows";

		try{
			// 環境変数からOSの名前を取得する
			os = System.getProperty(ENV_OS).toLowerCase();

		} catch(SecurityException e) {
			// セキュリティー違反
			System.exit(1);

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
