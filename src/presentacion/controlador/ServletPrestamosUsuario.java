package presentacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.classfile.PMGClass;

import entidades.Cuentas;
import entidades.Movimientos;
import entidades.Prestamos;
import entidades.TipoMovimientos;
import entidades.Usuarios;
import negocio.CuentasNegocio;
import negocio.MovimientosNegocio;
import negocio.PrestamosNegocio;
import negocioImpl.CuentasNegocioImpl;
import negocioImpl.MovimientosNegocioImpl;
import negocioImpl.PrestamosNegocioImpl;

/**
 * Servlet implementation class ServletPrestamosUsuario
 */
@WebServlet("/ServletPrestamosUsuario")
public class ServletPrestamosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamosNegocio pn = new PrestamosNegocioImpl();
	private CuentasNegocio cn = new CuentasNegocioImpl();
	MovimientosNegocio mn= new MovimientosNegocioImpl();
       
    public ServletPrestamosUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ParamPrestamos")!=null) {
			cargarCuentasOrigen(request);
			obtenerAllPrestamosXusr(request);
			RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pagarPrestamoLogica(request);
		cargarCuentasOrigen(request);
		solicitarPrestamo(request);
		obtenerAllPrestamosXusr(request);

		RequestDispatcher rd = request.getRequestDispatcher("Prestamos.jsp");
		rd.forward(request, response);
	}
	
	private void solicitarPrestamo(HttpServletRequest request) {
		if(request.getParameter("btnPedirPrestamo") != null) 
			insertPrestamo(request);
	}
	
	private void pagarPrestamoLogica(HttpServletRequest request) {
		if(request.getParameter("btnPagar") != null) 
			pagarPrestamo(request);	
		if(request.getParameter("btnCancelarCuota") != null) 
			request.setAttribute("prestamoAPagar",  new Prestamos());
		if(request.getParameter("btnPagarCuota") != null) 
			pagarCuota(request);			
	}
	
	private void pagarCuota(HttpServletRequest request) {
		Cuentas cuentaOrigen = cn.cuentasXNro(Integer.parseInt(request.getParameter("ddlCuentaOrigenParaPagar")));
		String detalle = "Préstamo: " + request.getParameter("prestamoCuotaPagar") + " - Cuota: " + request.getParameter("txtCuotas");
		float importe = Float.parseFloat(request.getParameter("txtParaPagar"));
		Movimientos movimiento = new Movimientos(cuentaOrigen, new TipoMovimientos(3,"Pago de préstamo"), detalle, importe);
		int res = pn.pagarPrestamo(new Prestamos(Integer.parseInt(request.getParameter("prestamoCuotaPagar"))), movimiento);
		request.setAttribute("mensaje", res);
	}
	
	private void pagarPrestamo(HttpServletRequest request) {
		request.setAttribute("prestamoAPagar",  obtenerPrestamo( Integer.parseInt(request.getParameter("prestamoSeleccionado")), request));
	}
	
	private Prestamos obtenerPrestamo(int idPrestamo, HttpServletRequest request) {
		for(Prestamos p : pn.prestXUsuario((Usuarios)request.getSession().getAttribute("sessionUser"))) {
			if(p.getId_Pr() == idPrestamo) 
				return p;
		}
		return null;
	}
	
	private void insertPrestamo(HttpServletRequest request) {
		request.setAttribute("resultadoPrestamo",  pn.agregarPrestamo(asignarInputsPrestamo(request)));
	}
	
	private void obtenerAllPrestamosXusr(HttpServletRequest request) {
		request.setAttribute("prestamosDeUsr", pn.prestXUsuario((Usuarios)request.getSession().getAttribute("sessionUser")));
	}
	
	private void cargarCuentasOrigen(HttpServletRequest request) {
		Usuarios user = (Usuarios) request.getSession().getAttribute("sessionUser");
		if(request.getParameter("estaPidiendoPrestamo") != null) 
			request.setAttribute("prestamoInsert",  true);		
		if(request.getParameter("btnCancelarInsert") != null) 
			request.setAttribute("prestamoInsert",  false);
		request.setAttribute("listaCuentasOrigen", cn.cuentasXPropietario(user.getDNI_Usr()));
	}
	
	private Prestamos asignarInputsPrestamo(HttpServletRequest request) {
		Usuarios usr = (Usuarios)request.getSession().getAttribute("sessionUser");
	    Cuentas CuentaDestino = obtenerCuentaXNro(Integer.parseInt(request.getParameter("ddlCuentaOrigen")));
	    float ImpSolicitado_Pr = Float.parseFloat(request.getParameter("txtImporte"));
	    float ImpResultante_Pr = (float) (ImpSolicitado_Pr * 1.70);
	    int PlazoMeses_Pr = Integer.parseInt(request.getParameter("ddlCuotas"));
	    float ImpPagoAlMes = ImpResultante_Pr / PlazoMeses_Pr;
	    return new Prestamos(usr, CuentaDestino, ImpSolicitado_Pr, ImpResultante_Pr, PlazoMeses_Pr, ImpPagoAlMes);
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

}
