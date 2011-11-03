package es.jcorralejo.android.maps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import es.jcorralejo.android.R;

public class ItemizedOverlayLugar extends ItemizedOverlay<OverlayItemLugar> {

	private ArrayList<OverlayItemLugar> mOverlays = new ArrayList<OverlayItemLugar>();
	private Context context;
	private final MapController mc;
	
	private OverlayItemLugar lugarPulsado = null;
	
	private FrameLayout popUp;
	private LinearLayout contenidoPopUp;
	private LinearLayout clickContenidoPopUp;
	private TextView nombreLugar;
	private TextView descripcionLugar;
	private ImageView cerrarPopUp;

	public ItemizedOverlayLugar(Context context, Drawable defaultMarker, MapView mapa) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
		this.mc = mapa.getController();
		
		
		LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contenidoPopUp = (LinearLayout) inflater.inflate(R.layout.balloon_overlay, contenidoPopUp);
		contenidoPopUp.setVisibility(LinearLayout.VISIBLE);
		
		popUp = new FrameLayout(context);
		popUp.setPadding(10, 0, 10, 0);
		
		clickContenidoPopUp = (LinearLayout) contenidoPopUp.findViewById(R.id.balloon_inner_layout);
		clickContenidoPopUp.setOnTouchListener(createBalloonTouchListener());

		
		nombreLugar = (TextView) contenidoPopUp.findViewById(R.id.balloon_item_title);
		descripcionLugar = (TextView) contenidoPopUp.findViewById(R.id.balloon_item_snippet);
		ImageView close = (ImageView) contenidoPopUp.findViewById(R.id.close_img_button);
		close.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				contenidoPopUp.setVisibility(LinearLayout.GONE);
			}
		});
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;
		popUp.addView(contenidoPopUp, params);
		mapa.addView(popUp);
	}
	
	private OnTouchListener createBalloonTouchListener() {
		return new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				View l =  ((View) v.getParent()).findViewById(R.id.balloon_main_layout);
				Drawable d = l.getBackground();
				
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int[] states = {android.R.attr.state_pressed};
					if (d.setState(states)) {
						d.invalidateSelf();
					}
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					int newStates[] = {};
					if (d.setState(newStates)) {
						d.invalidateSelf();
					}
//					onBalloonTap(lugarPulsado.getIdLugar(), lugarPulsado);
					return true;
				} else {
					return false;
				}
			}
		};
	}
	
	public void add(double lat, double lon, String etiqueta, long idLugar) {
		int lt = (int) (lat * 1E6);
		int ln = (int) (lon * 1E6);
		GeoPoint punto = new GeoPoint(lt, ln);
		OverlayItemLugar item = new OverlayItemLugar(punto, etiqueta, null, idLugar);
		mOverlays.add(item);
		populate();
	}
	
	public void clear() {
		mOverlays.clear();
		populate();
	}
	
	@Override
	protected OverlayItemLugar createItem(int i) {
		return mOverlays.get(i);
	}
	
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		boolean result = super.onTap(index);
		lugarPulsado = mOverlays.get(index);
		
		crearCuadroResumen(index);
		mc.animateTo(lugarPulsado.getPoint());
		
		return result;
	}
	
	private void crearCuadroResumen(int index){
		
		popUp.setVisibility(View.GONE);
		
//		List<Overlay> mapOverlays = mapView.getOverlays();
//		if (mapOverlays.size() > 1) {
//			hideOtherBalloons(mapOverlays);
//		}
		
		GeoPoint point = lugarPulsado.getPoint();
		MapView.LayoutParams params = new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point, MapView.LayoutParams.BOTTOM_CENTER);
		params.mode = MapView.LayoutParams.MODE_MAP;
		popUp.setLayoutParams(params);
		popUp.setVisibility(View.VISIBLE);
		
		
		rellenarPopUp();
	}
	
	private void rellenarPopUp(){
		if (lugarPulsado != null){
			contenidoPopUp.setVisibility(LinearLayout.VISIBLE);
			if (lugarPulsado.getTitle() != null) {
				nombreLugar.setVisibility(LinearLayout.VISIBLE);
				nombreLugar.setText(lugarPulsado.getTitle());
			} else {
				nombreLugar.setVisibility(TextView.GONE);
			}
			if (lugarPulsado.getSnippet() != null) {
				descripcionLugar.setVisibility(TextView.VISIBLE);
				descripcionLugar.setText(lugarPulsado.getSnippet());
			} else {
				descripcionLugar.setVisibility(TextView.GONE);
			}
		}
	}

	public OverlayItemLugar getLugarPulsado() {
		return lugarPulsado;
	}

	public void setLugarPulsado(OverlayItemLugar lugarPulsado) {
		this.lugarPulsado = lugarPulsado;
	}

}

