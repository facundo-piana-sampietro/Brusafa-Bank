package presentacion.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrestamosDao;
import dao.UsuariosDao;
import daoImpl.PrestamosDaoImpl;
import daoImpl.UsuariosDaoImpl;
import entidades.Cuentas;
import entidades.Localidades;
import entidades.Prestamos;
import entidades.Provincias;
import entidades.Usuarios;
import negocio.*;
import negocioImpl.*;
import entidades.Usuarios;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamosNegocio pn = new PrestamosNegocioImpl();
	private CuentasNegocio cn = new CuentasNegocioImpl();

    public ServletPrestamos() {}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		autorizarPrestamo(request);
		obtenerAllPrestamos(request);
		solicitarPrestamo(request);
		
		cargarPrestamos(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("AdministrarPrestamos.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("AdministrarPrestamos.jsp");
		request.setAttribute("listaCuentas", cn.obtenerTodos());
		rd.forward(request, response);
	}
	
	private void solicitarPrestamo(HttpServletRequest request) {
		if(request.getParameter("btnPedirPrestamo") != null) {
			insertPrestamo(request);
		}
	
	}
	
	private void insertPrestamo(HttpServletRequest request) {
		request.setAttribute("resultadoPrestamo",  pn.agregarPrestamo(asignarInputsPrestamo(request)));
	}
	
	private Prestamos asignarInputsPrestamo(HttpServletRequest request) {
		Usuarios usr = (Usuarios)request.getSession().getAttribute("sessionUser");
	    Cuentas CuentaDestino = obtenerCuentaXNro(Integer.parseInt(request.getParameter("ddlCuentaOrigenPrestamo")));
	    float ImpSolicitado_Pr = Float.parseFloat(request.getParameter("txtImporte"));
	    float ImpResultante_Pr = (float) (ImpSolicitado_Pr * 1.70);
	    int PlazoMeses_Pr = Integer.parseInt(request.getParameter("ddlCuotas"));
	    float ImpPagoAlMes = ImpResultante_Pr / PlazoMeses_Pr;
	    Prestamos p = new Prestamos(usr, CuentaDestino, ImpSolicitado_Pr, ImpResultante_Pr, PlazoMeses_Pr, ImpPagoAlMes);	
	    System.out.println("prestamos: " + p);
	    return p;
	}
	
	private Cuentas obtenerCuentaXNro(int nro) {
		for(Cuentas cta : cn.obtenerTodos()) {
			if(cta.getNro_Cuentas() == nro)
				return cta;
		}
		return null;
	}
	
	private void obtenerAllPrestamos(HttpServletRequest request){
		request.setAttribute("listaPrestamos", pn.obtenerTodos());
	}
	
	private void cargarPrestamos (HttpServletRequest request)
	{
		request.setAttribute("listaPrestamos", pn.obtenerTodos());
	}
	
	private void autorizarPrestamo (HttpServletRequest request)
	{
	   
		if (request.getParameter("btnAceptarPrestamo") != null)
		{
			for (Prestamos prestamo : pn.obtenerTodos())
			{
				if (prestamo.getId_Pr() ==  Integer.parseInt(request.getParameter("btnAceptarPrestamo")))
					//Autorizamos el prestamo
					pn.modificarAutorizado(prestamo, true);
					request.setAttribute("modificado", 1);
			}
		}
		if (request.getParameter("btnRechazarPrestamo") != null)
		{
			for (Prestamos prestamo : pn.obtenerTodos())
			{
				if (prestamo.getId_Pr() ==  Integer.parseInt(request.getParameter("btnRechazarPrestamo")))
				{
					//Rechazamos el prestamo
					pn.modificarAutorizado(prestamo, false);
					pn.eliminarPrestamo(prestamo);
					request.setAttribute("modificado", 0);
				}
			}
		}
	}


}
