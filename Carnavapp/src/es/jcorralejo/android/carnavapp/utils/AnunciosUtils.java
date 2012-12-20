package es.jcorralejo.android.carnavapp.utils;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;

public class AnunciosUtils {
	
	public static void cargarAnuncios(Activity activity, Integer...ids){
		
		if(ids!=null && ids.length>0){
			Set<String> key = new HashSet<String>();
			key.add("Carnaval"); 
			key.add("Cádiz"); 
			key.add("Comparsa"); 
			key.add("Chirigota"); 
			key.add("Coro"); 
			key.add("Cuarteto"); 
			key.add("Febrero");
			
//			for(int i=0; i<ids.length; i++){
//				AdRequest ar = new AdRequest();
//				ar.setKeywords(key);
//				ids = (AdView) activity.findViewById(ids[i]);
//				ids.loadAd(ar);
//				
//			}
			
		}
	}

}
