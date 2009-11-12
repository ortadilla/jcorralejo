package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.OPINION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
import static utilidades.varios.NombresBean.SERVICIO_OPINION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.OpinionDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.OpinionImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioOpinion;
import dondeando.modelo.servicio.ServicioTipoInteres;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_OPINION)
public class ServicioOpinionImpl implements ServicioOpinion{
	
	//Utilidades
	@In(value= OPINION_DAO, create=true)
	private OpinionDAO opinionDAO;
	
	private Log log = LogFactory.getLog(ServicioOpinionImpl.class);
	
	//Servicios
    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
    
    @In(value=SERVICIO_NOTIFICACION, create=true)
    private ServicioNotificacion servicioNotificacion;
    
    @In(value=SERVICIO_USUARIO, create=true)
    private ServicioUsuario servicioUsuario;
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioOpinion#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
    public void rellenarPropiedadesNoMapeadas(List<Opinion> listaOpiniones) {
		for(Opinion opinion : listaOpiniones){
			OpinionImpl OpinionImpl = (OpinionImpl)opinion;
			OpinionImpl.setAutorYFecha(opinion.getUsuario().getLogin()+" ("+opinion.getFecha()+")");
		}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioOpinion#eliminarOpinion(dondeando.modelo.entidades.Opinion)
     */
    public void eliminarOpinion(Opinion opinion) {
    	if(opinion!=null){
    		opinion.getLocal().getOpiniones().remove(opinion);
    		opinion.setLocal(null);
    		opinion.getUsuario().getOpiniones().remove(opinion);
    		opinion.setUsuario(null);
//    		opinionDAO.hacerTransitorio(opinion);
    	}
    }
    

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioOpinion#crearOpinion(dondeando.modelo.entidades.Local, java.lang.String, dondeando.modelo.entidades.Usuario)
     */
    public Opinion crearOpinion(Local local, String mensaje, Usuario usuario) {
		Opinion opinion = new OpinionImpl();
		setearDatosOpinion(local, opinion, mensaje, usuario, new Date());
		opinion.setValoracionUsuarios(0);
		opinionDAO.hacerPersistente(opinion);
		//Hay que setearle el tema después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(local!=null){
			if(local.getOpiniones()==null)
				local.setOpiniones(new HashSet<Opinion>());
			if(!local.getOpiniones().contains(opinion))
				local.getOpiniones().add(opinion);
		}
		//Hay que setearle el tema después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(usuario!=null){
			if(usuario.getOpiniones()==null)
				usuario.setOpiniones(new HashSet<Opinion>());
			if(!usuario.getOpiniones().contains(opinion))
				usuario.getOpiniones().add(opinion);
		}
		
		//Enviamos las notificaciones
		servicioNotificacion.enviarNotificacionesTipoObjeto(ServicioTipoInteres.TIPO_OPINION_LOCAL, local, opinion);
		
		//Actualizamos el karma del usuario
    	servicioUsuario.actualizarKarma(ServicioUsuario.OPERACION_OPINIONAR_LOCAL, null, null);
		
		return opinion;
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioOpinion#editarOpinion(dondeando.modelo.entidades.Opinion, java.lang.String)
     */
    public void editarOpinion(Opinion opinionEdicion, String mensaje){
    	setearDatosOpinion(opinionEdicion.getLocal(), opinionEdicion,mensaje, opinionEdicion.getUsuario(), new Date()); 
		try {
			opinionDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del mensajeForo");
		}
    }
    
    /**
     * Asigna a la opinión los valores indicados
     * @param local Local para la opinión
     * @param opinion Opinión a asignar los valores
     * @param mensaje mensaje para la opinión
     * @param usuario usuario para la opinión 
     * @param fecha Fecha para la opinión
     */
    private void setearDatosOpinion(Local local, Opinion opinion, String mensaje, Usuario usuario, Date fecha){
    	opinion.setLocal(local);
    	opinion.setOpinion(mensaje);
    	opinion.setUsuario(usuario);
    	opinion.setFecha(fecha);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioOpinion#encontrarTodas()
     */
    public List<Opinion> encontrarTodas() {
    	return opinionDAO.encontrarTodos();
    }

}
