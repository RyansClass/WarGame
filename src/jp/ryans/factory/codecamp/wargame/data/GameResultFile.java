/**
 *
 */
package jp.ryans.factory.codecamp.wargame.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.exception.SuperCsvException;
import org.supercsv.prefs.CsvPreference;

import com.github.mygreen.supercsv.exception.SuperCsvBindingException;
import com.github.mygreen.supercsv.exception.SuperCsvNoMatchColumnSizeException;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanWriter;

/**
 *
 */
public class GameResultFile {

	private String filepath = "game_result.csv";

	private String charset = "UTF-8";

	private List<GameResult> list;

	/**
	 * コンストラクタ
	 */
	public GameResultFile(String filepath) {

		this.filepath = filepath;
	}

	public void readAll() {

		CsvAnnotationBeanReader<GameResult> csvReader = null;

		File f = new File(filepath);

		if ( ! f.exists() ) {
			list = new ArrayList<GameResult>();

			list.add( new GameResult(0,0,0) );

			return;
		}

		try {
			csvReader = new CsvAnnotationBeanReader<GameResult>(
					GameResult.class,
					Files.newBufferedReader(f.toPath(), Charset.forName(this.charset)),
					CsvPreference.STANDARD_PREFERENCE);

		} catch (IOException e) {

		}

		try {

			list = csvReader.readAll(true);

		}catch(SuperCsvNoMatchColumnSizeException e) {
			list = new ArrayList<GameResult>();

			list.add( new GameResult(0,0,0) );

			return;

		}catch(SuperCsvBindingException e) {
			list = new ArrayList<GameResult>();

			list.add( new GameResult(0,0,0) );

			return;

		}catch(SuperCsvException  e) {
			list = new ArrayList<GameResult>();

			list.add( new GameResult(0,0,0) );

			return;

		}catch (IOException e) {
			list = new ArrayList<GameResult>();

			list.add( new GameResult(0,0,0) );

			return;

		}

		close(csvReader);
	}

	public void writeAll() {

		CsvAnnotationBeanWriter<GameResult> csvWriter = null;

		try {

			csvWriter = new CsvAnnotationBeanWriter<GameResult>(
					GameResult.class,
					Files.newBufferedWriter(new File(filepath).toPath(), Charset.forName(this.charset)),
					CsvPreference.STANDARD_PREFERENCE);

		} catch (IOException e) {

		}

		// ヘッダー行と全レコードデータの書き込み
		try {
			csvWriter.writeHeader();

			csvWriter.writeAll(list);

		} catch (IOException e) {

		}

		close(csvWriter);
	}

	private void close(Object obj) {

		if ( obj instanceof CsvAnnotationBeanWriter) {
			@SuppressWarnings("unchecked")
			CsvAnnotationBeanWriter<GameResult> s = (CsvAnnotationBeanWriter<GameResult>) obj;

			try {
				s.close();

			} catch (IOException e) {

			}
		} else if( obj instanceof CsvAnnotationBeanReader) {
			@SuppressWarnings("unchecked")
			CsvAnnotationBeanReader<GameResult> s = (CsvAnnotationBeanReader<GameResult>) obj;
			try {
				s.close();

			} catch (IOException e) {

			}
		}
	}

	/**
	 * @return list
	 */
	public List<GameResult> getList() {
		return list;
	}

	public void Upadate(int size) {
		GameResult data;

		if( list.isEmpty() ) {
			data = new GameResult(0,0,0);
		} else {
			data = list.get(0);
		}

		data.setGames(data.getGames()+1 );

		if( size > data.getMaxPossession() ) {
			data.setMaxPossession(size);
		}
		if( list.isEmpty() ) {
			list.add(data);
		}

	}

	public void setCharset(String scvCharset) {
		this.charset = scvCharset;

	}


}
