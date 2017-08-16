/**
 *
 */
package jp.ryans.factory.codecamp.wargame.resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

/**
 * @author Ryan
 *
 */
public class JsonFile<T> {

	/**
	 *
	 */
	public JsonFile() {

	}

	public T loadFile(Class<T> classOfT) {
		Gson gson = new Gson();
		byte[] buffer;
		String content = "";
		try {
			buffer = Files.readAllBytes(Paths.get("resource/string/ja/strings.json"));
			content = new String(buffer, StandardCharsets.UTF_8);

		} catch (IOException e) {
			return null;
		}


		return  gson.fromJson(content, classOfT);

	}

}
