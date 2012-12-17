package es.jcorralejo.android.carnavapp;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	private List<NamedFragment> fragments;
	
	public PagerAdapter(FragmentManager fm, List<NamedFragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}
	
	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return  fragments.get(position).getTitulo();
	}
	
	public List<NamedFragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<NamedFragment> fragments) {
		this.fragments = fragments;
		notifyDataSetChanged();
	}
	
}