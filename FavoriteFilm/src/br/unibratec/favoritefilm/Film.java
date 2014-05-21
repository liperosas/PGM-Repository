package br.unibratec.favoritefilm;

import java.io.Serializable;

public class Film implements Serializable {

	public long id;
	public String title;
	public String director;
	public String running_time;
	public String synopsis;
	public String trailer;
	public String imageFilm;
	public boolean favorite;

	// construtor para o banco
	public Film(long id, String title, String director, String running_time,
			String synopsis, String trailer, String imageFilm) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.running_time = running_time;
		this.synopsis = synopsis;
		this.trailer = trailer;
		this.imageFilm = imageFilm;
	}

	// construtor para json - http
	public Film(String title, String director, String running_time,
			String synopsis, String trailer, String imageFilm) {
		super();
		this.title = title;
		this.director = director;
		this.running_time = running_time;
		this.synopsis = synopsis;
		this.trailer = trailer;
		this.imageFilm = imageFilm;
	}

	public String toString() {
		return title;
	}
}
