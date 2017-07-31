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

/**
 *
 */
public class GameResultFile {


	private List<GameResult> list;

	/**
	 *
	 */
	public GameResultFile() {
		// TODO 自動生成されたコンストラクター・スタブ
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

}
