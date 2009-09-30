package dondeando.bean;

import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.MODIFICAR_CONTRASENA_BEAN;
import static utilidades.varios.NombresBean.PROTOCOLO_RESULTADO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioUsuario;

import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;

@Scope(ScopeType.CONVERSATION)
@Name(MODIFICAR_CONTRASENA_BEAN)
public class ModificarContrasenaBean {
	
	private String password;
	private String password2;
	
	//Servicios
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
    
    private ProtocoloEdicion protocoloEdicion;
    
    @Create
	@Begin(join=true)
	public void inicializar(){
	}
    
    public void cargarArgumentosDeEntrada(){
    	if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
    		protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
    	
    	if(protocoloEdicion==null || protocoloEdicion.getObjeto()==null
    	|| !(protocoloEdicion.getObjeto() instanceof Usuario))
    		throw new IllegalAccessError("Es obligatorio indicar el Usuario al que modificar la constreña");
    }
    
	public String aceptar(){
		String outcome = "";
		
		if(password==null || "".equals(password) || password2==null || "".equals(password2))
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("PASSWORD_OBLIGATORIOS"));
		//Contraseñas correctas
		else if(password!=null && password2!=null && !password.equals(password2))
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("PASSWORDS_INCORRECTOS"));
		else{
			servicioUsuario.modificarPasswordUsuario((Usuario)protocoloEdicion.getObjeto(), password);
			utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_ACTUALIZADO"));
			outcome = protocoloEdicion.getOutcomeVuelta();
			configuraMapaVuelta();
			//Limpiamos para futuros usos
			protocoloEdicion = null;
			password = null;
			password2 = null;
		}
		return outcome;
	}
	
	public String cancelar(){
		String outcome = protocoloEdicion.getOutcomeVuelta();
		configuraMapaVuelta();

		//Limpiamos para futuros usos
		protocoloEdicion = null;
		password = null;
		password2 = null;
		
		return outcome;
	}
	
	private void configuraMapaVuelta(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(PROTOCOLO_RESULTADO, protocoloEdicion.getObjeto());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	

}
