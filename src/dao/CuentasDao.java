package dao;

import java.util.ArrayList;
import java.util.Date;

import entidades.Cuentas;
import entidades.TipoCuentas;
import entidades.Usuarios;

public interface CuentasDao 
{
	public ArrayList<Cuentas> obtenerTodos();
	public ArrayList<Cuentas> cuentasXPropietario(String DNI);
	public ArrayList<Cuentas> cuentasXFechaCreacion(Date fecha1, Date fecha2);
	public ArrayList<Cuentas> cuentasXTipo(TipoCuentas tipoCuentas);
	public Cuentas cuentaxNro(int nro);
	public boolean insert(Cuentas cuenta); 
	public boolean delete(int id); 
	public boolean modificate(Cuentas cuenta);
	
	public boolean existeCuenta(String CBU);
	
}
