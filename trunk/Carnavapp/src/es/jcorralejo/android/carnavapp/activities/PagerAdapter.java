package es.jcorralejo.android.carnavapp.activities;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	private List<String> titulos;
	
	public PagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titulos) {
		super(fm);
		this.fragments = fragments;
		this.titulos = titulos;
	}
	
	@Override
	public Fragment getItem(int position) {
		return fragments!=null ? fragments.get(position) : null;
	}

	@Override
	public int getCount() {
		return fragments!=null ? fragments.size() : 0;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return titulos!=null ? titulos.get(position).toUpperCase() : "";
	}
	
	public List<Fragment> getFragments() {
		return fragments;
	}

	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
		notifyDataSetChanged();
	}

	public List<String> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<String> titulos) {
		this.titulos = titulos;
	}
	
}