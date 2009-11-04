package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.MENSAJE_FORO_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import utilidades.varios.HerramientasList;
import dondeando.modelo.dao.MensajeForoDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.MensajeForoImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioMensajeForo;
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioTipoInteres;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_MENSAJE_FORO)
public class ServicioMensajeForoImpl implements ServicioMensajeForo{

	
	//Utilidades
	@In(value= MENSAJE_FORO_DAO, create=true)
	private MensajeForoDAO mensajeForoDAO;
	
	private Log log = LogFactory.getLog(ServicioMensajeForoImpl.class);
	
	//Servicios
    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
    
    @In(value=SERVICIO_NOTIFICACION, create=true)
    private ServicioNotificacion servicioNotificacion;
    
    /*
     * s(non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioMensajeForo#encontrarTemasDeForo(dondeando.modelo.entidades.Foro)
     */
    public List<MensajeForo> encontrarTemasDeForo(Foro foro) {
    	return mensajeForoDAO.encontrarPorCondicion(servicioCriterios.construyeCriterio(MensajeForo.ATRIBUTO_FORO, Criterio.IGUAL, foro),
    												servicioCriterios.construyeCriterio(MensajeForo.ATRIBUTO_RESPONDE_A, Criterio.IGUAL, null));
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioMensajeForo#rellenarPropiedadesNoMapeadas(java.util.List)
     */
    public void rellenarPropiedadesNoMapeadas(List<MensajeForo> listaMensajesForo) {
		for(MensajeForo mensajeForo : listaMensajesForo){
			MensajeForoImpl mensajeForoImpl = (MensajeForoImpl)mensajeForo;
			
			mensajeForoImpl.setAutorYFecha(mensajeForo.getAutor().getLogin()+" ("+mensajeForo.getFecha()+")");
			if(mensajeForo.getRespuestas()!=null && !mensajeForo.getRespuestas().isEmpty()){
				List<MensajeForo> respuestasOrdenadas = HerramientasList.ordenar(new ArrayList<MensajeForo>(mensajeForo.getRespuestas()), MensajeForo.ATRIBUTO_FECHA + " DESC");
				MensajeForo ultimaRespuesta = respuestasOrdenadas.get(0);
				mensajeForoImpl.setFechaUltimaRespuesta(ultimaRespuesta.getFecha());
				mensajeForoImpl.setAutorYFechaUltimaRespuesta(ultimaRespuesta.getAutor().getLogin()+" ("+ultimaRespuesta.getFecha()+")");
				mensajeForoImpl.setNumeroRespuestas(respuestasOrdenadas.size());
			}
		}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioMensajeForo#eliminarTema(dondeando.modelo.entidades.MensajeForo)
     */
    public void eliminarTema(MensajeForo tema) {
    	if(tema!=null){
    		for(MensajeForo respuesta : tema.getRespuestas())
    			mensajeForoDAO.hacerTransitorio(respuesta);
    		tema.getRespuestas().clear();
    		mensajeForoDAO.hacerTransitorio(tema);
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioMensajeForo#eliminarMensaje(dondeando.modelo.entidades.MensajeForo)
     */
    public void eliminarMensaje(MensajeForo mensaje) {
    	if(mensaje!=null){
    		if(mensaje.getRespondeA()==null)
    			eliminarTema(mensaje);
    		else{
    			mensaje.getRespondeA().getRespuestas().remove(mensaje);
    			mensaje.setRespondeA(null);
    			mensajeForoDAO.hacerTransitorio(mensaje);
    		}
    	}
    }

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioMensajeForo#crearMensajeForo(dondeando.modelo.entidades.Foro, dondeando.modelo.entidades.MensajeForo, java.lang.String, java.lang.String, dondeando.modelo.entidades.Usuario)
     */
	public MensajeForo crearMensajeForo(Foro foro, MensajeForo tema, String asunto, String mensaje, Usuario autor) {
		MensajeForo mensajeForo = new MensajeForoImpl();
		setearDatosMensajeForo(foro, tema, mensajeForo, asunto, mensaje, autor, new Date());
		mensajeForoDAO.hacerPersistente(mensajeForo);
		//Hay que setearle el tema después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(tema!=null){
			if(tema.getRespuestas()==null)
				tema.setRespuestas(new HashSet<MensajeForo>());
			if(!tema.getRespuestas().contains(mensajeForo))
				tema.getRespuestas().add(mensajeForo);
		}
		
    	//Enviamos las notificaciones
    	servicioNotificacion.enviarNotificacionesTipoObjeto(ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO, foro, mensajeForo);
		
		return mensajeForo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioMensajeForo#editarMensajeForo(dondeando.modelo.entidades.MensajeForo, dondeando.modelo.entidades.Foro, dondeando.modelo.entidades.MensajeForo, java.lang.String, java.lang.String, dondeando.modelo.entidades.Usuario)
	 */
	public void editarMensajeForo(MensajeForo mensajeForo, String asunto, String mensaje, Usuario autor) {
		setearDatosMensajeForo(mensajeForo.getForo(), mensajeForo.getRespondeA(), mensajeForo, asunto, mensaje, autor, mensajeForo.getFecha()); 
		try {
			mensajeForoDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del mensajeForo");
		}
	}

	/**
	 * Asigna los datos al mensajeForo indicado
	 * @param foro Foro del mensaje
	 * @param tema	Tema al que responde
	 * @param mensajeForo	Mensaje al que asignar los datos
	 * @param asunto	Asunto del mensaje
	 * @param mensaje	Mensaje en sí
	 * @param autor	Autor del mensaje
	 * @param fecha	fecha de la creación del mensaje
	 */
	private void setearDatosMensajeForo(Foro foro, MensajeForo tema, MensajeForo mensajeForo, String asunto, String mensaje, Usuario autor, Date fecha){
		mensajeForo.setForo(foro);
		mensajeForo.setRespondeA(tema);
		mensajeForo.setTitulo(asunto);
		mensajeForo.setMensaje(mensaje);
		mensajeForo.setAutor(autor);
		mensajeForo.setFecha(fecha);
	}


}
