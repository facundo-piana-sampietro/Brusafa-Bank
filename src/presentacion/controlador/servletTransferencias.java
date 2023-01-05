package presentacion.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuentas;
import entidades.Movimientos;
import entidades.TipoMovimientos;
import entidades.Usuarios;
import exceptions.ImporteNegativo;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;


@WebServlet("/servletTransferencias")
public class servletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentasNegocio negCuentas = new CuentasNegocioImpl();
	MovimientosNegocio negMov= new MovimientosNegocioImpl();
       
    
    public servletTransferencias() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ParamTransferencias")!=null) {
			cargarCuentasOrigen(request);
			cargarCuentasDestino(request);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);	
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnTransferir")!=null) {
			try {
				Cuentas.verificarImporte(Float.parseFloat(request.getParameter("txtImporte")));
				transferir(request);
			}
			catch (ImporteNegativo e) {
				request.setAttribute("importeNegativo", true);
			}
			
			cargarCuentasOrigen(request);
			cargarCuentasDestino(request);
			RequestDispatcher rd = request.getRequestDispatcher("Transferencias.jsp");
			rd.forward(request, response);
		}
  
	}

	

	private void cargarCuentasOrigen(HttpServletRequest request) {
		Usuarios user = (Usuarios) request.getSession().getAttribute("sessionUser");
		ArrayList<Cuentas> listaCuentasOrigen = negCuentas.cuentasXPropietario(user.getDNI_Usr());
		request.setAttribute("listaCuentasOrigen", listaCuentasOrigen);
		
	}
	
	private void cargarCuentasDestino(HttpServletRequest request) {
		ArrayList<Cuentas> listaCuentasDestino = negCuentas.obtenerTodos();
		request.setAttribute("listaCuentasDestino", listaCuentasDestino);
		
	}
	
	private void transferir(HttpServletRequest request) {
		Cuentas cuentaOrigen = negCuentas.cuentasXNro(Integer.parseInt(request.getParameter("ddlCuentaOrigen")));
		Cuentas cuentaDestino = negCuentas.cuentasXNro(Integer.parseInt(request.getParameter("ddlCuentaDestino")));
		String detalle = request.getParameter("txtDetalle");
		float importe = Float.parseFloat(request.getParameter("txtImporte"));
		Movimientos movimiento = new Movimientos(cuentaOrigen, new TipoMovimientos(4,"Transferencia"),detalle,cuentaDestino,importe);
		int mensaje = negMov.insert(movimiento);
		request.setAttribute("mensaje", mensaje);
	}
}
