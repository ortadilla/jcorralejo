package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.MENSAJE_FORO_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_MENSAJE_FORO;

import java.util.ArrayList;
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
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.implementacion.MensajeForoImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioMensajeForo;

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
			//Si es un tema...
			if(mensajeForo.getRespondeA()==null){
				MensajeForoImpl mensajeForoImpl = (MensajeForoImpl)mensajeForo;
				
				mensajeForoImpl.setAutorYFecha(mensajeForo.getAutor().getLogin()+" ("+mensajeForo.getFecha()+")");
				List<MensajeForo> respuestasOrdenadas = HerramientasList.ordenar(new ArrayList<MensajeForo>(mensajeForo.getRespuestas()), MensajeForo.ATRIBUTO_FECHA + " DESC");
				if(!respuestasOrdenadas.isEmpty()){
					MensajeForo ultimaRespuesta = respuestasOrdenadas.get(0);
					mensajeForoImpl.setFechaUltimaRespuesta(ultimaRespuesta.getFecha());
					mensajeForoImpl.setAutorYFechaUltimaRespuesta(ultimaRespuesta.getAutor().getLogin()+" ("+ultimaRespuesta.getFecha()+")");
					mensajeForoImpl.setNumeroRespuestas(respuestasOrdenadas.size());
				}
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
    
}
