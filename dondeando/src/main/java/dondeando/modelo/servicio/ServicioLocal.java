package dondeando.modelo.servicio;

import java.math.BigDecimal;
import java.util.List;

import utilidades.varios.EntidadConCodigo;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Servicio;
import dondeando.modelo.entidades.TipoVia;

public interface ServicioLocal {
	
	public static final String RANGO_PRECIO_MENOR_10 = "RANGO_PRECIO_MENOR_10";
	public static final String RANGO_PRECIO_10_30 = "RANGO_PRECIO_10_30";
	public static final String RANGO_PRECIO_30_50 = "RANGO_PRECIO_30_50";
	public static final String RANGO_PRECIO_MAYOR_50 = "RANGO_PRECIO_MAYOR_50";
	
	/**
	 * Busca los locales que coincidan con los parámetros indicados
	 * @param nombre	Nombre del Local. Puede ser null
	 * @param tipoLocal	Ids de los tipos de los locales. Puede ser null
	 * @param provincia	Provincia donde se encuentra el local. Puede ser null
	 * @param precio	Precio medio del local. Puede ser null
	 * @param activo	Indica si los locales estarán activos o no. Puede ser null
	 * @return	Lista de locales encontrados
	 */
	public List<Local> encontrarLocalesPorNombreTipoProvinciaYPrecio(String nombre, List<Integer> tiposLocal, Provincia provincia, String precio, Boolean criterioActivo);

	/**
	 * Rellena las propiedades no mapeadas de los locales pasados por parámetros
	 * @param locales Locales a los que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Local> locales);
	
	/**
	 * Desactiva el local indicado
	 * @param local	Local a desactivar
	 */
	public void desactivarLocal(Local local);
	
	/**
	 * Activa el local indicado
	 * @param local	Local a activa
	 */
	public void activarLocal(Local local);
	
	/**
	 * Descarta el local indicado de la sessión de Hibernate
	 * @param local local a descartar
	 */
	public void descartarLocal(Local local);
	
	/**
	 * Devuelve una lista con los rangos de precios en los que están clasificados
	 * los Locales
	 * @return Lista con los rangos de precios en los que están clasificados los Locales
	 */
	public List<EntidadConCodigo> obtenerRangosPrecio();
	
	/**
	 * Obtiene el rango de precios donde se cuenta el precio pasado por parámetros
	 * @param precio Precio para el que calcular el rango de precios
	 * @return Rango de precios. Los posibles valores son {@link ServicioLocal#RANGO_PRECIO_MENOR_10},
	 * {@link ServicioLocal#RANGO_PRECIO_10_30}, {@link ServicioLocal#RANGO_PRECIO_30_50},
	 * {@link ServicioLocal#RANGO_PRECIO_MAYOR_50}
	 */
	public String obtenerRangoPrecioDePrecio(BigDecimal precio);
	
	/**
	 * Busca el local con el nombre indicado para la localidad y la provincia pasados por parámetros
	 * @param nombre	Nombre del local a buscar
	 * @param localidad	Localidad donde se ubica el local a buscar
	 * @param provincia Provincia donde se ubica el local a buscar
	 * @return Local con el nombre indicado para la localidad y la provincia pasados por parámetros
	 */
	public Local encontrarLocalPorNombreLocalidadProvincia(String nombre,String localidad, Provincia provincia);

	/**
	 * Crea un local con los datos indicados
	 * @param nombre Nombre del local
	 * @param provincia Provincia donde está ubicado el local
	 * @param localidad	Localidad donde está ubicado el local
	 * @param tipoVia	Tipo de vía donde está ubicado el local
	 * @param tiposLocal Tipos en los que sw puede clasificar el local
	 * @param nombreVia Nombre de la vía donde está ubicado el local
	 * @param numero	Número donde está ubicado el local
	 * @param codigoPostal Código postal donde está ubicado el local
	 * @param descripcion Descripción del local
	 * @param telefono Teléfono del local
	 * @param email	Email del local
	 * @param horario	Horario del local
	 * @param precioMedio	Precio medio del local	
	 * @param otraInformacion Otra información sobre el local
	 * @param servicios Servicios que dispone el local
	 * @return Local creado
	 */
	public Local crearLocal(String nombre, Provincia provincia, String localidad, 
							TipoVia tipoVia, List<Integer> tiposLocal, String nombreVia, 
							Integer numero, Integer codigoPostal, String descripcion, String telefono,
							String email, String horario, BigDecimal precioMedio, String otraInformacion,
							List<Servicio> servicios);
	
	/**
	 * Edita el local con los datos indicados 
	 * @param local Local a editar
	 * @param nombre Nombre del local
	 * @param provincia Provincia donde está ubicado el local
	 * @param localidad	Localidad donde está ubicado el local
	 * @param tipoVia	Tipo de vía donde está ubicado el local
	 * @param tiposLocal Tipos en los que sw puede clasificar el local
	 * @param nombreVia Nombre de la vía donde está ubicado el local
	 * @param numero	Número donde está ubicado el local
	 * @param codigoPostal Código postal donde está ubicado el local
	 * @param descripcion Descripción del local
	 * @param telefono Teléfono del local
	 * @param email	Email del local
	 * @param horario	Horario del local
	 * @param precioMedio	Precio medio del local	
	 * @param otraInformacion Otra información sobre el local
	 * @param servicios Servicios que dispone el local
	 */
	public void editarLocal(Local local, String nombre, Provincia provincia, String localidad, 
						    TipoVia tipoVia, List<Integer> tiposLocal, String nombreVia, 
						    Integer numero, Integer codigoPostal, String descripcion, String telefono,
						    String email, String horario, BigDecimal precioMedio, String otraInformacion,
						    List<Servicio> servicios);

}
