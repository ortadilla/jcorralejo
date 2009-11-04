package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.NOTIFICACION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_INTERES;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_INTERES;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.MensajesCore;
import dondeando.modelo.dao.NotificacionDAO;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.NotificacionImpl;
import dondeando.modelo.servicio.ServicioInteres;
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioTipoInteres;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_NOTIFICACION)
public class ServicioNotificacionImpl implements ServicioNotificacion{
	
    @In(value=NOTIFICACION_DAO, create=true) 
    private NotificacionDAO notificacionDAO;
    
    @In(value=SERVICIO_TIPO_INTERES, create=true) 
    private ServicioTipoInteres servicioTipoInteres;
    
    @In(value=SERVICIO_INTERES, create=true) 
    private ServicioInteres servicioInteres;
    
    @In(value=MENSAJES_CORE, create=true) 
    private MensajesCore mensajesCore;
    
    private Log log = LogFactory.getLog(ServicioNotificacionImpl.class);
    
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioNotificacion#enviarNotificacionesTipoObjeto(java.lang.Integer, java.lang.Integer)
     */
    public void enviarNotificacionesTipoObjeto(Integer idTipoInteres, Object objetoObjetivo, Object objetoMensaje){
    	TipoInteres tipoInteres = servicioTipoInteres.encontrarTipoInteresPorId(idTipoInteres);

    	if(tipoInteres!=null 
    	&& (objetoObjetivo==null || objetoObjetivo instanceof Local || objetoObjetivo instanceof Provincia || objetoObjetivo instanceof Foro)){
    	
    		Integer idObjeto = obtenerIdObjetoNotificacion(idTipoInteres, objetoObjetivo);
    		
    		List<Interes> intereses = servicioInteres.encontrarNotificacionesPorTipoYObjeto(tipoInteres, idObjeto);
    		for(Interes interes : intereses){
    			Notificacion notificacion = new NotificacionImpl();
    			notificacion.setLeida(false);
    			notificacion.setTipoInteres(tipoInteres);
    			notificacion.setUsuario(interes.getUsuario());
    			notificacion.setMensaje(obtenerMensajeNotificacion(idTipoInteres, objetoObjetivo, objetoMensaje));
    			
    			notificacionDAO.hacerPersistente(notificacion);
    			
    			if(interes.isEnviarEmail()){
    				//TODO: Emails
    			}
    		}
    	}
    }

    /**
     * Obtiene el mensaje a enviar en la notificación  
     * @param tipoInteres Tipo de Interés
     * @param objeto Objeto de la notificación
     * @return Mensaje para la notificación
     */
    private String obtenerMensajeNotificacion(Integer idTipoInteres, Object objetoObjetivo, Object objetoMensaje){
    	String mensaje = "";
    	
    	if(ServicioTipoInteres.TIPO_NUEVO_USUARIO.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_1", ((Usuario)objetoMensaje).getLogin());
    	else if(ServicioTipoInteres.TIPO_NUEVO_LOCAL.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_2", ((Local)objetoMensaje).getNombre());
    	else if(ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_3", ((Provincia)objetoObjetivo).getNombre(), ((Local)objetoMensaje).getNombre());
    	else if(ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_4", ((Local)objetoObjetivo).getNombre());
    	else if(ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_5", ((Provincia)objetoObjetivo).getNombre(), ((Local)objetoMensaje).getNombre());
    	else if(ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_6", ((Local)objetoObjetivo).getNombre(), ((Opinion)objetoMensaje).getOpinion());
    	else if(ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_7", ((Local)objetoObjetivo).getNombre(), "Lo mejor: '"+ ((Puntuacion)objetoMensaje).getLoMejor()+"', Lo peor: '"+((Puntuacion)objetoMensaje).getLoPeor()+"'");
    	else if(ServicioTipoInteres.TIPO_NUEVO_FORO.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_8", ((Foro)objetoMensaje).getTitulo());
    	else if(ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_9", ((Foro)objetoObjetivo).getTitulo(), ((MensajeForo)objetoMensaje).getMensaje());
    	else if(ServicioTipoInteres.TIPO_NUEVA_ENCUESTA.equals(idTipoInteres))
    		mensaje = mensajesCore.obtenerTexto("MENSAJE_TIPO_10");
    	
    	return mensaje;
    }

    /**
     * Obtiene el id correcto del objeto de la Notificación
     * @param idTipoInteres Id del tipo de notificación
     * @param objeto Objeto del que sacar el id
     * @return id correcto del objeto de la Notificación
     */
    private Integer obtenerIdObjetoNotificacion(Integer idTipoInteres, Object objeto){
		Integer idObjeto = null;
		
		if((ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(idTipoInteres)
		||  ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(idTipoInteres)
		||  ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(idTipoInteres))
		&&  objeto instanceof Local)
			idObjeto = ((Local)objeto).getId();
				
		if(ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(idTipoInteres)
		&& objeto instanceof Foro)
			idObjeto = ((Foro)objeto).getId();

		if((ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(idTipoInteres)
		||  ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(idTipoInteres))
		&&  objeto instanceof Provincia)
			idObjeto = ((Provincia)objeto).getId();
		
		return idObjeto;
    }
    

}
