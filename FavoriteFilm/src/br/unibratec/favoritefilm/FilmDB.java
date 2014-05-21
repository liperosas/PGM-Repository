package br.unibratec.favoritefilm;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FilmDB {

	private FilmDBCreator helper;

	public FilmDB(Context context) {
		helper = new FilmDBCreator(context);
	}

	public long insertFilm(Film film) {

		SQLiteDatabase sqlBD = helper.getWritableDatabase();
		ContentValues contentValues = filmDATA(film);

		long id = sqlBD.insert("films", null, contentValues);
		film.id = id;
		film.favorite = true;

		sqlBD.close();
		return id;

	}

	private ContentValues filmDATA(Film film) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("title", film.title);
		contentValues.put("director", film.director);
		contentValues.put("running_time", film.running_time);
		contentValues.put("synopsis", film.synopsis);
		contentValues.put("trailer", film.trailer);
		contentValues.put("image", film.imageFilm);

		return contentValues;
	}

	public int removeFilm(Film film) {

		SQLiteDatabase sqlDB = helper.getWritableDatabase();
		int rows = sqlDB.delete("films", "_id = " + film.id, null);
		film.favorite = false;
		sqlDB.close();

		return rows;
	}

	public List<Film> filmsList() {

		List<Film> films = new ArrayList<Film>();
		SQLiteDatabase sqlBD = helper.getReadableDatabase();

		Cursor cursor = sqlBD.rawQuery("SELECT * from films", null);

		while (cursor.moveToNext()) {
			Film film = loadFilms(cursor);
			films.add(film);
		}

		cursor.close();
		sqlBD.close();

		return films;

	}

	private Film loadFilms(Cursor cursor) {

		long id = cursor.getLong(0);
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String director = cursor.getString(cursor.getColumnIndex("director"));
		String running_time = cursor.getString(cursor
				.getColumnIndex("running_time"));
		String synopsis = cursor.getString(cursor.getColumnIndex("synopsis"));
		String trailer = cursor.getString(cursor.getColumnIndex("trailer"));
		String imageFilm = cursor.getString(cursor.getColumnIndex("image"));

		Film film = new Film(id, title, director, running_time, synopsis,
				trailer, imageFilm);
		film.favorite = true;

		return film;
	}

	public boolean favorite(Film film) {

		SQLiteDatabase sqlDB = helper.getReadableDatabase();
		Cursor cursor = sqlDB.rawQuery("SELECT * from films where title =?",
				new String[] { film.title });

		boolean result = false;
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToNext();
			film.id = cursor.getLong(cursor.getColumnIndex("_id"));
			result = true;
		}

		cursor.close();
		sqlDB.close();

		return result;
	}

}
