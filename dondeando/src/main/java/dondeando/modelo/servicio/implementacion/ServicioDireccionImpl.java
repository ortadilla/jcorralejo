package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.DIRECCION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_DIRECCION;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.DireccionDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Direccion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoVia;
import dondeando.modelo.entidades.implementacion.DireccionImpl;
import dondeando.modelo.servicio.ServicioDireccion;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_DIRECCION)
public class ServicioDireccionImpl implements ServicioDireccion{

    @In(value=DIRECCION_DAO, create=true)
    private DireccionDAO direccionDAO;
    
    private Log log = LogFactory.getLog(ServicioDireccionImpl.class);

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioDireccion#crearDireccion(dondeando.modelo.entidades.Provincia, java.lang.String, dondeando.modelo.entidades.TipoVia, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public Direccion crearDireccion(Provincia provincia, String localidad, TipoVia tipoVia, 
									String nombreVia, Integer numero, Integer codigoPostal) {
		Direccion direccion = new DireccionImpl();
		setearDatosDireccion(direccion, provincia, localidad, tipoVia, nombreVia, numero, codigoPostal);
		direccionDAO.hacerPersistente(direccion);
		return direccion;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioDireccion#editarDireccion(dondeando.modelo.entidades.Direccion, dondeando.modelo.entidades.Provincia, java.lang.String, dondeando.modelo.entidades.TipoVia, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public void editarDireccion(Direccion direccion, Provincia provincia,
								String localidad, TipoVia tipoVia, String nombreVia,
								Integer numero, Integer codigoPostal) {
		setearDatosDireccion(direccion, provincia, localidad, tipoVia, nombreVia, numero, codigoPostal);
		try {
			direccionDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al guardar los datos de la dirección");
		}
	}
	
	/**
	 * Setea a la dirección los datos indicados
	 * @param direccion Direción a actualizar
	 * @param provincia	Provincia de la dirección
	 * @param localidad Localidad de la dirección
	 * @param tipoVia Tipo de vía de la dirección
	 * @param nombreVia Nombre de la vía de la dirección
	 * @param numero Número de la dirección
	 * @param codigoPostal Código postal de la dirección
	 * @return Dirección actualizada
	 */
	private Direccion setearDatosDireccion(Direccion direccion, Provincia provincia, String localidad, TipoVia tipoVia, 
										   String nombreVia, Integer numero, Integer codigoPostal){
		
		direccion.setProvincia(provincia);
		direccion.setLocalidad(localidad);
		direccion.setTipoVia(tipoVia);
		direccion.setNombreVia(nombreVia);
		direccion.setNumero(numero);
		direccion.setCodigoPostal(codigoPostal);
		
		return direccion;
	}
}
