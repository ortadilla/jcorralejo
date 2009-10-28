package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.FORO_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_FORO;

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
import dondeando.modelo.dao.ForoDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.ForoImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioForo;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_FORO)
public class ServicioForoImpl implements ServicioForo{

	//Utilidades
	@In(value= FORO_DAO, create=true)
	private ForoDAO foroDAO;
	
	private Log log = LogFactory.getLog(ServicioForoImpl.class);
	
	//Servicios
    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;

    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioForo#encontrarForosPorTituloYActivo(java.lang.String, java.lang.Boolean)
     */
	public List<Foro> encontrarForosPorTituloYActivo(String titulo, Boolean activo) {
		
		List<Criterio> criterios = new ArrayList<Criterio>();
		if(titulo!=null && !"".equals(titulo))
			criterios.add(servicioCriterios.construyeCriterio(Foro.ATRIBUTO_TITULO, Criterio.IGUAL, titulo));
		if(activo!=null)
			criterios.add(servicioCriterios.construyeCriterio(Foro.ATRIBUTO_ACTIVO, Criterio.IGUAL, activo));
		
		return foroDAO.encontrarPorCondicion(criterios);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
	public void rellenarPropiedadesNoMapeadas(List<Foro> listaForos) {
		for(Foro foro : listaForos){
			if(foro.getMensajes()!=null && !foro.getMensajes().isEmpty()){
				List<MensajeForo> mensajesOrdenados = HerramientasList.ordenar(new ArrayList<MensajeForo>(foro.getMensajes()), MensajeForo.ATRIBUTO_FECHA + " DESC");
				ForoImpl foroImpl = (ForoImpl)foro;
				
				MensajeForo ultimoMensaje = mensajesOrdenados.get(0);
				foroImpl.setDescripcionUltimoPost(ultimoMensaje.getTitulo() + " ("+ultimoMensaje.getFecha()+") de " + ultimoMensaje.getAutor().getLogin());
				foroImpl.setNumeroMensajes(mensajesOrdenados.size());
				foroImpl.setNumeroTemas(HerramientasList.obtenerElementos(mensajesOrdenados, MensajeForo.ATRIBUTO_RESPONDE_A, null).size());
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#desactivarForo(dondeando.modelo.entidades.Foro)
	 */
	public void desactivarForo(Foro foro) {
    	foro.setActivo(false);
    	try {
			foroDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del local "+e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#descartarForo(dondeando.modelo.entidades.Foro)
	 */
	public void descartarForo(Foro foro) {
		foroDAO.descartar(foro);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#activarForo(dondeando.modelo.entidades.Foro)
	 */
	public void activarForo(Foro foro) {
		foro.setActivo(true);
    	try {
    		foroDAO.flush();
    	} catch (DAOExcepcion e) {
    		log.debug("Error al actualizar los datos del local "+e);
    	}
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#crearForo(java.lang.String, java.lang.String)
	 */
	public Foro crearForo(String titulo, String descripcion) {
		Foro foro = new ForoImpl();
		setearDatosForo(foro, titulo, descripcion, true);
		foroDAO.hacerPersistente(foro);
		return foro;
	}

	/**
	 * Asigna los datos al foro indicado
	 * @param foro Foro al que asignar los datos
	 * @param titulo	Título para el foro
	 * @param descripcion	Descripción para el foro
	 * @param activo	Campo activo para el foro
	 */
	private void setearDatosForo(Foro foro, String titulo, String descripcion, boolean activo){
		foro.setTitulo(titulo);
		foro.setDescripcion(descripcion);
		foro.setActivo(activo);
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#editarForo(dondeando.modelo.entidades.Foro, java.lang.String, java.lang.String)
	 */
	public void editarForo(Foro foro, String titulo, String descripcion) {
		setearDatosForo(foro, titulo, descripcion, foro.isActivo());
		try {
			foroDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del foro");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#encontrarForoPorTitulo(java.lang.String)
	 */
	public Foro encontrarForoPorTitulo(String titulo) {
		List<Foro> foros = foroDAO.encontrarPorCondicion(servicioCriterios.construyeCriterio(Foro.ATRIBUTO_TITULO, Criterio.IGUAL, titulo));
		return foros.isEmpty() ? null : foros.get(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#aniadirModerador(dondeando.modelo.entidades.Foro, dondeando.modelo.entidades.Usuario)
	 */
	public void aniadirModerador(Foro foro, Usuario moderador) {
		if(foro!=null && moderador!=null){
			foro.getModeradores().add(moderador);
			try {
				foroDAO.flush();
			} catch (DAOExcepcion e) {
				log.debug("Error al añadir un moderador a un foro", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioForo#eliminarModerador(dondeando.modelo.entidades.Foro, dondeando.modelo.entidades.Usuario)
	 */
	public void eliminarModerador(Foro foro, Usuario moderador) {
		if(foro!=null && moderador!=null){
			foro.getModeradores().remove(moderador);
			try {
				foroDAO.flush();
			} catch (DAOExcepcion e) {
				log.debug("Error al eliminar un moderador de un foro", e);
			}
		}
		
	}

}
