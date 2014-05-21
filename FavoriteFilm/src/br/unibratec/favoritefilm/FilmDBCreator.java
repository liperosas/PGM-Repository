package br.unibratec.favoritefilm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmDBCreator extends SQLiteOpenHelper {

	private static final String FILM_DATABASE = "filmsDB";
	private static final int DATABASE_VERSION = 1;

	public FilmDBCreator(Context context) {
		super(context, FILM_DATABASE, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE films (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "title TEXT NOT NULL, director TEXT, running_time TEXT, synopsis TEXT, trailer TEXT, image TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
