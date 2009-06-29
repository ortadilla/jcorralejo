package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.varios.NombresBean.PRUEBA_BD;
import static utilidades.varios.NombresBean.PRUEBA_DAO;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.MensajesCore;
import utilidades.varios.NombresBean;
import dondeando.modelo.dao.hibernate.PruebaDAOHibernate;
import dondeando.modelo.servicio.ServicioCriterios;


@Scope(CONVERSATION)
@Name(PRUEBA_BD)
public class PruebaBD{
	
	@In(value=PRUEBA_DAO, create=true, required=true)
	private PruebaDAOHibernate pruebaDAOHibernate;

	@In(value=NombresBean.MENSAJES_CORE, create=true, required=true)
	private MensajesCore mensajesCore;
	
	private Session session;
	private boolean rendered=false;
	private String resultadoSelect;
	private String mensaje = "Prueba BD: ";
	
	@In(value=SERVICIO_CRITERIOS, create=true, required=true)
	private ServicioCriterios servicioCriterios;
	
	public void accionBoton(){
		
		rendered=true;
		session = pruebaDAOHibernate.getLaDatabaseEscritura();
		SQLQuery sql = session.createSQLQuery("select Nombre_usuario from tabla_prueba");
		List resultado = sql.list();
		resultadoSelect = mensajesCore.obtenerTexto("RESULTADOS")+": "+resultado.get(0).toString();
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
