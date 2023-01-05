package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadesDao;
import daoImpl.LocalidadesDaoImpl;
import entidades.Localidades;
import negocio.LocalidadesNegocio;

public class LocalidadesNegocioImpl implements LocalidadesNegocio {
	LocalidadesDao locDao = new LocalidadesDaoImpl();
	@Override
	public ArrayList<Localidades> GetAllLocalidadesPorProv(int idProv) {
		return locDao.GetAllLocalidadesPorProv(idProv);
	}
	@Override
	public int buscarNumLoc(String descripcion) {
		return locDao.buscarNumLoc(descripcion);
	}
}
