package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.PUNTUACION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_PUNTUACION;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import dondeando.modelo.dao.PuntuacionDAO;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.PuntuacionImpl;
import dondeando.modelo.servicio.ServicioPuntuacion;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_PUNTUACION)
public class ServicioPuntuacionImpl implements ServicioPuntuacion{

	//Utilidades
	@In(value= PUNTUACION_DAO, create=true)
	private PuntuacionDAO puntuacionDAO;

	private Log log = LogFactory.getLog(ServicioPuntuacionImpl.class);

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioPuntuacion#rellenarPropiedadesNoMapeadas(java.util.List)
	 */
	public void rellenarPropiedadesNoMapeadas(List<Puntuacion> listaPuntuaciones) {
		for(Puntuacion puntuacion : listaPuntuaciones){
			PuntuacionImpl puntuacionImpl = (PuntuacionImpl)puntuacion;
			puntuacionImpl.setAutorYFecha(puntuacion.getUsuario().getLogin()+" ("+puntuacion.getFecha()+")");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioPuntuacion#crearPuntuacionLocal(dondeando.modelo.entidades.Local, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, java.lang.String, dondeando.modelo.entidades.Usuario)
	 */
	public Puntuacion crearPuntuacionLocal(Local local, BigDecimal comida,
										   BigDecimal ambiente, BigDecimal servicio, BigDecimal calidadPrecio,
										   String loMejor, String loPeor, Usuario usuario) {
		Puntuacion puntuacion = new PuntuacionImpl();
		
		//Seteamos los datos
		puntuacion.setLocal(local);
		puntuacion.setComida(comida);
		puntuacion.setAmbiente(ambiente);
		puntuacion.setServicio(servicio);
		puntuacion.setCalidadPrecio(calidadPrecio);
		puntuacion.setLoMejor(loMejor);
		puntuacion.setLoPeor(loPeor);
		puntuacion.setUsuario(usuario);
		puntuacion.setFecha(new Date());
		
		puntuacionDAO.hacerPersistente(puntuacion);
		//Hay que setearle el tema después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(local!=null){
			if(local.getPuntuaciones()==null)
				local.setPuntuaciones(new HashSet<Puntuacion>());
			if(!local.getPuntuaciones().contains(puntuacion))
				local.getPuntuaciones().add(puntuacion);
		}
		//Hay que setearle el tema después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(usuario!=null){
			if(usuario.getPuntuaciones()==null)
				usuario.setPuntuaciones(new HashSet<Puntuacion>());
			if(!usuario.getPuntuaciones().contains(puntuacion))
				usuario.getPuntuaciones().add(puntuacion);
		}
		return puntuacion;
	}
}
