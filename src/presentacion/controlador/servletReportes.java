package presentacion.controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuentas;
import entidades.Movimientos;
import entidades.Prestamos;
import entidades.TipoMovimientos;
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocio.PrestamosNegocio;
import negocio.UsuariosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;
import negocioImpl.PrestamosNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.UsuariosNegocioImpl;

/**
 * Servlet implementation class servletReportes
 */
@WebServlet("/servletReportes")
public class servletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentasNegocio negCuentas = new CuentasNegocioImpl();
	UsuariosNegocio negUser= new UsuariosNegocioImpl();
	MovimientosNegocioImpl negMov = new MovimientosNegocioImpl();
	ProvinciasNegocioImpl negProv = new ProvinciasNegocioImpl();
	PrestamosNegocioImpl negPrest = new PrestamosNegocioImpl();
  
    public servletReportes() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if (request.getParameter("ParamRep") != null)	
		//{
			armarMovimientosFiltrados(request);
			
			cargarDDlProvincias(request);
			
			armarUsuariosFiltrados(request);
				
			cargarCuentas(request);
			
			cargarPrestamos(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");
			rd.forward(request, response);
		//}
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if (request.getParameter("ParamRep") != null)	
		//{
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");
			rd.forward(request, response);
		//}
	}
	
	private void armarMovimientosFiltrados(HttpServletRequest request) 
	{
		ArrayList <Movimientos> listaMovimientos = negMov.obtenerTodos();
		
		if (request.getParameter("txtDniRemitenteMovimiento") != null && request.getParameter("txtDniRemitenteMovimiento") != "")
		{
			listaMovimientos.removeIf(s -> !s.getCuenta_Mov().getUsuario_Cuentas().getDNI_Usr().startsWith(request.getParameter("txtDniRemitenteMovimiento")));
		}
		
		if (request.getParameter("txtDniReceptorMovimiento") != null && request.getParameter("txtDniReceptorMovimiento") != "")
		{
			listaMovimientos.removeIf(s -> !s.getCuentaDestino_Mov().getUsuario_Cuentas().getDNI_Usr().startsWith(request.getParameter("txtDniReceptorMovimiento")));
		}
		
		if (request.getParameter("importe1Movimiento") != null && request.getParameter("importe2Movimiento") != null) 
		{
			try 
			{
				listaMovimientos.removeIf(s -> 
				s.getImporte_Mov() > Float.parseFloat(request.getParameter("importe2Movimiento")) ||
				s.getImporte_Mov() < Float.parseFloat(request.getParameter("importe1Movimiento"))
				);
			}
			catch(Exception e)
			{
				
			}
		}
		
		if (request.getParameter("ddlTipos") != null)
		{
			if (!request.getParameter("ddlTipos").equals("-1"))
			{
				listaMovimientos.removeIf(s -> s.getTiposMov_Mov().getId_TiposMov() != Integer.parseInt(request.getParameter("ddlTipos")));
			}
		}
			
		if (request.getParameter("btnFiltrarMovimientos") != null)
			request.setAttribute("listaMovimientosFiltrada", listaMovimientos);
		else
			request.setAttribute("listaMovimientosFiltrada", negMov.obtenerTodos());
	}
	
	private void armarUsuariosFiltrados(HttpServletRequest request) {
		ArrayList <Usuarios> listaUsuarios = negUser.readALL();
		
		if (request.getParameter("txtNombreCliente") != "" && request.getParameter("txtNombreCliente") != null)
		{
			listaUsuarios.removeIf(s -> !s.getNombre_Usr().toUpperCase().startsWith(request.getParameter("txtNombreCliente").toUpperCase()));
		}
		
		if (request.getParameter("txtApellidoCliente") != "" && request.getParameter("txtApellidoCliente") != null)
		{
			listaUsuarios.removeIf(s -> !s.getApellido_Usr().toUpperCase().startsWith(request.getParameter("txtApellidoCliente").toUpperCase()));
		}
		
		if (request.getParameter("txtDniCliente") != null && request.getParameter("txtDniCliente") != "")
		{
			listaUsuarios.removeIf(s -> !s.getDNI_Usr().startsWith(request.getParameter("txtDniCliente")));
		}
		
		if (request.getParameter("ddlProvinciasCliente") != null)
		{
			if (!request.getParameter("ddlProvinciasCliente").equals("-1"))
				listaUsuarios.removeIf(s -> s.getProvincia_Usr().getIdProvincia_Prov() != Integer.parseInt(request.getParameter("ddlProvinciasCliente")));
		}
			
		if (request.getParameter("btnFiltrarUser") != null)
			request.setAttribute("listaUser", listaUsuarios);
		else
			request.setAttribute("listaUser", negUser.readALL());
	}
	
	private void cargarDDlProvincias(HttpServletRequest request) {
		request.setAttribute("listaProvincias", negProv.readALL());
	}
	
	private void cargarCuentas(HttpServletRequest request) 
	{
		ArrayList <Cuentas> listaCuentas = negCuentas.obtenerTodos();
		
		if (request.getParameter("txtNombreCuentas") != "" && request.getParameter("txtNombreCuentas") != null)
		{
			listaCuentas.removeIf(s -> !s.getUsuario_Cuentas().getDNI_Usr().startsWith(request.getParameter("txtNombreCuentas")));
		}
		
		if (request.getParameter("txtSaldoMenorCuenta") != null && request.getParameter("txtSaldoMayorCuenta") != null) 
		{
			try 
			{
				listaCuentas.removeIf(s -> 
				s.getSaldo_Cuentas() < Float.parseFloat(request.getParameter("txtSaldoMenorCuenta"))  ||
				s.getSaldo_Cuentas() > Float.parseFloat(request.getParameter("txtSaldoMayorCuenta"))
				);
			}
			catch(Exception e)
			{
				
			}
		}
		
		if (request.getParameter("ddlTiposCuentas") != null)
		{
			if (!request.getParameter("ddlTiposCuentas").equals("-1"))
				listaCuentas.removeIf(s -> s.getTipoCuenta_Cuentas().getId_TipoCuenta() != Integer.parseInt(request.getParameter("ddlTiposCuentas")));
		}
			
		if (request.getParameter("btnFiltrarCuentas") != null)
			request.setAttribute("listaCuentas", listaCuentas);
		else
			request.setAttribute("listaCuentas", negCuentas.obtenerTodos());
	}
	
	private void cargarPrestamos(HttpServletRequest request)
	{
		ArrayList<Prestamos> listaPrestamos = negPrest.obtenerTodos();
		
		if (request.getParameter("txtNombrePrestamo") != "" && request.getParameter("txtNombrePrestamo") != null)
		{
			listaPrestamos.removeIf(s -> !s.getCuentaDestino_Pr().getUsuario_Cuentas().getDNI_Usr().startsWith(request.getParameter("txtNombrePrestamo")));
		}
		
		if (request.getParameter("txtImportePagar1") != null && request.getParameter("txtImportePagar2") != null) 
		{
			try 
			{
				listaPrestamos.removeIf(s -> 
				s.getImpResultante_Pr() < Float.parseFloat(request.getParameter("txtImportePagar1"))  ||
				s.getImpResultante_Pr() > Float.parseFloat(request.getParameter("txtImportePagar2"))
				);
			}
			catch(Exception e)
			{
				
			}
		}
		
		if (request.getParameter("txtImportePedido1") != null && request.getParameter("txtImportePedido2") != null) 
		{
			try 
			{
				listaPrestamos.removeIf(s -> 
				s.getImpSolicitado_Pr() < Float.parseFloat(request.getParameter("txtImportePedido1"))  ||
				s.getImpSolicitado_Pr() > Float.parseFloat(request.getParameter("txtImportePedido2"))
				);
			}
			catch(Exception e)
			{
				
			}
		}
		
		if (request.getParameter("txtCuotasPrestamo") != "" && request.getParameter("txtCuotasPrestamo") != null)
		{
			listaPrestamos.removeIf(s -> s.getCantCuotas_Pr() != Integer.parseInt(request.getParameter("txtCuotasPrestamo")));
		}
			
		if (request.getParameter("btnFiltrarPrestamos") != null)
			request.setAttribute("listaPrestamos", listaPrestamos);
		else
			request.setAttribute("listaPrestamos", negPrest.obtenerTodos() );
	}

}
