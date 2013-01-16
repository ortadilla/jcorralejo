package es.jcorralejo.android.carnavapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;

public class WebFragment extends Fragment{
	
	CarnavappApplication app;
	private String url;
	private LayoutInflater miInflater;
	
	public WebFragment() {}

	public WebFragment(String url) {
		this.url = url;
	}
	
	public static WebFragment newInstance(String url){
		return new WebFragment(url);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (CarnavappApplication) getActivity().getApplication();
		miInflater = LayoutInflater.from(getActivity());
		setRetainInstance(true);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.web, container, false);
		
		if(url!=null && !url.equals("")){
			WebView webView = (WebView) view.findViewById(R.id.my_webview);
			webView.setWebViewClient(new  WebViewClient(){
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
			});
			webView.loadUrl(url);
		}
		
		return view;
	}
	
	

}
