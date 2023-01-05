package negocio;

import java.util.ArrayList;
import java.util.Date;

import entidades.Movimientos;
import entidades.TipoMovimientos;
import entidades.Usuarios;

public interface MovimientosNegocio
{
	public ArrayList<Movimientos> obtenerTodos();
	public ArrayList<Movimientos> MovxUsuario(Usuarios usuario);
	public ArrayList<Movimientos> MovxTipo(TipoMovimientos tipo);
	public ArrayList<Movimientos> MovxFecha(String fecha1, String fecha2);
	public ArrayList<Movimientos> MovxCuenta(int nroCuenta);
	public int insert(Movimientos movimiento);
}
