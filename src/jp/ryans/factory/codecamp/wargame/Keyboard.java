/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.util.Scanner;

/**
 * @author Ryan
 *
 */
public class Keyboard {
	/**
	 * 入力コマンド
	 */
	public static final String INPUT_Y = "y";
	public static final String INPUT_N = "n";
	public static final String INPUT_D = "d";
	public static final String INPUT_Q = "q";

	public static final String PROMPT_RESTART = "中断したゲームを再開しますか? [ %s(Yes)/ %s(No)] >";

	public static final String PROMPT_INTERRRUPT = "札を切りますか? (%s:札を切る, %s:中断) >";

	public static final String RESTART_MESSAGE = "中断したゲームを再開します。";

	public static final String INTERRRUPT_MESSAGE = "dまたはqの文字を入力してください。";


	/**
	 * スキャナー
	 */
	private Scanner scanner = null;

	/**
	 *
	 */
	public Keyboard() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * スキャナーを閉じる
	 */
	public void close() {
		this.scanner.close();
	}

	public boolean isReStart() {
		while (true) {
			System.out.print( String.format( PROMPT_RESTART ,INPUT_Y, INPUT_N) );
			String str = scanner.next();

			if (INPUT_Y.equalsIgnoreCase(str)) {
				// yが入力されたらゲームの再開
				System.out.println(RESTART_MESSAGE);
				return true;
			} else if (INPUT_N.equalsIgnoreCase(str)) {
				// nが入力されたら新規にゲームを開始
				return false;
			}
		}
	}

	public boolean isInterruption() {
		while (true) {
			System.out.print( String.format( PROMPT_INTERRRUPT ,INPUT_D, INPUT_Q) );
			String str = scanner.next();

			if (INPUT_D.equalsIgnoreCase(str)) {
				// dが入力されたら札を切る
				return false;
			} else if (INPUT_Q.equalsIgnoreCase(str)) {
				// qが入力されたらゲームを中断
				return true;
			}
			System.out.println(INTERRRUPT_MESSAGE);

		}
	}

}
