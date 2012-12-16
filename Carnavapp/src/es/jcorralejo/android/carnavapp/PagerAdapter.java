package es.jcorralejo.android.carnavapp;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	private List<String> nombres;

	public PagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> nombes) {
		super(fm);
		this.fragments = fragments;
		this.nombres = nombes;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return  this.nombres.get(position);
	}
}