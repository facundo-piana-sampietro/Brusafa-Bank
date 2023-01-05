package dao;

import java.util.ArrayList;
import java.util.List;

import entidades.Usuarios;

public interface UsuariosDao {
	public Boolean insert(Usuarios usuario);
	public boolean delete (String dni);
	public boolean edit(Usuarios usuario);
	public ArrayList<Usuarios> readALL();
	public Usuarios readOne(String nombreUsuario);
	public boolean existeUsuario(String user, String contra);
} 
