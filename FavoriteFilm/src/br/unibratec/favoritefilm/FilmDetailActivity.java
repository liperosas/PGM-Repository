package br.unibratec.favoritefilm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

public class FilmDetailActivity extends ActionBarActivity {

	private ShareActionProvider mShareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.film_detail_activity);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		Film film = (Film) getIntent().getSerializableExtra("film");

		FilmDetailFragment fdm = FilmDetailFragment.newInstance(film);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragTeste, fdm).commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.film_detail_activity_menu, menu);

		MenuItem shareItem = menu.findItem(R.id.menu_share);
		mShareActionProvider = (ShareActionProvider) MenuItemCompat
				.getActionProvider(shareItem);
		mShareActionProvider.setShareIntent(getDefaultIntent());

		return true;
	}

	private Intent getDefaultIntent() {
		// TODO Auto-generated method stub
		Film film = (Film) getIntent().getSerializableExtra("film");
		Intent intent = new Intent(Intent.ACTION_SEND);
		Uri screenshotUri = Uri.parse(film.imageFilm);
		intent.setType("*/*");
		intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
		intent.putExtra(intent.EXTRA_TEXT, "Title: " + film.title.toString()
				+ "\nDirector: " + film.director.toString()
				+ "\nRunning Time: " + film.running_time.toString()
				+ "\nTrailer: " + film.trailer.toString());
		return intent;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.home:
			return true;
			// case R.id.action_settings:
			// Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT)
			// .show();
			// break;
		}
		return super.onOptionsItemSelected(item);
	}
}
