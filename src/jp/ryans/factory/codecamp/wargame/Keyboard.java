/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

import java.util.Scanner;

import jp.ryans.factory.codecamp.wargame.resource.R;

/**
 * @author Ryan
 *
 */
public class Keyboard {
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
			System.out.print( String.format(Main.resource.findByPromptId( R.PROMPT_RESTART)  ,Main.resource.findByPromptId(R.INPUT_Y), Main.resource.findByPromptId(R.INPUT_N)) );
			String str = scanner.next();

			if (Main.resource.findByPromptId(R.INPUT_Y).equalsIgnoreCase(str)) {
				// yが入力されたらゲームの再開
				System.out.println(Main.resource.findByStringsId(R.RESTART_MESSAGE));
				return true;
			} else if (Main.resource.findByPromptId(R.INPUT_N).equalsIgnoreCase(str)) {
				// nが入力されたら新規にゲームを開始
				return false;
			}
		}
	}

	public boolean isInterruption() {
		while (true) {
			System.out.print( String.format(Main.resource.findByPromptId( R.PROMPT_INTERRRUPT) ,Main.resource.findByPromptId(R.INPUT_D), Main.resource.findByPromptId(R.INPUT_Q)) );
			String str = scanner.next();

			if (Main.resource.findByPromptId(R.INPUT_D).equalsIgnoreCase(str)) {
				// dが入力されたら札を切る
				return false;
			} else if (Main.resource.findByPromptId(R.INPUT_Q).equalsIgnoreCase(str)) {
				// qが入力されたらゲームを中断
				return true;
			}
			System.out.println( Main.resource.findByErrorsId(R.INTERRRUPT_MESSAGE));

		}
	}

}
