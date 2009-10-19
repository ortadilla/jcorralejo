package dondeando.modelo.entidades;

import java.util.Date;


public interface MensajeForo {

	public static final String ATRIBUTO_FECHA = "fecha";
	public static final String ATRIBUTO_RESPONDE_A = "respondeA";
	
	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public String getTitulo();
	public void setTitulo(String titulo);

	public String getMensaje();
	public void setMensaje(String mensaje);
	
	public Usuario getAutor();
	public void setAutor(Usuario autor);
	
	public Date getFecha();
	public void setFecha(Date fecha);
	
	public MensajeForo getRespondeA();
	public void setRespondeA(MensajeForo respondeA);
	
	public Foro getForo();
	public void setForo(Foro foro);
	
}
