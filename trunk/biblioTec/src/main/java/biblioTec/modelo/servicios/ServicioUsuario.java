package biblioTec.modelo.servicios;

import biblioTec.modelo.entidades.Usuario;

public interface ServicioUsuario {

	public boolean autenticar(String login, String passw);

	public Usuario devolverUsuarioActivo();

}
