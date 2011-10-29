package es.jcorralejo.android.utils;

public class Constantes {
	
	/**
	 * Constante para identificar el par�metro id de un lugar concreto 
	 */
	public static final String PARAMETRO_ID_LUGAR = "idLugar";
	
	/**
	 * Constante para identificar el par�metro punto seleccionado en el mapa 
	 */
	public static final String PARAMETRO_PUNTO_MAPA_SELECCIONADO = "puntoMapaSeleccionado";
	
	/**
	 * Constante para identificar el popUp para perdir confirmaci�n al usuario de un Lugar
	 */
	public static final int DIALOG_PEDIR_CONFIRMACION_MULTIPLE = 0;
	
	/**
	 * Constante para identificar el popUp para perdir confirmaci�n al usuario de un Lugar
	 */
	public static final int DIALOG_PEDIR_CONFIRMACION_SIMPLE = 1;
	
	/**
	 * Constante para identificar el % de pantalla que debe ocupar las im�genes
	 */
	public static final float COEFICIENTE_REDUCCION_IMAGEN = (float) 0.8;

	/**
	 * Constante para identificar cada foto seleccionada de la Galer�a
	 */
	public static final int RESULT_FOTO = 1;

	/**
	 * Constante para identificar la nueva coordenada cuando la estamos editando en un lugar
	 */
	public static final int RESULT_EDITAR_COORDENADA = 2;

	/**
	 * Constante para identificar la opci�n de men� contextual "DETALLES"
	 */
	public static final int MENU_DETALLES = 0;

	/**
	 * Constante para identificar la opci�n de men� contextual "EDITAR"
	 */
	public static final int MENU_EDITAR = 1;

	/**
	 * Constante para identificar la opci�n de men� contextual "ELIMINAR"
	 */
	public static final int MENU_ELIMINAR = 4;

	/**
	 * Constante para identificar la opci�n de men� contextual "VER UBICACI�N"
	 */
	public static final int MENU_VER_UBICACION = 2;
	
	/**
	 * Constante para identificar la opci�n de men� contextual "VER UBICACI�N"
	 */
	public static final int MENU_NAVEGAR = 3;
	
	/**
	 * Constante para indicar TODOS los lugares almacenados en BD
	 */
	public static final long TODOS_LUGARES = Long.MAX_VALUE;

	/**
	 * Constante para indicar NINGUN lugar almacenad en BD
	 */
	public static final long NINGUN_LUGAR = Long.MIN_VALUE;

	/**
	 * Constante para identificar el popUp "Acerca de..."
	 */
	public static final int DIALOG_ACERCA_DE = 0;

	/**
	 * Constante para identificar el popUp "Opciones sobre Punto en el Mapa"
	 */
	public static final int DIALOG_OPCIONES_MAPA = 0;

	/**
	 * Constante para identificar el �ndice de las coordenadas que identifica a la latitud 
	 */
	public static final int INDICE_COORDENADA_LATITUD = 0;

	/**
	 * Constante para identificar el �ndice de las coordenadas que identifica a la longitud
	 */
	public static final int INDICE_COORDENADA_LONGITUD = 1;
	
	/**
	 * Constante para indicar si estamos editando las coordenadas de un lugar
	 */
	public static final String EDITAR_COORDENADA_LUGAR = "EDITAR_COORDENADA_LUGAR";
	
	
}
