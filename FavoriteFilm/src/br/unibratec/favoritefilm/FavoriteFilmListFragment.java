package br.unibratec.favoritefilm;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FavoriteFilmListFragment extends ListFragment {

	List<Film> films;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		OnItemLongClickListener longListener = new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long id) { // TODO Auto-generated method stub
				Toast.makeText(
						getActivity().getBaseContext(),
						"Title: " + films.get(position).title + "\nDirector: "
								+ films.get(position).director
								+ "\nRunning Time: "
								+ films.get(position).running_time,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		};
		getListView().setOnItemLongClickListener(longListener);

		setRetainInstance(true);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateList();
	}

	public void updateList() {
		FilmDB filmDB = new FilmDB(getActivity());
		films = filmDB.filmsList();

		FilmAdapter adapter = new FilmAdapter(getActivity(), films);
		setListAdapter(adapter);
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
}
