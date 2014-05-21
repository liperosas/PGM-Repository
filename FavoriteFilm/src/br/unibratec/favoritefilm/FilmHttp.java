package br.unibratec.favoritefilm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FilmHttp {

	public static HttpURLConnection connectToJson() throws IOException {

		URL url = new URL(
				"https://dl.dropboxusercontent.com/u/30273954/Json's%20File/films.json");
		HttpURLConnection openConnection = (HttpURLConnection) url
				.openConnection();
		openConnection.setRequestMethod("GET");
		openConnection.setDoInput(true);
		openConnection.connect();
		return openConnection;
	}

	public static List<Film> loadFilm(InputStream inputStream)
			throws JSONException, IOException {

		List<Film> films = new ArrayList<Film>();
		JSONObject json = new JSONObject(bytesToString(inputStream));
		JSONArray jsonFilmList = json.getJSONArray("films");

		for (int i = 0; i < jsonFilmList.length(); i++) {
			JSONObject jsonFilm = jsonFilmList.getJSONObject(i);
			
//			Film film = new Film(jsonFilm.getString("title"),
//					jsonFilm.getString("director"),
//					jsonFilm.getString("running_time"),
//					jsonFilm.getString("synopsis"),
//					jsonFilm.getString("trailer"), jsonFilm.getString("image"));
//			films.add(film);
			
			String title = jsonFilm.getString("title");
			String director = jsonFilm.getString("director");
			String running_time = jsonFilm.getString("running_time");
			String synopsis = jsonFilm.getString("synopsis");
			String trailer = jsonFilm.getString("trailer");
			String image = jsonFilm.getString("image");
			
			Film film = new Film(title, director, running_time, synopsis, trailer, image);
			films.add(film);
			
		}
		return films;
	}

	// ler cada byte do Json e retornar em String
	private static String bytesToString(InputStream inputStream)
			throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int read;
		while ((read = inputStream.read(bytes)) > 0) {
			baos.write(bytes, 0, read);
		}
		return new String(baos.toByteArray());
	}

}
