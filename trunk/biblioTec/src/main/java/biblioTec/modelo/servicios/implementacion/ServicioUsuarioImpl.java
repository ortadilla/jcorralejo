package biblioTec.modelo.servicios.implementacion;

import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
import static biblioTec.utilidades.NombresBean.USUARIO_DAO;
import static biblioTec.utilidades.NombresBean.USUARIO_LOGUEADO;
import static org.jboss.seam.ScopeType.SESSION;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.daos.UsuarioDAO;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.servicios.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_USUARIO)
public class ServicioUsuarioImpl implements ServicioUsuario {
	
    @In(value=USUARIO_LOGUEADO, required=false, scope=SESSION)
    @Out(value=USUARIO_LOGUEADO, required=false, scope=SESSION)
    private Integer usuarioLogueado;
    
    @In(value=USUARIO_DAO, create=true) 
    private UsuarioDAO usuarioDAO;
    
	private Log log = LogFactory.getLog(ServicioUsuarioImpl.class);

	public boolean autenticar(String login, String passw) {
        if(login != null && passw != null){ 
            Usuario us = usuarioDAO.encontrarUsuarioPorLogin(login.toLowerCase());
            
            if(us==null)
                log.debug("No existe el usuario "+login);
            else if(!us.getPass().equals(encriptar(passw)))
                log.debug("Password incorrecta"); 
            else{
                autenticar(us);
                return true;
            }
        }
        return false;
	}
	
	private static String encriptar(String texto){
        if (texto==null)
            return null;
		try {
            return aHexadecimal(MessageDigest.getInstance("SHA-1").digest(texto.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("El algoritmo predefinido no es correcto", e);
		}
	}
	
	private static String aHexadecimal( byte[] bytes ){
	    StringBuffer sb = new StringBuffer();
	    for(byte bi:bytes){
	        String cadAux = Integer.toHexString((int)(0x00FF & bi)); 
	        if(cadAux.length() < 2) //Hay que a�adir un 0 
	            sb.append("0"); 
	        sb.append(cadAux);
	    }
	    return sb.toString();
	}
	
    private void autenticar(Usuario usuario) {
        usuarioLogueado = usuario!=null ? usuario.getId() : null;
    }
    
    public void logout(){
        usuarioLogueado = null;
    }
    
    public Usuario devolverUsuarioActivo() {
    	Usuario usuarioActivo = null;
    	if(usuarioLogueado==null){
    		log.debug("No hay usuario en sesi�n...");
    	}else{
    		usuarioActivo = usuarioDAO.encontrarPorId(usuarioLogueado);
    	}
    	return usuarioActivo;
    }

}