package dondeando.modelo.entidades.implementacion;

import java.util.Set;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;

public class ForoImpl implements Foro{
	
	private Integer id;
	private Integer version;
	private String descripcion;
	private String titulo;
	private boolean activo;
	Set<Usuario> moderadores;
	Set<MensajeForo> mensajes;
	
	private int numeroTemas;
	private int numeroMensajes;
	private String descripcionUltimoPost;
	
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
		if (!(obj instanceof ForoImpl))
			return false;
		final ForoImpl other = (ForoImpl) obj;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Set<Usuario> getModeradores() {
		return moderadores;
	}
	public void setModeradores(Set<Usuario> moderadores) {
		this.moderadores = moderadores;
	}
	public Set<MensajeForo> getMensajes() {
		return mensajes;
	}
	public void setMensajes(Set<MensajeForo> mensajes) {
		this.mensajes = mensajes;
	}
	public int getNumeroTemas() {
		return numeroTemas;
	}
	public void setNumeroTemas(int numeroTemas) {
		this.numeroTemas = numeroTemas;
	}
	public int getNumeroMensajes() {
		return numeroMensajes;
	}
	public void setNumeroMensajes(int numeroMensajes) {
		this.numeroMensajes = numeroMensajes;
	}
	public String getDescripcionUltimoPost() {
		return descripcionUltimoPost;
	}
	public void setDescripcionUltimoPost(String descripcionUltimoPost) {
		this.descripcionUltimoPost = descripcionUltimoPost;
	}

}
