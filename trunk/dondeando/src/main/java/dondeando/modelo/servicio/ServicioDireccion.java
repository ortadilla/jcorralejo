package dondeando.modelo.servicio;

import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoVia;


public interface ServicioDireccion {
	
	/**
	 * Crea una dirección con los datos indicados
	 * @param provincia	Provincia de la dirección
	 * @param localidad Localidad de la dirección
	 * @param tipoVia Tipo de vía de la dirección
	 * @param nombreVia Nombre de la vía de la dirección
	 * @param numero Número de la dirección
	 * @param codigoPostal Código postal de la dirección
	 * @return Dirección creada
	 */
	public Direccion crearDireccion(Provincia provincia, String localidad, TipoVia tipoVia, 
									String nombreVia, Integer numero, Integer codigoPostal);
	
	/**
	 * Edita la dirección con los datos indicados
	 * @param direccion Direción a editar
	 * @param provincia	Provincia de la dirección
	 * @param localidad Localidad de la dirección
	 * @param tipoVia Tipo de vía de la dirección
	 * @param nombreVia Nombre de la vía de la dirección
	 * @param numero Número de la dirección
	 * @param codigoPostal Código postal de la dirección
	 */
	public void editarDireccion(Direccion direccion, Provincia provincia, String localidad, TipoVia tipoVia, 
							    String nombreVia, Integer numero, Integer codigoPostal);

}
