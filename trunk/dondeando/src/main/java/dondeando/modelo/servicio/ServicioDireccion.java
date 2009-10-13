package dondeando.modelo.servicio;

import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoVia;


public interface ServicioDireccion {
	
	/**
	 * Crea una direcci�n con los datos indicados
	 * @param provincia	Provincia de la direcci�n
	 * @param localidad Localidad de la direcci�n
	 * @param tipoVia Tipo de v�a de la direcci�n
	 * @param nombreVia Nombre de la v�a de la direcci�n
	 * @param numero N�mero de la direcci�n
	 * @param codigoPostal C�digo postal de la direcci�n
	 * @return Direcci�n creada
	 */
	public Direccion crearDireccion(Provincia provincia, String localidad, TipoVia tipoVia, 
									String nombreVia, Integer numero, Integer codigoPostal);
	
	/**
	 * Edita la direcci�n con los datos indicados
	 * @param direccion Direci�n a editar
	 * @param provincia	Provincia de la direcci�n
	 * @param localidad Localidad de la direcci�n
	 * @param tipoVia Tipo de v�a de la direcci�n
	 * @param nombreVia Nombre de la v�a de la direcci�n
	 * @param numero N�mero de la direcci�n
	 * @param codigoPostal C�digo postal de la direcci�n
	 */
	public void editarDireccion(Direccion direccion, Provincia provincia, String localidad, TipoVia tipoVia, 
							    String nombreVia, Integer numero, Integer codigoPostal);

}
