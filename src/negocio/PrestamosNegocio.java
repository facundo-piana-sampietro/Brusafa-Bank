package negocio;

import java.util.ArrayList;
import java.util.Date;

import entidades.Movimientos;
import entidades.Prestamos;
import entidades.Usuarios;

public interface PrestamosNegocio {
	public ArrayList<Prestamos> obtenerTodos();
	public ArrayList<Prestamos> prestXUsuario(Usuarios us);
	public ArrayList<Prestamos> prestXFecha(Date fecha1, Date fecha2);
	public ArrayList<Prestamos> prestXImportePagar(float importe1, float importe2);
	public ArrayList<Prestamos> prestXImportePedido(float importe1, float importe2);
	public ArrayList<Prestamos> prestXCantidadCuotas(int cuotas);
	public int pagarPrestamo(Prestamos prestamo, Movimientos movimiento);
	public boolean agregarPrestamo(Prestamos prestamo);
	public boolean modificarAutorizado(Prestamos prestamo, boolean aprobado);
	public boolean modificarCuotas(Prestamos prestamo);
	public boolean eliminarPrestamo(Prestamos prestamo);
}
