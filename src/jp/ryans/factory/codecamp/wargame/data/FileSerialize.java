/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jp.ryans.factory.codecamp.wargame.resource.R;
import jp.ryans.factory.codecamp.wargame.resource.Strings;

/**
 * シリアライズしたクラスをファイルへ保存または読み込むクラス
 */
public class FileSerialize<T> extends FileRoot {

	/**
	 * コンストラクタ
	 * @param filename ファイル名
	 */
	public FileSerialize(Strings r, String filename) {

		super(r, filename);

	}

	/**
	 * ファイルからデータクラスを読み込む
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public T read() throws IOException {

		T reslt = null;

		ObjectInputStream fInput;

		try {

			fInput = new ObjectInputStream(new FileInputStream(filename));

		} catch (FileNotFoundException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_NOT_FOUND_FILE) + "file={}", filename);

			throw new IOException(e);

		} catch (IOException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_NOT_OPEN) + "file={}", filename);

			throw e;
		}
		try {

			reslt = (T) fInput.readObject();

		} catch (ClassNotFoundException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_CLASS_LAOD) + " {}", e.getMessage());

			throw new IOException(e);

		} catch (IOException e) {

			throw e;

		} finally {

			try {

				fInput.close();

			} catch (IOException e) {

				logger.error(resource.findByErrorsId(R.FILE_ERROR_CLOSE) + "file={}", filename);
			}
		}

		return reslt;
	}

	/**
	 * データクラスをファイルへ書き込む
	 * @param data データクラス
	 * @throws IOException
	 */
	public void write(T data) throws IOException {

		ObjectOutputStream fOutput = null;

		try {

			fOutput = new ObjectOutputStream(new FileOutputStream(filename, false));

		} catch (FileNotFoundException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_NOT_FOUND_FILE) + "file={}", filename);

			throw new IOException(e);

		} catch (IOException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_NOT_OPEN) + "file={}", filename);

			throw e;
		}

		try {

			fOutput.writeObject(data);

		} catch (IOException e) {

			logger.error(resource.findByErrorsId(R.FILE_ERROR_CLASS_SAVE) + " {}", e.getMessage());

			throw e;

		} finally {

			try {

				fOutput.close();

			} catch (IOException e) {

				logger.error(resource.findByErrorsId(R.FILE_ERROR_CLOSE) + "file={}", filename);
			}
		}
	}

}
