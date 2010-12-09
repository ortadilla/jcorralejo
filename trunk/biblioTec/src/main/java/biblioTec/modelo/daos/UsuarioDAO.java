package biblioTec.modelo.daos;

import biblioTec.modelo.entidades.Usuario;

public interface UsuarioDAO {
	
	public Usuario encontrarUsuarioPorLogin(String login);
	
	public Usuario encontrarPorId(Integer id);

}
