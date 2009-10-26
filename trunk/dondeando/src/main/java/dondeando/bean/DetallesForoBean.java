package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_TEMAS_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.DETALLES_FORO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.MapaArgumentos;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Foro;

@Scope(ScopeType.CONVERSATION)
@Name(DETALLES_FORO_BEAN)
public class DetallesForoBean {

	private String titulo;
	private String descripcion;
	
	private Foro foroEdicion;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	private ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			//Comprobamos si estamos editando un usuario ya existente
			if(protocoloEdicion.getObjeto()!=null){
				foroEdicion = (Foro)protocoloEdicion.getObjeto();
				titulo = foroEdicion.getTitulo();
				descripcion = foroEdicion.getDescripcion();
			}
		}

	}
	
	public String volver(){
		return protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta()
									  : MENU_PRINCIPAL;
	}
	
	/**
	 * Navega a los temas de foro
	 * @return Regla de navegación
	 */
	public String verTemas(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(foroEdicion, DETALLES_FORO, null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return GESTION_TEMAS_FORO;
	}
	
	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}

	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
