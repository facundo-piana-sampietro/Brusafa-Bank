package dao;

import java.util.ArrayList;

import entidades.Localidades;

public interface LocalidadesDao {
	public ArrayList<Localidades> GetAllLocalidadesPorProv(int idProv); 
	public int buscarNumLoc(String descripcion);
}
