package presentacion.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuentas;
import entidades.Movimientos;
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;

/**
 * Servlet implementation class servletCuentasUsr
 */
@WebServlet("/servletCuentasUsr")
public class servletCuentasUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentasNegocio negCuentas = new CuentasNegocioImpl();
	MovimientosNegocio negMov = new MovimientosNegocioImpl();
	
	
    public servletCuentasUsr() {
        super();
          
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ParamCuentasUsr")!=null) {
			 cargarCuentas(request);
			 RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
			 rd.forward(request, response);
		}
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnVerMovimientos")!=null) {
			cargarMovimientos(request);
			cargarCuentas(request);
			RequestDispatcher rd = request.getRequestDispatcher("Cuentas.jsp");
			rd.forward(request, response);
		}
	}


	private void cargarCuentas(HttpServletRequest request) {
		Usuarios user = (Usuarios)request.getSession().getAttribute("sessionUser"); 
		List <Cuentas> listaCuentas = new ArrayList<>();
		listaCuentas = negCuentas.cuentasXPropietario(user.getDNI_Usr());
		request.setAttribute("listaCuentas", listaCuentas);
	}

	private void cargarMovimientos(HttpServletRequest request) {
		ArrayList<Movimientos> listaMovimientos;
		int nroCuenta = Integer.parseInt(request.getParameter("hiddenNroCuenta"));
		listaMovimientos = negMov.MovxCuenta(nroCuenta);
		request.setAttribute("listaMovimientos", listaMovimientos);
		
		
	}
}
