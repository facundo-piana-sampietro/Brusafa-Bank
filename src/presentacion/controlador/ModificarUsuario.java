package presentacion.controlador;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Localidades;
import entidades.Provincias;
import entidades.Usuarios;
import negocio.LocalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.UsuariosNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.UsuariosNegocioImpl;


@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProvinciasNegocio provNeg = new ProvinciasNegocioImpl();
	private LocalidadesNegocio locNeg = new LocalidadesNegocioImpl();
	private UsuariosNegocio usrNeg= new UsuariosNegocioImpl();
	
    public ModificarUsuario() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("btnCancelar") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("AdministrarUsuarios.jsp");
			rd.forward(request, response);	
		}
/*
		if (request.getParameter("btnModificar")!=null) {
			request.setAttribute("estadoInsertado", editar(request));
		}	
*/

		refreshPost(request);
		RequestDispatcher rd = request.getRequestDispatcher("AdministrarUsuarios.jsp");
		rd.forward(request, response);	
	}
/*
	private boolean editar(HttpServletRequest request) {
	//	return usrNeg.edit(asignarInputsAEntidad(request));
	}*/
/*

	private Usuarios asignarInputsAEntidad(HttpServletRequest request) {
		String DNI_Usr = request.getParameter("txtDNIEdit");
		String CUIL_Usr = request.getParameter("txtCUILEdit");
	
		String Nacionalidad_Usr  = request.getParameter("txtNacionalidadEdit");
		LocalDate FechaNacimiento_Usr = LocalDate.parse(request.getParameter("FechaEdit"));
		String Direccion_Usr= request.getParameter("txtDireccionEdit");
		System.out.println("id ddllocedit; " + Integer.parseInt(request.getParameter("ddlLocalidadesEdit")));
		Provincias Provincia_Usr = new Provincias(Integer.parseInt(request.getParameter("ddlProvinciasEdit")),null);  
		Localidades Localidad_Usr = new Localidades(Provincia_Usr, 
		Integer.parseInt(request.getParameter("ddlLocalidadesEdit")), null);
		String Email_Usr = request.getParameter("txtEmailEdit");
		String Telefono_Usr = request.getParameter("txtTelefonoEdit");
		boolean Tipo_Usr = Boolean.parseBoolean(request.getParameter("ddlTipoEdit"));
		String Usuario_Usr = request.getParameter("txtUsuarioEdit");
		String Contrasenia_Usr = request.getParameter("txtContraseniaEdit");
		Usuarios usr = new Usuarios (DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, true);
		System.out.println("modificado: " + usr);
		return usr;
	}
*/
	private void refreshPost(HttpServletRequest request) {
		
		//cargarUsuarios(request);
	}

	

}
