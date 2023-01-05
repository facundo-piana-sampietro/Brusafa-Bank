package negocioImpl;

import java.util.ArrayList;
import java.util.Date;
import dao.PrestamosDao;
import entidades.Movimientos;
import entidades.Prestamos;
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocio.PrestamosNegocio;
import daoImpl.PrestamosDaoImpl;

public class PrestamosNegocioImpl implements PrestamosNegocio {

	private PrestamosDaoImpl prDao = new PrestamosDaoImpl();
	private CuentasNegocio cn = new CuentasNegocioImpl();
	private MovimientosNegocio mn= new MovimientosNegocioImpl();
	
	@Override
	public ArrayList<Prestamos> obtenerTodos() {
		return prDao.obtenerTodos();
	}

	@Override
	public ArrayList<Prestamos> prestXUsuario(Usuarios us) {
		return prDao.prestXUsuario(us);
	}

	@Override
	public ArrayList<Prestamos> prestXFecha(Date fecha1, Date fecha2) {
		return prDao.prestXFecha(fecha1, fecha2);
	}

	@Override
	public ArrayList<Prestamos> prestXImportePagar(float importe1, float importe2) {
		return prDao.prestXImportePagar(importe1, importe2);
	}

	@Override
	public ArrayList<Prestamos> prestXImportePedido(float importe1, float importe2) {
		return prDao.prestXImportePedido(importe1, importe2);
	}

	@Override
	public ArrayList<Prestamos> prestXCantidadCuotas(int cuotas) {
		return prDao.prestXCantidadCuotas(cuotas);
	}

	@Override
	public boolean modificarAutorizado(Prestamos prestamo, boolean aprobado) {
		return prDao.modificarAutorizado(prestamo, aprobado);
	}

	@Override
	public boolean eliminarPrestamo(Prestamos prestamo) {
		return prDao.eliminarPrestamo(prestamo);
	}

	@Override
	public boolean agregarPrestamo(Prestamos prestamo) {
		return prDao.agregarPrestamo(prestamo);
	}
	

	@Override
	public int pagarPrestamo(Prestamos prestamo, Movimientos moviento) {
		int idResultado = mn.insert(moviento); 
		if(idResultado == 1) {
			modificarCuotas(prestamo); //agrega uno al contador de cuotas pagads
			return idResultado;
		}
		return idResultado;
	}

	@Override
	public boolean modificarCuotas(Prestamos prestamo) {
		prDao.modificarCuotas(prestamo);
		return false;
	}
}
