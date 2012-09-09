package es.jcorralejo.android.llamaamama;


import android.app.Activity;
import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WidgetConfig extends Activity {

	private static final int OBTENER_CONTACTO = 0;
	private EditText txtTfno = null;
	private int widgetId = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_config);

		//Obtenemos el Intent que ha lanzado esta ventana y recuperamos sus parámetros
		Intent intentOrigen = getIntent();
		Bundle params = intentOrigen.getExtras();

		//Obtenemos el ID del widget que se está configurando
		widgetId = params.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

		//Obtenemos la referencia a los controles de la pantalla
		final Button btnAceptar = (Button)findViewById(R.id.BtnAceptar);
		final Button btnCancelar = (Button)findViewById(R.id.BtnCancelar);
		txtTfno = (EditText)findViewById(R.id.TxtMensaje);
		final ImageView contacto = (ImageView) findViewById(R.id.contacto);

		//Establecemos el resultado por defecto (si se pulsa el botón 'Atrás' del teléfono será éste el resultado devuelto).
		setResult(RESULT_CANCELED);

		contacto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, OBTENER_CONTACTO);
			}
		});

		//Implementación del botón "Cancelar"
		btnCancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Devolvemos como resultado: CANCELAR (RESULT_CANCELED)
				finish();
			}
		});

		//Implementación del botón "Aceptar"
		btnAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Guardamos el mensaje personalizado en las preferencias
				SharedPreferences prefs = getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("msg_" + widgetId, txtTfno.getText().toString());
				editor.commit();

				//Actualizamos el widget tras la configuración
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(WidgetConfig.this);
				MiWidget.actualizarWidget(WidgetConfig.this, appWidgetManager, widgetId);

				//Devolvemos como resultado: ACEPTAR (RESULT_OK)
				Intent resultado = new Intent();
				resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
				setResult(RESULT_OK, resultado);
				finish();
			}
		});
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (OBTENER_CONTACTO) :
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c =  managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

					Cursor cursorTfns = getContentResolver().query(
							CommonDataKinds.Phone.CONTENT_URI,
							new String[] { CommonDataKinds.Phone.NUMBER },
							ContactsContract.Data.CONTACT_ID + " = " + id,
							null, null);

					if (cursorTfns.moveToFirst()) {
						final CharSequence[] tfns = new CharSequence[cursorTfns.getCount()];
						for(int i=0; i<cursorTfns.getCount(); i++){
							tfns[i] = cursorTfns.getString(0);
							cursorTfns.moveToNext();
						}
						if(tfns.length>1)
							lanzarPopUpTfns(tfns);
						else
							txtTfno.setText(tfns[0]);
						
					}else{
						Toast.makeText(getApplicationContext(), R.string.error_no_tfn, Toast.LENGTH_LONG).show();
					}
				}
			}
		break;
		}
	}

	private void lanzarPopUpTfns(final CharSequence[] tfns){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.seleccion_tfn);
		builder.setItems(tfns, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				txtTfno.setText(tfns[item]);
			}
		});
		AlertDialog alert = builder.create();
		alert.setOwnerActivity(this);
		alert.show();
	}
}