package es.jcorralejo.android.carnavapp.activities;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class PagerAdapter extends FragmentPagerAdapter {

	private ViewPager container;
	private FragmentManager fm;
	private List<Fragment> fragments;
	private List<String> titulos;
	
	public PagerAdapter(ViewPager container, FragmentManager fm, List<Fragment> fragments, List<String> titulos) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
		this.titulos = titulos;
		this.container = container;
	}
	
	@Override
	public Fragment getItem(int position) {
		return fragments!=null ? fragments.get(position) : null;
	}

	public int getItemPosition(Object object) {
		return fragments!=null && fragments.contains(object) ? fragments.indexOf(object) : POSITION_UNCHANGED;
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
	
	
    public void replace(int position, Fragment fragment) {
        // Get currently active fragment.
        Fragment old_fragment = fragments.get(position);
        if (old_fragment == null) {
          return;
        }
        // Replace the fragment using transaction and in underlaying array list.
        startUpdate(container);
//        fm.beginTransaction().hide(old_fragment).add(fragment, fragment.getTag()).commit();
        
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				             .remove(old_fragment).add(container.getId(), fragment)
				             .commit();
        fragments.set(position, fragment);
        notifyDataSetChanged();
        finishUpdate(container);
      }
}