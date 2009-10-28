package dondeando.modelo.entidades.implementacion;

import java.util.Date;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Usuario;

public class OpinionImpl implements Opinion{
	
	private Integer id;
	private Integer version;
	private String opinion;
	private Usuario usuario;
	private Date fecha;
	private Local local;
	
	private String autorYFecha;
	
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
		if (!(obj instanceof OpinionImpl))
			return false;
		final OpinionImpl other = (OpinionImpl) obj;
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
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}

	public String getAutorYFecha() {
		return autorYFecha;
	}

	public void setAutorYFecha(String autorYFecha) {
		this.autorYFecha = autorYFecha;
	}

}
