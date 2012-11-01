package es.jcorralejo.android.llamaamama;


import java.util.Date;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.widget.RemoteViews;

public class MiWidget extends AppWidgetProvider {
	
	final static long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		//Iteramos la lista de widgets en ejecución
		for (int i = 0; i < appWidgetIds.length; i++) 
		{
			//ID del widget actual
			int widgetId = appWidgetIds[i];

			//Actualizamos el widget actual
			actualizarWidget(context, appWidgetManager, widgetId);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		//Accedemos a las preferencias de la aplicación
		SharedPreferences prefs = context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		//Eliminamos las preferencias de los widgets borrados
		for(int i=0; i<appWidgetIds.length; i++)
		{
			//ID del widget actual
			int widgetId = appWidgetIds[i];

			editor.remove("msg_" + widgetId);
		}

		//Aceptamos los cambios
		editor.commit();

		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("es.jcorralejo.android.llamaamama.ACTUALIZAR_WIDGET")) {

			//Obtenemos el ID del widget a actualizar
			int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

			//Obtenemos el widget manager de nuestro contexto
			AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);

			//Actualizamos el widget
			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				actualizarWidget(context, widgetManager, widgetId);
			}
		}

		super.onReceive(context, intent);
	}

	public static void actualizarWidget(Context context, AppWidgetManager appWidgetManager, int widgetId)
	{
		//Recuperamos el mensaje personalizado para el widget actual
		SharedPreferences prefs = context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
		String tfn = prefs.getString("msg_" + widgetId, "----------");
		
		int imagen = obtenerImagen(obtenerUltimaLlamada(context, tfn));

		//Obtenemos la lista de controles del widget actual
		RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.miwidget);

		//Asociamos los 'eventos' al widget
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+tfn));
//		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		//Configuramos la llamada al tocar la imagen
		controles.setOnClickPendingIntent(R.id.imagen, pendingIntent);

		//Actualizamos la imagen
		controles.setImageViewResource(R.id.imagen, imagen);

		//Notificamos al manager de la actualización del widget actual
		appWidgetManager.updateAppWidget(widgetId, controles);
	}
	
	private static int obtenerImagen(int dias){
		int imagen;
		switch (dias) {
			case 0: 
				imagen = R.drawable.bn1;
				break;
			case 1:
				imagen = R.drawable.bn2;
				break;
			case 2:
				imagen = R.drawable.bn3;
				break;
			default:
				imagen = R.drawable.bn4;
				break;
		}
		return imagen;
	}

	private static int obtenerUltimaLlamada(Context context, String numero){
		Cursor cur = context.getContentResolver().query(
				Calls.CONTENT_URI,
				new String[] {Calls.DATE}, //Columnas a devolver
				Calls.NUMBER+" = "+numero+" and "+Calls.TYPE+" = "+Calls.OUTGOING_TYPE,//Llamadas salientes al tfn
				null,
				Calls.DATE +" DESC");//Ordenadas por la fecha

		int diferencia = Integer.MAX_VALUE;
		if (cur!=null && cur.moveToFirst())
		{
			int colDate = cur.getColumnIndex(Calls.DATE);
			Date fecha = new Date(cur.getLong(colDate));
			Date hoy = new Date();
			diferencia = (int) ((hoy.getTime() - fecha.getTime()) / MILLSECS_PER_DAY);
		}
		return diferencia;
	}
}
