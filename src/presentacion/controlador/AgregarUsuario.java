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
import exceptions.DniInvalido;
import negocio.LocalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.UsuariosNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.UsuariosNegocioImpl;


@WebServlet("/AgregarUsuario")
public class AgregarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuariosNegocio usrNeg= new UsuariosNegocioImpl();
	private ProvinciasNegocio provNeg = new ProvinciasNegocioImpl();
	private LocalidadesNegocio locNeg = new LocalidadesNegocioImpl();
	
    public AgregarUsuario() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if (request.getParameter("btnAgregarUsr")!=null) {
			try {
			Usuarios.verificarDniInvalido(request.getParameter("txtDNI"));
			request.setAttribute("estadoInsertado", insert(request));
			}
			catch (DniInvalido e) {
				request.setAttribute("dniInvalido", true);
			}
		}
			
		refreshPost(request);
		RequestDispatcher rd = request.getRequestDispatcher("AdministrarUsuarios.jsp");
		rd.forward(request, response);	
	}
		
	private void refreshPost(HttpServletRequest request) {
		cargarProvinciasUser(request);
		cargarLocalidadesUser(request);
		cargarUsuarios(request);
	}
	
	private void cargarProvinciasUser(HttpServletRequest request) {		
		request.getSession().setAttribute("provincias", provNeg.readALL());
	}
	
	private void cargarLocalidadesUser(HttpServletRequest request) {
		request.getSession().setAttribute("localidades", locNeg.GetAllLocalidadesPorProv(-1));
	}
	
	private Boolean insert(HttpServletRequest request) {
		
		if(asignarInputsAEntidad(request) != null) {
			return usrNeg.insert(asignarInputsAEntidad(request));
		}
		return false;
		
	}
	
	private Boolean contraseniaVerificada(HttpServletRequest request) {
		String contra1 = request.getParameter("txtContrasenia");
		String contra2 = request.getParameter("txtContraseniaConfirm");
		if(contra1.equals(contra2))
			return true;
		return false;
	}
	
	private Usuarios asignarInputsAEntidad(HttpServletRequest request) {
		if(!contraseniaVerificada(request)) {
			request.setAttribute("contraseniaEstado", false);
			return null;
		}
		request.setAttribute("contraseniaEstado", true);
		String DNI_Usr = request.getParameter("txtDNI");
		String CUIL_Usr = request.getParameter("txtCUIL");
		String Nombre_Usr = request.getParameter("txtNombre");
		String Apellido_Usr = request.getParameter("txtApellido");
		String Sexo_Usr = request.getParameter("rbSexo");
		String Nacionalidad_Usr  = request.getParameter("txtNacionalidad");
		LocalDate FechaNacimiento_Usr = LocalDate.parse(request.getParameter("Fecha"));
		String Direccion_Usr= request.getParameter("txtDireccion");
		Provincias Provincia_Usr = new Provincias(Integer.parseInt(request.getParameter("ddlProvincias")),null);  
		Localidades Localidad_Usr = new Localidades(Provincia_Usr,
				Integer.parseInt(request.getParameter("ddlLocalidades")), null);
		String Email_Usr = request.getParameter("txtEmail");
		String Telefono_Usr = request.getParameter("txtTelefono");
		boolean Tipo_Usr = Boolean.parseBoolean(request.getParameter("ddlTipo"));
		String Usuario_Usr = request.getParameter("txtUsuario");
		String Contrasenia_Usr = request.getParameter("txtContrasenia");
		Usuarios usr = new Usuarios (DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, true);	
		return usr;
	}
	
	private void cargarUsuarios(HttpServletRequest request) {
		request.setAttribute("listaUser", usrNeg.readALL());
	}

}
