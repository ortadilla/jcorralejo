package es.jcorralejo.android.carnavapp;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

public class CarnavappActivity3 extends SherlockFragmentActivity{
	
	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums", "Songs", "Playlists", "Genres" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

		List<Fragment> fragments = new Vector<Fragment>();
		List<String> nombres = new Vector<String>();
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		nombres.add("Preeliminares");
		nombres.add("Cuartos");
		nombres.add("Semifinales");
		nombres.add("Final");
        FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragments, nombres);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }


}
