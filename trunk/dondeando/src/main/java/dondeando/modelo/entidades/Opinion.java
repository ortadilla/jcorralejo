package dondeando.modelo.entidades;

import java.util.Date;

public interface Opinion {
	
	public static final String ATRIBUTO_USUARIO = "usuario";
	public static final String ATRIBUTO_FECHA = "fecha";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getOpinion();
	public void setOpinion(String opinion);

	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	
	public Local getLocal();
	public void setLocal(Local local);
	
	public Date getFecha();
	public void setFecha(Date fecha);
}
