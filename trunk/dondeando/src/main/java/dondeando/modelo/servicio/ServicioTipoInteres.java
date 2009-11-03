package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoInteres;

public interface ServicioTipoInteres {
	
	public static final Integer TIPO_NUEVO_USUARIO = 1;
	public static final Integer TIPO_NUEVO_LOCAL = 2;
	public static final Integer TIPO_NUEVO_LOCAL_PROVINCIA = 3;
	public static final Integer TIPO_MODIFICAR_LOCAL = 4;
	public static final Integer TIPO_MODIFICAR_LOCAL_PROVINCIA = 5;
	public static final Integer TIPO_OPINION_LOCAL = 6;
	public static final Integer TIPO_VALORACION_LOCAL = 7;
	public static final Integer TIPO_NUEVO_FORO = 8;
	public static final Integer TIPO_NUEVO_TEMA_MENSAJE_FORO = 9;
	public static final Integer TIPO_NUEVA_ENCUESTA = 10;
	
	
	/**
	 * Devuelve todos los Tipos de Interes de la aplicación
	 * @return	Todos los Tipos de Interes de la aplicación
	 */
	public List<TipoInteres> encontrarTodos();

}
