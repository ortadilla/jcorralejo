package dondeando.modelo.entidades.implementacion;

import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;

public class InteresImpl implements Interes{
	
	private Integer id;
	private Integer version;
	private Usuario usuario;
	private TipoInteres tipoInteres;
	private Integer objetoInteres;
	private boolean enviarEmail;
	
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
		if (!(obj instanceof InteresImpl))
			return false;
		final InteresImpl other = (InteresImpl) obj;
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
	public Integer getObjetoInteres() {
		return objetoInteres;
	}
	public void setObjetoInteres(Integer objetoInteres) {
		this.objetoInteres = objetoInteres;
	}
	public boolean isEnviarEmail() {
		return enviarEmail;
	}
	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}
	
	

}
