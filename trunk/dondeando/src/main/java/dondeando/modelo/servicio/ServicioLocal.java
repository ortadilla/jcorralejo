package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;

public interface ServicioLocal {
	
	public static final String RANGO_PRECIO_MENOR_10 = "RANGO_PRECIO_MENOR_10";
	public static final String RANGO_PRECIO_10_30 = "RANGO_PRECIO_10_30";
	public static final String RANGO_PRECIO_30_50 = "RANGO_PRECIO_30_50";
	public static final String RANGO_PRECIO_MAYOR_50 = "RANGO_PRECIO_MAYOR_50";
	
	/**
	 * Busca los locales que coincidan con los parámetros indicados
	 * @param nombre	Nombre del Local
	 * @param tipoLocal	Ids de los tipos de los locales
	 * @param provincia	Provincia donde se encuentra el local
	 * @param precio	Precio medio del local
	 * @return	Lista de locales encontrados
	 */
	public List<Local> encontrarLocalesPorNombreTipoProvinciaYPrecio(String nombre, List<Integer> tiposLocal, Provincia provincia, String precio);

	/**
	 * Rellena las propiedades no mapeadas de los locales pasados por parámetros
	 * @param locales Locales a los que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Local> locales);
}
