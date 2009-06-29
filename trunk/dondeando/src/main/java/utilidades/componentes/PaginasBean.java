package utilidades.componentes;

import static utilidades.varios.NombresBean.PAGINAS_BEAN;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioUsuario;


@Name(PAGINAS_BEAN)
@Scope(ScopeType.CONVERSATION)
public class PaginasBean {
	
    @In(value=SERVICIO_USUARIO, create=true)
    private ServicioUsuario servicioUsuario;
    
    private Log log = LogFactory.getLog(PaginasBean.class);
    
    
    /**
     * Método de inicialización. Se une a la conversación que se esté creando
     * para poder controlar el breadcrumb
     */
    @Create
    @Begin(join=true)
    public void inicializar(){
    	log.trace("Inicializando PaginasBean...");
    }
    
    public String getNombreUsuario() {
        String nombre = null; 
        Usuario usuario = servicioUsuario.devolverUsuarioActivo();
        if(usuario!=null)
            nombre = usuario.getNombreCompleto();
        if(nombre==null || nombre.trim().length()==0)
            nombre = usuario.getLogin();
        return nombre+" |";
    }

}
