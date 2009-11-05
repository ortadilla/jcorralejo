package dondeando.modelo.entidades;

public interface Notificacion {
	
	public static final String ATRIBUTO_USUARIO = "usuario";
		public static final String ATRIBUTO_LEIDA = "leida";

	public Integer getId();
	public void setId(Integer id);
	
	public Integer getVersion();
	public void setVersion(Integer version);
	
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	
	public TipoInteres getTipoInteres();
	public void setTipoInteres(TipoInteres tipoInteres);
	
	public boolean isLeida();
	public void setLeida(boolean leida);
	
	public String getMensaje();
	public void setMensaje(String mensaje);
}
