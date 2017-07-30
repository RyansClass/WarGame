package jp.ryans.factory.codecamp.wargame.data;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ゲームファイルをアクセスする基底クラス
 *
 */
public class FileRoot {

	public static final String FILE_ERROR_NOT_FOUND_FILE = "ファイルが見つかりません。";

	public static final String FILE_ERROR_NOT_OPEN = "ファイルを開くことができません。";

	public static final String FILE_ERROR_CLASS_LAOD = "クラスを復元できません。";

	public static final String FILE_ERROR_CLASS_SAVE = "クラスを保存できません。";

	public static final String FILE_ERROR_READ = "ファイルの読み込みに失敗しました。";

	public static final String FILE_ERROR_WRITE = "ファイルの書き込みに失敗しました。";

	public static final String FILE_ERROR_CLOSE = "ファイルを閉じることができません。";


	/**
	 * ログ出力
	 */
	protected Logger logger = LogManager.getLogger(this.getClass());

	protected String filename = "";

	/**
	 * コンストラクタ　使用不可
	 */
	@SuppressWarnings("unused")
	private FileRoot() {
	}

	/**
	 * コンストラクタ
	 * @param filename
	 */
	public FileRoot(String filename) {

		this.filename = filename;
	}

	/**
	 * ファイルの存在チェック
	 * @return true:存在する flase:存在しない
	 */
	public boolean isFileCheck() {

		return (new File(filename)).exists();
	}

	/**
	 * ファイルの削除
	 */
	public void delete() {

		File ｆ = new File(filename);

		ｆ.delete();
	}
}
