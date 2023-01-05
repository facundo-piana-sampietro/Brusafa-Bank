package negocioImpl;

import java.util.ArrayList;
import java.util.Date;

import dao.MovimientosDao;
import daoImpl.MovimientosDaoImpl;
import entidades.Movimientos;
import entidades.TipoMovimientos;
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;

public class MovimientosNegocioImpl implements MovimientosNegocio {

	MovimientosDao md = new MovimientosDaoImpl();
	CuentasNegocio negCuentas = new CuentasNegocioImpl();
	
	@Override
	public ArrayList<Movimientos> obtenerTodos() {
		return md.obtenerTodos();
	}

	@Override
	public ArrayList<Movimientos> MovxUsuario(Usuarios usuario) {
		return md.MovxUsuario(usuario);
	}

	@Override
	public ArrayList<Movimientos> MovxTipo(TipoMovimientos tipo) {
		return md.MovxTipo(tipo);
	}

	@Override
	public ArrayList<Movimientos> MovxFecha(String fecha1, String fecha2) {
		return md.MovxFecha(fecha1, fecha2);
	}

	@Override
	public ArrayList<Movimientos> MovxCuenta(int nroCuenta) {
		return md.MovxCuenta(nroCuenta);
	}
	
	/*
	 * Estados sobre errores de movimientos
	 * -2: intento de transferencia a una misma cuenta
	 * -1: importe mayor a el saldo de una cuenta
	 * 0: problema en el insert despues de pasar las dos primeras validaciones.
	 * 1: insert exitoso
	 * cuenta destino puede ser nulo.
	 * @see negocio.MovimientosNegocio#insert(entidades.Movimientos)
	 */

	@Override
	public int insert(Movimientos movimiento) {
		if(movimiento.getCuentaDestino_Mov() != null) 
			if (movimiento.getCuenta_Mov().getNro_Cuentas() == movimiento.getCuentaDestino_Mov().getNro_Cuentas())  //transferencia a una misma cuenta
				return -2;
		
		if (movimiento.getImporte_Mov() > movimiento.getCuenta_Mov().getSaldo_Cuentas())  //importe mayor a saldo de cuenta
			return -1;
		
		System.out.println(movimiento.getTiposMov_Mov().getId_TiposMov() + " - " + movimiento.getImporte_Mov());
		if(md.insert(movimiento)) 
			return 1;
		return 0;
	}

}
