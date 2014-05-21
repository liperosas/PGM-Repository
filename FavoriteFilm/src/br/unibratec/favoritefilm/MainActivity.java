package br.unibratec.favoritefilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements TabListener,
		FavoriteFilmsInterface, OnFilmItemListClick, OnItemClickListener {

	DrawerLayout drawerLayout;
	ActionBarDrawerToggle abdt;
	String[] menuOption;
	String menuTitle;
	ListView listView;
	ArrayAdapter<String> adapterDrawer;
	ViewPager mPager;
	FilmListFragment filmListFragment;
	FavoriteFilmListFragment favoriteFilmListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//		listView = (ListView) findViewById(R.id.left_drawer);
//		abdt = new ActionBarDrawerToggle(this, drawerLayout,
//				R.drawable.ic_drawer, R.string.drawer_open,
//				R.string.drawer_close) {
//
//			@Override
//			public void onDrawerOpened(View drawerView) {
//
//				super.onDrawerOpened(drawerView);
//				getSupportActionBar().setTitle(R.string.drawer_open);
//				supportInvalidateOptionsMenu();
//			}
//
//			@Override
//			public void onDrawerClosed(View view) {
//
//				super.onDrawerClosed(view);
//				getSupportActionBar().setTitle(R.string.drawer_close);
//				supportInvalidateOptionsMenu();
//			}
//		};
//		
//		drawerLayout.setDrawerListener(abdt);
//		menuOption = getResources().getStringArray(R.array.menu_options);
//		adapterDrawer = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuOption);
//		listView.setAdapter(adapterDrawer);
//		listView.setOnItemClickListener(this);
//		
//		getSupportActionBar().setHomeButtonEnabled(true);

		filmListFragment = new FilmListFragment();
		favoriteFilmListFragment = new FavoriteFilmListFragment();

		final ActionBar actionBar = getSupportActionBar();

		mPager = (ViewPager) findViewById(R.id.viewPager);
		mPager.setAdapter(new FilmPagerAdapter(getSupportFragmentManager()));
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab aba1 = actionBar.newTab();
		aba1.setText("Film List");
		aba1.setTabListener(this);

		Tab aba2 = actionBar.newTab();
		aba2.setText("Favorites");
		aba2.setTabListener(this);

		actionBar.addTab(aba1);
		actionBar.addTab(aba2);

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void filmToFavorite(Film film) {
		// TODO Auto-generated method stub
		favoriteFilmListFragment.updateList();
	}

	@Override
	public void itemClicked(Film film) {
		// TODO Auto-generated method stub

		if (isTablet()) {
			FilmDetailFragment fdf = FilmDetailFragment.newInstance(film);

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.viewPagerTablet, fdf).commit();
		} else {
			Intent it = new Intent(this, FilmDetailActivity.class);
			it.putExtra("film", film);
			startActivity(it);
		}
	}

	private boolean isTablet() {
		// TODO Auto-generated method stub
		
		return findViewById(R.id.viewPagerTablet) != null;
	}

	class FilmPagerAdapter extends FragmentPagerAdapter {

		public FilmPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			if (position == 0) {
				return filmListFragment;
			}
			return favoriteFilmListFragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
//		if(abdt.onOptionsItemSelected(item)){
//			return true;
//		}
		switch (item.getItemId()) {
		case R.id.menu_search:
			Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}
