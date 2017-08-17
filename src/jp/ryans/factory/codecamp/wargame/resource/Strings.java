/**
 *
 */
package jp.ryans.factory.codecamp.wargame.resource;

import java.util.ArrayList;

/**
 *
 */
public class Strings {

	private class IdSetText {

		public int id;

		public String text;

	}

	private static Strings instance;

	public static Strings getInstance(String loc) {

		if (instance == null) {


			JsonFile<Strings> jf = new JsonFile<Strings>(loc);


			instance = jf.loadFile(Strings.class);

		}

		return instance;
	}

	private String app;

	private String version;

	private String description;

	private ArrayList<IdSetText> prompt;

	private ArrayList<IdSetText> strings;

	private ArrayList<IdSetText> errors;

	private Strings() {

	}

	public String findByErrorsId(int id) {
		return findById(id, errors);
	}

	private String findById(int id, ArrayList<IdSetText> list) {
		for (IdSetText idset : list) {
			if (idset.id == id) {
				return idset.text;
			}
		}
		throw new IllegalArgumentException("コーディングミス");
	}

	public String findByPromptId(int id) {
		return findById(id, prompt);
	}

	public String findByStringsId(int id) {
		return findById(id, strings);
	}

	/**
	 * @return app
	 */
	public String getApp() {
		return app;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return version
	 */
	public String getVersion() {
		return version;
	}

}
