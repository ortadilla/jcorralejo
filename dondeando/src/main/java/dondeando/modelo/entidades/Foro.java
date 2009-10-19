package dondeando.modelo.entidades;

import java.util.Set;

public interface Foro {

	public static final String ATRIBUTO_TITULO = "titulo";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getTitulo();
	public void setTitulo(String titulo);

	public String getDescripcion();
	public void setDescripcion(String descripcion);
	
	public boolean isActivo();
	public void setActivo(boolean activo);
	
	public Set<Usuario> getModeradores();
	public void setModeradores(Set<Usuario> moderadores);
	
}
