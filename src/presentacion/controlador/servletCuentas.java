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
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.UsuariosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.UsuariosNegocioImpl;

/**
 * Servlet implementation class servletCuentas
 */
@WebServlet("/servletCuentas")
public class servletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentasNegocio negCuentas = new CuentasNegocioImpl();
	UsuariosNegocio negUser= new UsuariosNegocioImpl();
       
    
    public servletCuentas() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ParamCuentas")!=null) {
			cargarCuentas(request);
			cargarUsuarios(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);
		}
		
		
		
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnCuentas")!=null) {
			agregarCuenta(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);	
		}

		if (request.getParameter("btnEliminar")!=null) {
			eliminarCuenta(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);	
		}
		
		if (request.getParameter("btnModificar")!=null) {
			modificarCuenta(request);
			request.setAttribute("estaModificado", true);
			
			cargarCuentas(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);	
			
		}
		
		if (request.getParameter("btnConfirmarMod")!=null) {
			confirmarModificacion(request);
			cargarCuentas(request);
			cargarUsuarios(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnCancelar")!=null) {
			cargarCuentas(request);
			cargarUsuarios(request);
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarCuentas.jsp");
			rd.forward(request, response);
		}
		
		
	}
	


	private void cargarCuentas(HttpServletRequest request) {
		ArrayList <Cuentas> cuentas = null;
		cuentas = negCuentas.obtenerTodos();
		request.setAttribute("listaCuentas", cuentas);
	}
	private void cargarUsuarios(HttpServletRequest request) {
		ArrayList <Usuarios> users = null;
		users = negUser.readALL();
		request.setAttribute("listaUser", users);
		
	}
	
	private void agregarCuenta(HttpServletRequest request) {
		String DNI = request.getParameter("ddlUsuarios");
		int tipoCuenta = Integer.parseInt(request.getParameter("ddlTipoCuenta"));
		String CBU = request.getParameter("txtCBU");
		Cuentas cuenta= new Cuentas();
		cuenta.getUsuario_Cuentas().setDNI_Usr(DNI);
		cuenta.getTipoCuenta_Cuentas().setId_TipoCuenta(tipoCuenta);
		cuenta.setCBU_Cuentas(CBU);
		int mensaje=negCuentas.insert(cuenta);
		request.setAttribute("mensaje", mensaje);
		
		cargarCuentas(request);
		cargarUsuarios(request);
	}
	
	private void eliminarCuenta(HttpServletRequest request) {
		int id= Integer.parseInt(request.getParameter("hiddenId"));
		int mensajeEliminar = negCuentas.delete(id);
		request.setAttribute("mensajeEliminar", mensajeEliminar);
		cargarCuentas(request);
		cargarUsuarios(request);
	}
	
	private void modificarCuenta(HttpServletRequest request) {
		Cuentas cuentaFiltrada = null;
		ArrayList<Cuentas> cuentas = negCuentas.obtenerTodos();
		for (Cuentas cuenta : cuentas) {
			if (cuenta.getCBU_Cuentas().equals(request.getParameter("hiddenCBU")))
				cuentaFiltrada = cuenta; 
		}
		request.setAttribute("cuentaFiltrada", cuentaFiltrada);
	}
	
	private void confirmarModificacion(HttpServletRequest request) {
		int nroCuenta =  Integer.parseInt(request.getParameter("hiddenNro"));
		int tipoCuenta = Integer.parseInt(request.getParameter("ddlTipoCuenta"));
		String CBU = request.getParameter("txtCBU");
		float saldo = Float.parseFloat(request.getParameter("txtSaldo"));
		Cuentas cuenta = new Cuentas();
		cuenta.setNro_Cuentas(nroCuenta);
		cuenta.getTipoCuenta_Cuentas().setId_TipoCuenta(tipoCuenta);
		cuenta.setCBU_Cuentas(CBU);
		cuenta.setSaldo_Cuentas(saldo);
		int mensajeMod = negCuentas.modificate(cuenta);
		request.setAttribute("mensajeMod", mensajeMod);

	}

}
