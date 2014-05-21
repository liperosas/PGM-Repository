package br.unibratec.favoritefilm;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmAdapter extends ArrayAdapter<Film> {

	List<Film> films;

	public FilmAdapter(Context context, List<Film> films) {
		super(context, 0, films);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Film getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// ViewHolder viewHolder;
		Film film = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_film, null);
		}

		ImageView imageFilm = (ImageView) convertView
				.findViewById(R.id.imgFilm);
		TextView title = (TextView) convertView.findViewById(R.id.filmTitle);
		TextView running_time = (TextView) convertView
				.findViewById(R.id.filmRunningTime);

		Picasso.with(getContext()).load(film.imageFilm).noFade()
				.into(imageFilm);
		title.setText(film.title);
		running_time.setText(film.running_time);

		// viewHolder = new ViewHolder();
		// viewHolder.holderFilmTitle = (TextView) convertView
		// .findViewById(R.id.filmTitle);
		// viewHolder.holderFilmRunningTime = (TextView) convertView
		// .findViewById(R.id.filmRunningTime);
		// viewHolder.holderImgFilm = (ImageView) convertView
		// .findViewById(R.id.imgFilm);
		// } else {
		// viewHolder = (ViewHolder) convertView.getTag();
		// }

		// Picasso.with(getContext()).load(film.imageFilm).noFade()
		// .into(viewHolder.holderImgFilm);
		//
		// viewHolder.holderFilmTitle.setText(film.title);
		// viewHolder.holderFilmRunningTime.setText(film.running_time);

		return convertView;
	}

	// static class ViewHolder {
	// ImageView holderImgFilm;
	// TextView holderFilmTitle;
	// TextView holderFilmRunningTime;
	// }

}
