package br.unibratec.favoritefilm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class FilmDetailFragment extends Fragment {

	Film film;
	FilmDB filmDB;
	TextView txtTitle;
	TextView txtDirector;
	TextView txtRunningTime;
	TextView txtSynopsis;
	TextView txtTrailer;
	ImageView imageFilm;

	public static FilmDetailFragment newInstance(Film film) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("film", film);

		FilmDetailFragment ffd = new FilmDetailFragment();
		ffd.setArguments(bundle);

		return ffd;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
		filmDB = new FilmDB(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View layout = inflater.inflate(R.layout.films_detail_fragment, null);

		txtTitle = (TextView) layout.findViewById(R.id.filmTitle);
		txtDirector = (TextView) layout.findViewById(R.id.filmDirector);
		txtRunningTime = (TextView) layout.findViewById(R.id.filmRunningTime);
		txtSynopsis = (TextView) layout.findViewById(R.id.filmSynopsis);
		txtTrailer = (TextView) layout.findViewById(R.id.filmTrailer);
		imageFilm = (ImageView) layout.findViewById(R.id.imgFilm);

		film = (Film) getArguments().getSerializable("film");
		film.favorite = filmDB.favorite(film);
		txtTitle.setText(film.title);
		txtDirector.setText(film.director);
		txtRunningTime.setText(film.running_time);
		txtSynopsis.setText(film.synopsis);
		txtTrailer.setText(film.trailer);
		Picasso.with(getActivity()).load(film.imageFilm).noFade()
				.into(imageFilm);

		return layout;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.film_detail, menu);

		MenuItem menuItemFavorite = menu.findItem(R.id.menuFavorites);
		if (film.favorite) {
			menuItemFavorite.setIcon(android.R.drawable.ic_menu_delete);
		} else {
			menuItemFavorite.setIcon(android.R.drawable.ic_menu_save);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.menuFavorites:
			if (film.favorite) {
				filmDB.removeFilm(film);
				Toast.makeText(getActivity(),
						"Film successfully removed from favorites",
						Toast.LENGTH_SHORT).show();
				;
			} else {
				filmDB.insertFilm(film);
				Toast.makeText(getActivity(),
						"Film successfully added to favorites",
						Toast.LENGTH_SHORT).show();
				;
			}

			if (getActivity() instanceof FavoriteFilmsInterface) {
				((FavoriteFilmsInterface) getActivity()).filmToFavorite(film);
			}

			((ActionBarActivity) getActivity()).supportInvalidateOptionsMenu();
			break;

		// case R.id.menu_share:
		//
		// break;
		}
		return super.onOptionsItemSelected(item);

	}
}
