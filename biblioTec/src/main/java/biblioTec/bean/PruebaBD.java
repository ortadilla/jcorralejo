package biblioTec.bean;

import static biblioTec.utilidades.NombresBean.PRUEBA_BD;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.entidades.Ejemplo;
import biblioTec.modelo.entidades.implementacion.EjemploImpl;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.NombresBean;

@Scope(CONVERSATION)
@Name(PRUEBA_BD)
public class PruebaBD{
	
	@In(value=NombresBean.SESION_HIBERNATE, create=true)
    private Session laDatabase;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
	private boolean rendered=false;
	private String resultadoSelect;
	private String mensaje = "Prueba BD: ";
	
	public void accionBoton(){
		rendered = true;
		
		Ejemplo e = new EjemploImpl();
		e.setNombre("insertate!!!");
		laDatabase.save(e);
		laDatabase.flush();
		
		
		Criteria criteria = laDatabase.createCriteria(EjemploImpl.class);
		List<Ejemplo> resultado = criteria.list();
		resultadoSelect = mensajesCore.obtenerTexto("RESULTADOS")+": "+resultado.get(0).getNombre();
	}
	
	public String accionNavegar(){
		return "menu";
	}
	
	
	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public String getResultadoSelect() {
		return resultadoSelect;
	}

	public void setResultadoSelect(String resultadoSelect) {
		this.resultadoSelect = resultadoSelect;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
