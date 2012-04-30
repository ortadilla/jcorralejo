package es.jcorralejo.android.autodefinidos.bd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import entidades.Palabra;

public class DBHelper extends SQLiteOpenHelper{

	//Ruta por defecto de las bases de datos en el sistema Android
	private static String DB_PATH = "/data/data/es.jcorralejo.android.autodefinidos/databases/";

	private static String DB_NAME = "palabras.sqlite";
	private static String DATABASE_TABLE = "palabra";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	//Establecemos los nombres de las columnas
	public static final String KEY_ID = "_id";
	public final static String KEY_COL1 = "palabra";
	public final static String KEY_COL2 = "definicion";
	public final static String KEY_COL3 = "dificultad";
	//Array de strings para su uso en los diferentes m�todos
	private static final String[] cols = new String[] { KEY_ID, KEY_COL1, KEY_COL2, KEY_COL3 };

	/**
	 * Constructor
	 * Toma referencia hacia el contexto de la aplicaci�n que lo invoca para poder acceder a los 'assets' y 'resources' de la aplicaci�n.
	 * Crea un objeto DBOpenHelper que nos permitir� controlar la apertura de la base de datos.
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Crea una base de datos vac�a en el sistema y la reescribe con nuestro fichero de base de datos.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist){
			//la base de datos existe y no hacemos nada.
		}else{
			//Llamando a este m�todo se crea la base de datos vac�a en la ruta por defecto del sistema
			//de nuestra aplicaci�n por lo que podremos sobreescribirla con nuestra base de datos.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {
				throw new Error("Error copiando Base de Datos");
			}
		}

	}

	/**
	 * Comprueba si la base de datos existe para evitar copiar siempre el fichero cada vez que se abra la aplicaci�n.
	 * @return true si existe, false si no existe
	 */
	private boolean checkDataBase(){

		SQLiteDatabase checkDB = null;

		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		}catch(SQLiteException e){

			//si llegamos aqui es porque la base de datos no existe todav�a.

		}
		if(checkDB != null){

			checkDB.close();

		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copia nuestra base de datos desde la carpeta assets a la reci�n creada
	 * base de datos en la carpeta de sistema, desde d�nde podremos acceder a ella.
	 * Esto se hace con bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Abrimos el fichero de base de datos como entrada
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		//Ruta a la base de datos vac�a reci�n creada
		String outFileName = DB_PATH + DB_NAME;

		//Abrimos la base de datos vac�a como salida
		OutputStream myOutput = new FileOutputStream(outFileName);

		//Transferimos los bytes desde el fichero de entrada al de salida
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Liberamos los streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void open() throws SQLException{

		//Abre la base de datos
		try {
			createDataBase();
		} catch (IOException e) {
			throw new Error("Ha sido imposible crear la Base de Datos");
		}

		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/*
	 * A continuaci�n se crear�n los m�todos de lectura, inserci�n, actualizaci�n
	 * y borrado de la base de datos.
	 */
	
	public long insertPalabra(Integer id, String palabra, String definicion, String dificultad) {
		ContentValues newValues = new ContentValues();
		//		newValues.put(KEY_ID, id);
		newValues.put(KEY_COL1, palabra);
		newValues.put(KEY_COL2, definicion);
		newValues.put(KEY_COL3, dificultad);
		return myDataBase.insert(DATABASE_TABLE, null, newValues);
	}

	public boolean removePalabra(long _rowIndex) {
		return myDataBase.delete(DATABASE_TABLE, KEY_ID + "=" + _rowIndex, null) > 0;
	}

	public boolean updatePalabra(Integer _rowIndex,  String palabra, String definicion, String dificultad) {
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1,palabra);
		newValues.put(KEY_COL2, definicion);
		newValues.put(KEY_COL3, dificultad);
		return myDataBase.update(DATABASE_TABLE, newValues, KEY_ID + "=" + _rowIndex, null) > 0;
	}	

	public Palabra getPalabra(long _rowIndex) {
		Palabra palabra = null;
		Cursor result = myDataBase.query(true, DATABASE_TABLE, cols, KEY_ID + "=" + _rowIndex, null, null, null,null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) {
			//Si la alarma no existe, devuelve una alarma sin valores
			palabra = new Palabra(-1, "", "", "");
		} else {
			if (result.moveToFirst()) {
				palabra = new Palabra(
						result.getInt(result.getColumnIndex(KEY_ID)),
						result.getString(result.getColumnIndex(KEY_COL1)),
						result.getString(result.getColumnIndex(KEY_COL2)),
						result.getString(result.getColumnIndex(KEY_COL3))
				);
			}
		}
		return palabra;
	}

	public List<Palabra> getPalabras() {
		List<Palabra> palabras = new ArrayList<Palabra>();
		Cursor result = myDataBase.query(DATABASE_TABLE, cols, null, null, null, null, KEY_ID);
		if (result.moveToFirst())
			do {
				Palabra pal = new Palabra(
						result.getInt(result.getColumnIndex(KEY_ID)),
						result.getString(result.getColumnIndex(KEY_COL1)),
						result.getString(result.getColumnIndex(KEY_COL2)),
						result.getString(result.getColumnIndex(KEY_COL3))
				);
				palabras.add(pal);
			} while(result.moveToNext());
		return palabras;
	}

}