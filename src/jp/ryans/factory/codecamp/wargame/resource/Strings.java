/**
 *
 */
package jp.ryans.factory.codecamp.wargame.resource;

import com.google.gson.Gson;

/**
 * @author Ryan
 *
 */
public class Strings {

	public class IdSet {

		public int id;

		public String text;


		public IdSet() {

		}

		public IdSet(int id,String text) {
			this.id = id;
			this.text = text;
		}
	}


	public class Json {


		public String name;


		public IdSet ids;

		public Json() {

		}
		public Json(String name,IdSet ids) {

			this.name = name;

			this.ids = new IdSet(ids.id,ids.text);
		}
	}

	private String path;

	private Gson gson;

	/**
	 *
	 */
	public Strings(String local) {
		path = "resource/string/" + local + "string.json";
	}

	public String findByResource(String name,int id) {
		String result = "";
		Gson gson = new Gson();



		return result;
	}



}
