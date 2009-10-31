package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;

public class NotificacionImpl implements Notificacion {
	
	private Integer id;
	private Integer version;
	private Usuario usuario;
	private TipoInteres tipoInteres;
	private boolean leida;
	private String mensaje;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof NotificacionImpl))
			return false;
		final NotificacionImpl other = (NotificacionImpl) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public TipoInteres getTipoInteres() {
		return tipoInteres;
	}
	public void setTipoInteres(TipoInteres tipoInteres) {
		this.tipoInteres = tipoInteres;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isLeida() {
		return leida;
	}
	public void setLeida(boolean leida) {
		this.leida = leida;
	}

}
