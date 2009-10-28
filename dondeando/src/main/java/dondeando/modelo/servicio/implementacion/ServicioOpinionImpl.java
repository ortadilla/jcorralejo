package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.OPINION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_OPINION;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.OpinionDAO;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.implementacion.OpinionImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioOpinion;

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
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioOpinion#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
    public void rellenarPropiedadesNoMapeadas(List<Opinion> listaOpiniones) {
		for(Opinion opinion : listaOpiniones){
			OpinionImpl OpinionImpl = (OpinionImpl)opinion;
			OpinionImpl.setAutorYFecha(opinion.getUsuario()+" ("+opinion.getFecha()+")");
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
    		opinionDAO.hacerTransitorio(opinion);
    	}
    	
    }

}
