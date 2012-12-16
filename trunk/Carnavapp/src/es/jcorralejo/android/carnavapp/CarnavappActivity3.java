package es.jcorralejo.android.carnavapp;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

public class CarnavappActivity3 extends SherlockFragmentActivity{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

		List<NamedFragment> fragments = new Vector<NamedFragment>();
		fragments.add(ConcursoFragment.newInstance("Preeliminares"));
		fragments.add(ConcursoFragment.newInstance("Cuartos de Final"));
		fragments.add(ConcursoFragment.newInstance("Semifinales"));
		fragments.add(ConcursoFragment.newInstance("Final"));
        FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }


}
