package es.jcorralejo.android.carnavapp.activities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.holoeverywhere.widget.CalendarView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;

public class CalendarioFragment extends Fragment{
	
	CarnavappApplication app;
	private LayoutInflater miInflater;
	
	public CalendarioFragment() {
	}
	
	public static CalendarioFragment newInstance() {
		CalendarioFragment calendarioFragment = new CalendarioFragment();
	    return calendarioFragment;
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
		View view = inflater.inflate(R.layout.calendario, container, false);
		
		CalendarView calendar = (CalendarView) view.findViewById(R.id.calendar);
		
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			 
	        public void onSelectedDayChange(CalendarView view, int year, int monthOfYear, int dayOfMonth) {
	            
	        	GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
	        	Date dia = calendar.getTime();
				if(app.hoyHayConcurso(dia)){
					((CarnavappActivity3)getActivity()).verActuacionesDia(dia);
				}else
					Toast.makeText(getActivity(), getString(R.string.no_evento_dia), Toast.LENGTH_SHORT).show();
	            
	        }});		

		return view;
	}

	



    
}
