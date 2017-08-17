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


	private String path = "resource/string/%s/strings.json";

	public JsonFile(String loc) {

		path = String.format(path, loc);

	}

	public T loadFile(Class<T> classOfT) {

		Gson gson = new Gson();

		byte[] buffer;

		String content = "";

		try {

			buffer = Files.readAllBytes(Paths.get(path));

			content = new String(buffer, StandardCharsets.UTF_8);

		} catch (IOException e) {

			return null;

		}

		return  gson.fromJson(content, classOfT);

	}

}
