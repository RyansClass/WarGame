/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

/**
 * @author Ryan
 *
 */
public class GameConst {


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
	public static final String ENV_OS = "os.name";

	/**
	 * ゲームの結果保存ファイル名
	 */
	public static final String GAMEDATA_FILEPATH = "game_result.csv";

	/**
	 * ゲームの中断データ保存ファイル名
	 */
	public static final String INTERRUPTION_FILEPATH = "wargame.dat";

	/**
	 * Windowsの文字コード
	 */
	public static final String CHAR_WINDOWS = "Windows-31j";

	/**
	 * MacもしくはLinuxの文字コード
	 */
	public static final String CHAR_UTF = "UTF-8";


}
