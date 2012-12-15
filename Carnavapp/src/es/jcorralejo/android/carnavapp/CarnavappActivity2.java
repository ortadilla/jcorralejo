package es.jcorralejo.android.carnavapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class CarnavappActivity2 extends SherlockFragmentActivity implements OnTabChangeListener, OnPageChangeListener{
	//http://thepseudocoder.wordpress.com/2011/10/13/android-tabs-viewpager-swipe-able-tabs-ftw/
	
	private TabHost mTabHost;
	private Map<String, TabInfo> mapTabInfo = new HashMap<String, CarnavappActivity2.TabInfo>();
	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;


	private class TabInfo {
		private String tag;
		private Class clss;
		private Bundle args;
		private Fragment fragment;
		TabInfo(String tag, Class clazz, Bundle args) {
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}
	}

	class TabFactory implements TabContentFactory {

		private final Context mContext;

		public TabFactory(Context context) {
			mContext = context;
		}

		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialiseTabHost(savedInstanceState);
		intialiseViewPager();
		if (savedInstanceState != null) {
			//Cargamos el tabs guardado
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); 
		}
		
	}
	
    private void intialiseViewPager() {
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		fragments.add(SherlockFragment.instantiate(this, ConcursoActivity.class.getName()));
		this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//Guardamos el tab seleccionado
		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();

		TabInfo tabInfo = null;

		String preeliminares = getResources().getString(R.string.preeliminares);
		String cuartos = getResources().getString(R.string.cuartos);
		String semifinales = getResources().getString(R.string.semifinales);
		String fasefinal = getResources().getString(R.string.fasefinal);
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec(preeliminares).setIndicator(preeliminares), ( tabInfo = new TabInfo(preeliminares, ConcursoActivity.class, args)));
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec(cuartos).setIndicator(cuartos), ( tabInfo = new TabInfo(cuartos, ConcursoActivity.class, args)));
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec(semifinales).setIndicator(semifinales), ( tabInfo = new TabInfo(semifinales, ConcursoActivity.class, args)));
		addTab(this, this.mTabHost, this.mTabHost.newTabSpec(fasefinal).setIndicator(fasefinal), ( tabInfo = new TabInfo(fasefinal, ConcursoActivity.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		// Default to first tab
		mTabHost.setOnTabChangedListener(this);
	}

	private void addTab(SherlockFragmentActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		tabSpec.setContent(new TabFactory(activity));
		tabHost.addTab(tabSpec);
	}

	@Override
	public void onTabChanged(String tabId) {
		int pos = this.mTabHost.getCurrentTab();
		this.mViewPager.setCurrentItem(pos);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) { }

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) { }

	@Override
	public void onPageSelected(int position) {
		this.mTabHost.setCurrentTab(position);
	}
	
	

}
