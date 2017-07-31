/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.supercsv.prefs.CsvPreference;

import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanWriter;

/**
 *
 */
public class GameResultFile {

	private List<GameResult> list;

	/**
	 * コンストラクタ
	 */
	public GameResultFile() {

	}

	public void readAll() {

		CsvAnnotationBeanReader<GameResult> csvReader = null;

		try {
			csvReader = new CsvAnnotationBeanReader<GameResult>(
					GameResult.class,
					Files.newBufferedReader(new File("game_result.csv").toPath(), Charset.forName("Windows-31j")),
					CsvPreference.STANDARD_PREFERENCE);

		} catch (IOException e) {

		}

		try {

			list = csvReader.readAll();

		} catch (IOException e) {

		}

		try {

			csvReader.close();

		} catch (IOException e) {

		}
	}

	public void writeAll() {

		CsvAnnotationBeanWriter<GameResult> csvWriter = null;

		try {

			csvWriter = new CsvAnnotationBeanWriter<GameResult>(
					GameResult.class,
					Files.newBufferedWriter(new File("game_result.csv").toPath(), Charset.forName("Windows-31j")),
					CsvPreference.STANDARD_PREFERENCE);

		} catch (IOException e) {

		}

		// ヘッダー行と全レコードデータの書き込み
		try {

			csvWriter.writeAll(list);

		} catch (IOException e) {

		}

		try {

			csvWriter.close();

		} catch (IOException e) {

		}
	}

}
