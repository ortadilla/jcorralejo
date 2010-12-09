package biblioTec.modelo.daos;

import java.util.List;

import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.Usuario;

public interface UsuarioDAO {

	public Usuario encontrarUsuarioPorLogin(String login);

	public Usuario encontrarPorId(Integer id);

	public List<Usuario> encontrarUsuariosPorLoginYPerfil(String usuario, Perfil perfil);

}
