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
			System.out.print( String.format(Main.resource.findByPromptId( WarGame.PROMPT_RESTART)  ,INPUT_Y, INPUT_N) );
			String str = scanner.next();

			if (INPUT_Y.equalsIgnoreCase(str)) {
				// yが入力されたらゲームの再開
				System.out.println(Main.resource.findByStringsId(WarGame.RESTART_MESSAGE));
				return true;
			} else if (INPUT_N.equalsIgnoreCase(str)) {
				// nが入力されたら新規にゲームを開始
				return false;
			}
		}
	}

	public boolean isInterruption() {
		while (true) {
			System.out.print( String.format(Main.resource.findByPromptId( WarGame.PROMPT_INTERRRUPT) ,INPUT_D, INPUT_Q) );
			String str = scanner.next();

			if (INPUT_D.equalsIgnoreCase(str)) {
				// dが入力されたら札を切る
				return false;
			} else if (INPUT_Q.equalsIgnoreCase(str)) {
				// qが入力されたらゲームを中断
				return true;
			}
			System.out.println( Main.resource.findByErrorsId(WarGame.INTERRRUPT_MESSAGE));

		}
	}

}
