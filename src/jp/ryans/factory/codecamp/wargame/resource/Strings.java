/**
 *
 */
package jp.ryans.factory.codecamp.wargame.resource;

import java.util.ArrayList;


/**
 *
 */
public class Strings {



	public class IdSetText {

		public int id;

		public String text;


		public IdSetText() {

		}

	}


	public String app;

	public String version;

	public String description;

	public ArrayList<IdSetText> prompt;

	public ArrayList<IdSetText> strings;

	public ArrayList<IdSetText> errors;


	public String findByPromptId(int id) {
		return findById(id,prompt);
	}
	public String findByStringsId(int id) {
		return findById(id,strings);
	}
	public String findByErrorsId(int id) {
		return findById(id,errors);
	}

	private String findById(int id,ArrayList<IdSetText> list) {
		for(IdSetText idset:  list) {
			if( idset.id == id ) {
				return idset.text;
			}
		}
		throw new IllegalArgumentException("コーディングミス");
	}

}
