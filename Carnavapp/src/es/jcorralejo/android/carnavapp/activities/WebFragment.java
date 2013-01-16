package es.jcorralejo.android.carnavapp.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
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
		setRetainInstance(true);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.web, container, false);
		
		if(url!=null && !url.equals("")){
			WebView webView = (WebView) view.findViewById(R.id.my_webview);
			webView.setWebViewClient(new  WebViewClient(){
				
				ProgressDialog pd = null;
				
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}
				
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					pd = ProgressDialog.show(getActivity(), getResources().getString(R.string.cargando_datos), getResources().getString(R.string.esperar_carga), true, false, null);
					super.onPageStarted(view, url, favicon);
				}
				
				@Override
				public void onPageFinished(WebView view, String url) {
					if(pd!=null){
						pd.dismiss();
						pd = null;
					}
					super.onPageFinished(view, url);
				}
			});
			webView.loadUrl(url);
		}
		
		return view;
	}
	
	

}
