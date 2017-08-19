package jp.ryans.factory.codecamp.wargame.data;

import java.io.File;

import jp.ryans.factory.codecamp.wargame.resource.Strings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ゲームファイルをアクセスする基底クラス
 *
 */
public class FileRoot {

	/**
	 * ログ出力
	 */
	protected Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * ファイルパス
	 */
	protected String filename;

	/**
	 * 国際化対応リソース
	 */
	protected Strings resource;

	/**
	 * コンストラクタ　使用不可
	 */
	@SuppressWarnings("unused")
	private FileRoot() {

	}

	/**
	 * コンストラクタ
	 * @param r 国際化対応リソース
	 * @param filename ファイルパス
	 */
	public FileRoot(Strings r,String filename) {
		this.resource = r;
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
