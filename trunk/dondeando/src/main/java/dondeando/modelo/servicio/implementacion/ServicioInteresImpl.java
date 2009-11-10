package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.INTERES_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_FORO;
import static utilidades.varios.NombresBean.SERVICIO_INTERES;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import dondeando.modelo.dao.InteresDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.InteresImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioForo;
import dondeando.modelo.servicio.ServicioInteres;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioTipoInteres;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_INTERES)
public class ServicioInteresImpl implements ServicioInteres{
	
	//Utilidades
	@In(value= INTERES_DAO, create=true)
	private InteresDAO interesDAO;
	
	private Log log = LogFactory.getLog(ServicioInteresImpl.class);
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;

	@In(value=SERVICIO_FORO, create=true)
	private ServicioForo servicioForo;
	
	@In(value=SERVICIO_CRITERIOS, create=true)
	private ServicioCriterios servicioCriterios;
	
    @In(value=SERVICIO_USUARIO, create=true)
    private ServicioUsuario servicioUsuario;
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioInteres#eliminarOpinion(dondeando.modelo.entidades.Interes)
	 */
	public void eliminarOpinion(Interes interes) {
    	if(interes!=null){
    		interes.getUsuario().getIntereses().remove(interes);
    		interes.setUsuario(null);
//    		interesDAO.hacerTransitorio(interes);
    		try {
				interesDAO.flush();
			} catch (DAOExcepcion e) {
				log.debug("Error al eliminar un interes", e);
				throw new RuntimeException(e);
			}
			
			//Actualizamos el karma del usuario
			servicioUsuario.actualizarKarma(ServicioUsuario.OPERACION_MENOS_NOTIFICACIONES, null);
    	}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioInteres#crearInteres(dondeando.modelo.entidades.Usuario, dondeando.modelo.entidades.TipoInteres, dondeando.modelo.entidades.Foro, dondeando.modelo.entidades.Local, dondeando.modelo.entidades.Provincia, boolean)
	 */
	public void crearInteres(Usuario usuario, TipoInteres tipoInteres, Foro foro, Local local, Provincia provincia, boolean avisarEmail){
		if(usuario!=null && tipoInteres!=null){
			Interes interes = new InteresImpl();
			
			interes.setUsuario(usuario);
			interes.setTipoInteres(tipoInteres);
			interes.setEnviarEmail(avisarEmail);
			if(ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(tipoInteres.getId())
			|| ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(tipoInteres.getId())
			|| ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(tipoInteres.getId()))
				interes.setObjetoInteres(local.getId());
			
			if(ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(tipoInteres.getId()))
				interes.setObjetoInteres(foro.getId());

			if((ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(tipoInteres.getId())
			||  ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(tipoInteres.getId())))
				interes.setObjetoInteres(provincia.getId());
			
			interesDAO.hacerPersistente(interes);
			
	    	//Actualizamos el karma del usuario
	    	servicioUsuario.actualizarKarma(ServicioUsuario.OPERACION_MÁS_NOTIFICACIONES, null);
			
			if(usuario.getIntereses()==null)
				usuario.setIntereses(new HashSet<Interes>());
			usuario.getIntereses().add(interes);
					
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioInteres#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
	public void rellenarPropiedadesNoMapeadas(List<Interes> intereses) {
		for(Interes interes : intereses){
			if(interes.getTipoInteres()!=null && interes.getTipoInteres().isNecesitaObjeto()){
				if(ServicioTipoInteres.TIPO_MODIFICAR_LOCAL.equals(interes.getTipoInteres().getId())
				|| ServicioTipoInteres.TIPO_OPINION_LOCAL.equals(interes.getTipoInteres().getId())
				|| ServicioTipoInteres.TIPO_VALORACION_LOCAL.equals(interes.getTipoInteres().getId())){
					Local local = servicioLocal.encontrarLocalPorId(interes.getObjetoInteres());
					if(local!=null)
						((InteresImpl)interes).setDescripcionObjeto("Local: "+local.getNombre());
				}
				
				else if (ServicioTipoInteres.TIPO_NUEVO_TEMA_MENSAJE_FORO.equals(interes.getTipoInteres().getId())){
					Foro foro = servicioForo.encontrarForoPorId(interes.getObjetoInteres());
					if(foro!=null)
						((InteresImpl)interes).setDescripcionObjeto("Foro: "+foro.getTitulo());
				}
						
				else if (ServicioTipoInteres.TIPO_MODIFICAR_LOCAL_PROVINCIA.equals(interes.getTipoInteres().getId())
					 ||  ServicioTipoInteres.TIPO_NUEVO_LOCAL_PROVINCIA.equals(interes.getTipoInteres().getId())){
					Provincia provincia = servicioProvincia.encontrarProvinciaPorId(interes.getObjetoInteres());
					if(provincia!=null)
						((InteresImpl)interes).setDescripcionObjeto("Provincia: "+provincia.getNombre());
				}
				
			}else 
				((InteresImpl)interes).setDescripcionObjeto("== NINGUNO ==");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioInteres#encontrarInteresPorUsuarioTipoYObjeto(dondeando.modelo.entidades.Usuario, dondeando.modelo.entidades.TipoInteres, java.lang.Integer)
	 */
	public Interes encontrarInteresPorUsuarioTipoYObjeto(Usuario usuario, TipoInteres tipoInteres, Integer idObjeto) {
		
		Interes interes = null;
		if(usuario!=null && tipoInteres!=null){
			Criterio criterioUsuario = servicioCriterios.construyeCriterio(Interes.ATRIBUTO_USUARIO, Criterio.IGUAL, usuario);
			Criterio criterioTipo = servicioCriterios.construyeCriterio(Interes.ATRIBUTO_TIPO_INTERES, Criterio.IGUAL, tipoInteres);
			Criterio criterioObjeto = servicioCriterios.construyeCriterio(Interes.ATRIBUTO_OBJETO_INTERES, Criterio.IGUAL, idObjeto);
			List<Interes> intereses = interesDAO.encontrarPorCondicion(criterioUsuario, criterioTipo, criterioObjeto);
			if(!intereses.isEmpty())
				interes = intereses.get(0);
		}
		return interes;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioInteres#encontrarNotificacionesPorTipoYObjeto(dondeando.modelo.entidades.TipoInteres, dondeando.modelo.entidades.Interes)
	 */
	public List<Interes> encontrarNotificacionesPorTipoYObjeto(TipoInteres tipoInteres, Integer idObjeto){
		
		List<Interes> intereses = new ArrayList<Interes>();
		
		if(tipoInteres!=null){
			Criterio criterioTipo = servicioCriterios.construyeCriterio(Interes.ATRIBUTO_TIPO_INTERES, Criterio.IGUAL, tipoInteres);
			Criterio criterioObjeto = servicioCriterios.construyeCriterio(Interes.ATRIBUTO_OBJETO_INTERES, Criterio.IGUAL, idObjeto);
			intereses = interesDAO.encontrarPorCondicion(criterioTipo, criterioObjeto);
		}
		
		return intereses;
	}

}
