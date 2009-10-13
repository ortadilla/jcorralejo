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
			log.debug("Error al guardar los datos de la direcci�n");
		}
	}
	
	/**
	 * Setea a la direcci�n los datos indicados
	 * @param direccion Direci�n a actualizar
	 * @param provincia	Provincia de la direcci�n
	 * @param localidad Localidad de la direcci�n
	 * @param tipoVia Tipo de v�a de la direcci�n
	 * @param nombreVia Nombre de la v�a de la direcci�n
	 * @param numero N�mero de la direcci�n
	 * @param codigoPostal C�digo postal de la direcci�n
	 * @return Direcci�n actualizada
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
