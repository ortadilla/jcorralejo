package biblioTec.bean;

import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_USUARIOS;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.MTO_USUARIO_BEAN;
import static org.jboss.seam.ScopeType.CONVERSATION;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.UsuarioImpl;
import biblioTec.utilidades.ConstantesArgumentosNavegacion;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.NombresBean;



@Scope(CONVERSATION)
@Name(MTO_USUARIO_BEAN)
public class MtoUsuarioBean {
	
	private String titulo;
	private String login;
	private String nombre;
	private String perfiles;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null){

			if(mapaArgumentos.contiene(ConstantesArgumentosNavegacion.USUARIO)){
				Usuario usuario = (Usuario) mapaArgumentos.getArgumento(ConstantesArgumentosNavegacion.USUARIO); 
				login = usuario.getLogin();
				nombre = usuario.getNombre();
				perfiles = ((UsuarioImpl)usuario).getDescPerfiles();
			}
		}
	}
	
	public String volver(){
		titulo = null;
		login = null;
		nombre = null;
		perfiles = null;
		return GESTIONAR_USUARIOS;
	}
	
	public String verPrestamos(){
		return null;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(String perfiles) {
		this.perfiles = perfiles;
	}

}
