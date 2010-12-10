package biblioTec.bean;

import static biblioTec.utilidades.NombresBean.LOGIN_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.servicios.ServicioUsuario;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.Redirect;
import biblioTec.utilidades.UtilJsfContext;

@Scope(CONVERSATION)
@Name(LOGIN_BEAN)
public class LoginBean {
	
	private String user;
	private String pass;
	
	private MensajesCore mensajesCore = MensajesCore.instancia();
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	private Log log = LogFactory.getLog(LoginBean.class);
	
	
	public void login(){
		if(servicioUsuario.autenticar(user, pass)){
			log.info("Login correcto para : " + user);

			pass = null;

			Redirect.redirigirInicio();
		}else{
			log.info("Login erróneo para: " + user);
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("LOGIN_INCORRECTO"));
		}
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}

}
