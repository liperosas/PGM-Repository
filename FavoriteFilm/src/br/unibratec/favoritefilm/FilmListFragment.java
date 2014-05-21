package br.unibratec.favoritefilm;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilmListFragment extends ListFragment {

	List<Film> films;
	ArrayAdapter<Film> adapter;
	FilmAsyncTask task;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		if (films == null) {
			films = new ArrayList<Film>();
		}

		adapter = new FilmAdapter(getActivity(), films);
		setListAdapter(adapter);

		if (task == null) {
			task = new FilmAsyncTask();
			task.execute();
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(listView, view, position, id);

		if (getActivity() instanceof OnFilmItemListClick) {
			((OnFilmItemListClick) getActivity()).itemClicked(films
					.get(position));
		}
	}

	class FilmAsyncTask extends AsyncTask<Void, Void, List<Film>> {

		@Override
		protected List<Film> doInBackground(Void... params) {

			try {

				HttpURLConnection connect = FilmHttp.connectToJson();

				if (connect.getResponseCode() == HttpURLConnection.HTTP_OK) {
					return FilmHttp.loadFilm(connect.getInputStream());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<Film> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (result != null) {
				films.clear();
				films.addAll(result);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
