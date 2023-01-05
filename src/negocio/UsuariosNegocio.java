package negocio;

import java.util.ArrayList;

import entidades.Usuarios;

public interface UsuariosNegocio {

	public Boolean insert(Usuarios usuario);
	public boolean delete(String DNI);
	public boolean edit(Usuarios usuario);
	public boolean existeUsuario(String user, String contra);
	public ArrayList<Usuarios> readALL();
	public Usuarios readOne(String user);
}
