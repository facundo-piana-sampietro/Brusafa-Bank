package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.CuentasDao;
import daoImpl.CuentasDaoImpl;
import entidades.Cuentas;
import entidades.TipoCuentas;
import entidades.Usuarios;
import negocio.CuentasNegocio;

public class CuentasNegocioImpl implements CuentasNegocio {

	private CuentasDao cd = new CuentasDaoImpl();
	
	@Override
	public ArrayList<Cuentas> obtenerTodos() {
		return cd.obtenerTodos();
	}

	@Override
	public ArrayList<Cuentas> cuentasXPropietario(String DNI) {
		return cd.cuentasXPropietario(DNI);
	}

	@Override
	public ArrayList<Cuentas> cuentasXFechaCreacion(Date fecha1, Date fecha2) {
		return cd.cuentasXFechaCreacion(fecha1, fecha2);
	}

	@Override
	public ArrayList<Cuentas> cuentasXTipo(TipoCuentas tipoCuentas) {
		return cd.cuentasXTipo(tipoCuentas);
	}
	
	@Override
	public Cuentas cuentasXNro(int nroCuenta) {
		return cd.cuentaxNro(nroCuenta);
	}

	@Override
	public int insert(Cuentas cuenta) {
		if (cd.existeCuenta(cuenta.getCBU_Cuentas())==false) {
			ArrayList <Cuentas> listaCuentas = cd.cuentasXPropietario(cuenta.getUsuario_Cuentas().getDNI_Usr());
			if (listaCuentas.size()<3) {
				boolean agrego=cd.insert(cuenta);
				if (agrego==true) return 1;
				else return -2; //Si hubo algun error al agregar de cualquier tipo
			}
			else {
				//Si ya tiene 3 cuentas
				return -1;
			}
		}
		else {
		//Si ya existe una cuenta con ese CBU
		return 0;
		}
	}
	
	public int delete(int id) {
		if (cd.delete(id)) return 1;
		else return 0;
	}

	@Override
	public int modificate(Cuentas cuenta) {
		Cuentas cuentaAntesMod = cd.cuentaxNro(cuenta.getNro_Cuentas());
		if (cuenta.getCBU_Cuentas().equals(cuentaAntesMod.getCBU_Cuentas())) {
			if(cd.modificate(cuenta))return 1;
			else return -1;			
		}
		
		else if (cd.existeCuenta(cuenta.getCBU_Cuentas())==false) {
			if(cd.modificate(cuenta))return 1;
			else return -1;
		}
		
		else return 0;
		
	}

	
}
