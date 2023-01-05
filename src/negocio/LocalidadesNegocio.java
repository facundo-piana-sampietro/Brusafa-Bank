package negocio;

import java.util.ArrayList;

import entidades.Localidades;

public interface LocalidadesNegocio {
	public ArrayList<Localidades> GetAllLocalidadesPorProv(int idProv);
	public int buscarNumLoc(String descripcion);
}
