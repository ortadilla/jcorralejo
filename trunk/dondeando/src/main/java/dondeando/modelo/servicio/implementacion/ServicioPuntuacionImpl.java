package dondeando.modelo.servicio.implementacion;

import static utilidades.varios.NombresBean.PUNTUACION_DAO;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
import static utilidades.varios.NombresBean.SERVICIO_PUNTUACION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import java.math.BigDecimal;
import java.math.MathContext;
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
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioPuntuacion;
import dondeando.modelo.servicio.ServicioTipoInteres;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_PUNTUACION)
public class ServicioPuntuacionImpl implements ServicioPuntuacion{

	//Utilidades
	@In(value= PUNTUACION_DAO, create=true)
	private PuntuacionDAO puntuacionDAO;
	
    @In(value=SERVICIO_NOTIFICACION, create=true)
    private ServicioNotificacion servicioNotificacion;
    
    @In(value=SERVICIO_USUARIO, create=true)
    private ServicioUsuario servicioUsuario;

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
		puntuacion.setKarmaUsuario(usuario.getKarma());
		
		puntuacionDAO.hacerPersistente(puntuacion);
		//Hay que setearle al local las puntuaciones después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(local!=null){
			if(local.getPuntuaciones()==null)
				local.setPuntuaciones(new HashSet<Puntuacion>());
			
			//Si es la primera puntuación, la media será la media aritmética de esta puntuación
			BigDecimal media = comida.add(ambiente).add(servicio).add(calidadPrecio).divide(new BigDecimal(4));
			if(local.getPuntuaciones().isEmpty())
				puntuacion.setMediaCalculada(media);
			
			else{
				//1. Calculamos la media de esta puntuación
				//2. Con la media del local calculamos el intervalo [media-karma/5,media+karma/5]
				BigDecimal liminteInf = local.getValoracion().subtract(usuario.getKarma().divide(new BigDecimal(5)));
				BigDecimal liminteSup = local.getValoracion().add(usuario.getKarma().divide(new BigDecimal(5)));
				
				//3. Si la media de la puntuación está fuera actualizamos su valor al extremo más cercano,
				//y si no, la dejamos tal cual está
				if(media.compareTo(liminteInf)<0)
					media = liminteInf;
				if(media.compareTo(liminteSup)>0)
					media = liminteSup;
				
				//4. Con esta media calculamos la "Media Bayesiana" (TODO)
				puntuacion.setMediaCalculada(media);
			}
			
			if(!local.getPuntuaciones().contains(puntuacion))
				local.getPuntuaciones().add(puntuacion);
			

			//Antes de actualizar la media del local, hay que actualizar el karma del usuario
			BigDecimal actualizacionKarma = BigDecimal.ZERO;
			if(media.compareTo(local.getValoracion().subtract(new BigDecimal(0.5)))>0 && media.compareTo(local.getValoracion().add(new BigDecimal(0.5)))<0)
				actualizacionKarma = new BigDecimal(0.15);
			else if (media.compareTo(local.getValoracion().subtract(new BigDecimal(1)))<0 || media.compareTo(local.getValoracion().add(new BigDecimal(1)))>0)
				actualizacionKarma = media.subtract(new BigDecimal(1)).abs().divide(BigDecimal.TEN).negate();
			if(BigDecimal.ZERO.compareTo(actualizacionKarma)!=0)
				servicioUsuario.actualizarKarma(ServicioUsuario.OPERACION_VALORAR_LOCAL, actualizacionKarma, null);
			
			
			//Actualizamos la puntuación media del local
			BigDecimal suma = BigDecimal.ZERO;
			for(Puntuacion puntuacionLocal : local.getPuntuaciones())
				suma = suma.add(puntuacionLocal.getMediaCalculada());
			local.setValoracion(suma.divide(new BigDecimal(local.getPuntuaciones().size()), new MathContext(100)));
		}
		//Hay que setearle al usuario las puntuaciones después de hacerlo persistente (para que tenga id y se pueda borrar justo después)
		if(usuario!=null){
			if(usuario.getPuntuaciones()==null)
				usuario.setPuntuaciones(new HashSet<Puntuacion>());
			if(!usuario.getPuntuaciones().contains(puntuacion))
				usuario.getPuntuaciones().add(puntuacion);
		}
		
		//Enviamos las notificaciones
		servicioNotificacion.enviarNotificacionesTipoObjeto(ServicioTipoInteres.TIPO_VALORACION_LOCAL, local, puntuacion);
		
		return puntuacion;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioPuntuacion#encontrarTodas()
	 */
	public List<Puntuacion> encontrarTodas() {
		return puntuacionDAO.encontrarTodos();
	}
}
