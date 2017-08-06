/**
 *
 */
package jp.ryans.factory.codecamp.wargame;

/**
 *
 */
public class Main {


	public static void main(String[] args) {

		String os = System.getProperty("os.name").toLowerCase();


		WarGame game = new WarGame(os);

		game.run();
	}

}
