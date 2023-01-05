package presentacion.controlador;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.UsuariosDaoImpl;
import entidades.Localidades;
import entidades.Provincias;
import entidades.Usuarios;
import negocio.LocalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.UsuariosNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.UsuariosNegocioImpl;

@WebServlet("/servletUsuarios")
public class servletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuariosNegocio usrNeg= new UsuariosNegocioImpl();
	private ProvinciasNegocio provNeg = new ProvinciasNegocioImpl();
	private LocalidadesNegocio locNeg = new LocalidadesNegocioImpl();

    public servletUsuarios() {
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		refreshDeGet(request);
		cargarUsuarios(request);
		RequestDispatcher rd = request.getRequestDispatcher("AdministrarUsuarios.jsp");
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		
		if (request.getParameter("btnIniciar")!=null) {
			logicaIniciarSesion(request, response);
		}
		
		if (request.getParameter("btnEditar") != null) {
			request.setAttribute("usrSeleccionado", obtenerUsrPorDNI(request.getParameter("hiddenDNI").toString()));
		}
		
		if (request.getParameter("btnModificar") != null) {
			request.setAttribute("resultadoEdit", editar(request));
		}
		

		if (request.getParameter("btnEliminarUsr")!=null) {
			eliminarUsr(request);
		}
		
		
		
		refreshDePost(request);
		rd = request.getRequestDispatcher("AdministrarUsuarios.jsp");
		rd.forward(request, response);
	}
	
	private boolean editar(HttpServletRequest request) {
		return usrNeg.edit(asignarInputsAEntidad(request));
	}

	private void eliminarUsr(HttpServletRequest request) {
		String DNI= request.getParameter("hiddenDNI");
		boolean mensajeDeleteUsr=usrNeg.delete(DNI);
		request.setAttribute("mensajeDeleteUsr", mensajeDeleteUsr);

	}

	private void refreshDeGet(HttpServletRequest request) {
		cargarProvinciasUser(request);
		cargarLocalidadesUser(request);
	}
	
	private void refreshDePost(HttpServletRequest request) {
		cargarProvinciasUser(request);
		cargarLocalidadesUser(request);
		cargarUsuarios(request);
	}
	
	private Usuarios obtenerUsrPorDNI(String dni) {
		for(Usuarios usr : usrNeg.readALL()) {
			if(usr.getDNI_Usr().equals(dni))
				return usr;
		}
		
		return null;
	}
	
	private void logicaIniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Primero verificamos que el usuario exista mediante el iniciar sesion. Si devuelve true, nos fijamos que tipo es para devolver al JSP correcto
		RequestDispatcher rd;
		if (iniciarSesion(request)) {
			Usuarios user = (Usuarios)request.getSession().getAttribute("sessionUser");
			
			if (user.isTipo_Usr()==false) {
				rd = request.getRequestDispatcher("Inicio.jsp");
			}
			else {
				rd = request.getRequestDispatcher("MiCuenta.jsp");
			}
			rd.forward(request, response);			
		}
		//Si la funcion devuelve false, el usuario no existe!
		else {
			request.setAttribute("noExiste", true);
			rd = request.getRequestDispatcher("IniciarSesion.jsp");
			rd.forward(request, response);
		}
	}
	
	private void cargarProvinciasUser(HttpServletRequest request) {		
		request.getSession().setAttribute("provincias", provNeg.readALL());
	}
	
	private void cargarLocalidadesUser(HttpServletRequest request) {
		request.getSession().setAttribute("localidades", locNeg.GetAllLocalidadesPorProv(-1));
	}
	
	private boolean iniciarSesion(HttpServletRequest request) {
		String user = request.getParameter("txtUsuario");
		String contra = request.getParameter("txtContraseña");
		boolean existe= usrNeg.existeUsuario(user, contra);
		if (existe) {
			Usuarios usuario= usrNeg.readOne(user);
			HttpSession sessionUser = request.getSession();
			sessionUser.setAttribute("sessionUser", usuario);
			return true;
		}
		else {
			return false;
		}
	}
	
	private void cargarUsuarios(HttpServletRequest request) {
		request.setAttribute("listaUser", usrNeg.readALL());
	}
	
	private Usuarios asignarInputsAEntidad(HttpServletRequest request) {
		String DNI_Usr = request.getParameter("txtDNIEdit");
		String CUIL_Usr = request.getParameter("txtCUILEdit");
		String Nombre_Usr = request.getParameter("txtNombreEdit");
		String Apellido_Usr = request.getParameter("txtApellidoEdit");
		String Sexo_Usr = request.getParameter("rbSexoEdit");
		String Nacionalidad_Usr  = request.getParameter("txtNacionalidadEdit");
		LocalDate FechaNacimiento_Usr = LocalDate.parse(request.getParameter("FechaEdit"));
		String Direccion_Usr= request.getParameter("txtDireccionEdit");
		Provincias Provincia_Usr = new Provincias(Integer.parseInt(request.getParameter("ddlProvinciasEdit")),null);  
		Localidades Localidad_Usr = new Localidades(Provincia_Usr, Integer.parseInt(request.getParameter("ddlLocalidadesEdit")) ,null);
		String Email_Usr = request.getParameter("txtEmailEdit");
		String Telefono_Usr = request.getParameter("txtTelefonoEdit");
		boolean Tipo_Usr = Boolean.parseBoolean(request.getParameter("ddlTipoEdit"));
		String Usuario_Usr = request.getParameter("txtUsuarioEdit");
		String Contrasenia_Usr = request.getParameter("txtContraseniaEdit");
		Usuarios usr = new Usuarios (DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, true);	
		System.out.println(usr);
		return usr;
	}
}
