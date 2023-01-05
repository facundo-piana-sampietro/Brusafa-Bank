package negocioImpl;

import java.util.ArrayList;

import dao.ProvinciasDao;
import daoImpl.ProvinciasDaoImpl;
import entidades.Provincias;
import negocio.ProvinciasNegocio;

public class ProvinciasNegocioImpl implements ProvinciasNegocio{
	ProvinciasDao provDao = new ProvinciasDaoImpl();
	@Override
	public ArrayList<Provincias> readALL() {
		return provDao.readALL();
	}

}
