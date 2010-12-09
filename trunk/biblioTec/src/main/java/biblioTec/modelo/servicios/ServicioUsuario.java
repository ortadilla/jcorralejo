package biblioTec.modelo.servicios;

import java.util.List;

import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.Usuario;

public interface ServicioUsuario {

	public boolean autenticar(String login, String passw);

	public Usuario devolverUsuarioActivo();
	
	public List<Usuario> encontrarUsuariosPorLoginYPerfil(String usuario, Perfil perfil);

}
