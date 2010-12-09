package biblioTec.modelo.daos;

import biblioTec.modelo.entidades.Usuario;

public interface PermisoPerfilDAO {
	
	public boolean hayPermisoParaUsuario(Usuario usuario, Integer permiso);

}
