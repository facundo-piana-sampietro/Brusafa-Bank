package presentacion.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuarios;
import negocio.UsuariosNegocio;
import negocioImpl.UsuariosNegocioImpl;

/**
 * Servlet implementation class ServletReportesClientes
 */
@WebServlet("/ServletReportesClientes")
public class ServletReportesClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuariosNegocio negUser= new UsuariosNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportesClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		armarUsuariosFiltrados(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		armarUsuariosFiltrados(request);
		
		RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");
		rd.forward(request, response);
	}
	
	private void armarUsuariosFiltrados(HttpServletRequest request) {
		ArrayList <Usuarios> listaUsuarios = negUser.readALL();
		
		if (request.getParameter("txtNombreCliente") != "")
		{
			listaUsuarios.removeIf(s -> !s.getNombre_Usr().equals(request.getParameter("txtNombreCliente")));
		}
		
		if (request.getParameter("txtApellidoCliente") != "")
		{
			listaUsuarios.removeIf(s -> !s.getNombre_Usr().equals(request.getParameter("txtApellidoCliente")));
		}
			
		if (request.getParameter("btnFiltrarClientes") != null)
			request.setAttribute("listaUser", listaUsuarios);
	}

}
