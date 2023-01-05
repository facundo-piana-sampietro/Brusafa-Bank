package entidades;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Usuarios {
	private String DNI_Usr;
	private String CUIL_Usr;
	private String Nombre_Usr; 
	private String Apellido_Usr; 
	private String Sexo_Usr; 
	private String Nacionalidad_Usr; 
	private LocalDate FechaNacimiento_Usr; 
	private String Direccion_Usr; 
	private Provincias Provincia_Usr;
	private Localidades Localidad_Usr; 
	private String Email_Usr;
	private String Telefono_Usr; 
	private boolean Tipo_Usr; 
	private String Usuario_Usr;
	private String Contrasenia_Usr;
	private boolean Estado_Usr;
	
	
	public Usuarios() {
		DNI_Usr = "-1";
		CUIL_Usr = "";
		Nombre_Usr = "";
		Apellido_Usr = "";
		Sexo_Usr = "";
		Nacionalidad_Usr = "";
		FechaNacimiento_Usr = null;
		Direccion_Usr = "";
		Provincia_Usr = new Provincias();
		Localidad_Usr = new Localidades();
		Email_Usr = "";
		Telefono_Usr = "";
		Tipo_Usr = false;
		Usuario_Usr = "";
		Contrasenia_Usr = "";
		Estado_Usr = false;
	}
	
	public Usuarios(String dNI_Usr) {
		DNI_Usr = dNI_Usr;
		CUIL_Usr = "";
		Nombre_Usr = "";
		Apellido_Usr = "";
		Sexo_Usr = "";
		Nacionalidad_Usr = "";
		FechaNacimiento_Usr = null;
		Direccion_Usr = "";
		Provincia_Usr = new Provincias();
		Localidad_Usr = new Localidades();
		Email_Usr = "";
		Telefono_Usr = "";
		Tipo_Usr = false;
		Usuario_Usr = "";
		Contrasenia_Usr = "";
		Estado_Usr = false;
	}
	
	public Usuarios(String dNI_Usr, String cUIL_Usr, String nombre_Usr, String apellido_Usr, String sexo_Usr,
	String nacionalidad_Usr, LocalDate fechaNacimiento_Usr, String direccion_Usr, Provincias provincia_Usr,
	Localidades localidad_Usr, String email_Usr, String telefono_Usr, boolean tipo_Usr, String usuario_Usr,
	String contrasenia_Usr, boolean estado_Usr) {
		
		DNI_Usr = dNI_Usr;
		CUIL_Usr = cUIL_Usr;
		Nombre_Usr = nombre_Usr;
		Apellido_Usr = apellido_Usr;
		Sexo_Usr = sexo_Usr;
		Nacionalidad_Usr = nacionalidad_Usr;
		FechaNacimiento_Usr = fechaNacimiento_Usr;
		Direccion_Usr = direccion_Usr;
		Provincia_Usr = provincia_Usr;
		Localidad_Usr = localidad_Usr;
		Email_Usr = email_Usr;
		Telefono_Usr = telefono_Usr;
		Tipo_Usr = tipo_Usr;
		Usuario_Usr = usuario_Usr;
		Contrasenia_Usr = contrasenia_Usr;
		Estado_Usr = estado_Usr;
	}
	
	//Gets y Sets
	public String getDNI_Usr() {
		return DNI_Usr;
	}

	public void setDNI_Usr(String dNI_Usr) {
		DNI_Usr = dNI_Usr;
	}

	public String getCUIL_Usr() {
		return CUIL_Usr;
	}

	public void setCUIL_Usr(String cUIL_Usr) {
		CUIL_Usr = cUIL_Usr;
	}

	public String getNombre_Usr() {
		return Nombre_Usr;
	}

	public void setNombre_Usr(String nombre_Usr) {
		Nombre_Usr = nombre_Usr;
	}

	public String getApellido_Usr() {
		return Apellido_Usr;
	}

	public void setApellido_Usr(String apellido_Usr) {
		Apellido_Usr = apellido_Usr;
	}

	public String getSexo_Usr() {
		return Sexo_Usr;
	}

	public void setSexo_Usr(String sexo_Usr) {
		Sexo_Usr = sexo_Usr;
	}

	public String getNacionalidad_Usr() {
		return Nacionalidad_Usr;
	}

	public void setNacionalidad_Usr(String nacionalidad_Usr) {
		Nacionalidad_Usr = nacionalidad_Usr;
	}


	public LocalDate getFechaNacimiento_Usr() {
		return FechaNacimiento_Usr;
	}

	public void setFechaNacimiento_Usr(LocalDate fechaNacimiento_Usr) {
		FechaNacimiento_Usr = fechaNacimiento_Usr;
	}

	public String getDireccion_Usr() {
		return Direccion_Usr;
	}

	public void setDireccion_Usr(String direccion_Usr) {
		Direccion_Usr = direccion_Usr;
	}

	public Provincias getProvincia_Usr() {
		return Provincia_Usr;
	}

	public void setProvincia_Usr(Provincias provincia_Usr) {
		Provincia_Usr = provincia_Usr;
	}

	public Localidades getLocalidad_Usr() {
		return Localidad_Usr;
	}

	public void setLocalidad_Usr(Localidades localidad_Usr) {
		Localidad_Usr = localidad_Usr;
	}

	public String getEmail_Usr() {
		return Email_Usr;
	}

	public void setEmail_Usr(String email_Usr) {
		Email_Usr = email_Usr;
	}

	public String getTelefono_Usr() {
		return Telefono_Usr;
	}

	public void setTelefono_Usr(String telefono_Usr) {
		Telefono_Usr = telefono_Usr;
	}

	public boolean isTipo_Usr() {
		return Tipo_Usr;
	}

	public void setTipo_Usr(boolean tipo_Usr) {
		Tipo_Usr = tipo_Usr;
	}

	public String getUsuario_Usr() {
		return Usuario_Usr;
	}

	public void setUsuario_Usr(String usuario_Usr) {
		Usuario_Usr = usuario_Usr;
	}

	public String getContrasenia_Usr() {
		return Contrasenia_Usr;
	}

	public void setContrasenia_Usr(String contrasenia_Usr) {
		Contrasenia_Usr = contrasenia_Usr;
	}

	public boolean isEstado_Usr() {
		return Estado_Usr;
	}

	public void setEstado_Usr(boolean estado_Usr) {
		Estado_Usr = estado_Usr;
	}
	
	//toString
	@Override
	public String toString() {
		return "Usuarios [DNI_Usr=" + DNI_Usr + ", CUIL_Usr=" + CUIL_Usr + ", Nombre_Usr=" + Nombre_Usr
				+ ", Apellido_Usr=" + Apellido_Usr + ", Sexo_Usr=" + Sexo_Usr + ", Nacionalidad_Usr=" + Nacionalidad_Usr
				+ ", FechaNacimiento_Usr=" + FechaNacimiento_Usr + ", Direccion_Usr=" + Direccion_Usr
				+ ", Provincia_Usr=" + Provincia_Usr + ", Localidad_Usr=" + Localidad_Usr + ", Email_Usr=" + Email_Usr
				+ ", Telefono_Usr=" + Telefono_Usr + ", Tipo_Usr=" + Tipo_Usr + ", Usuario_Usr=" + Usuario_Usr
				+ ", Contrasenia_Usr=" + Contrasenia_Usr + ", Estado_Usr=" + Estado_Usr + "]";
	}
	
	
	public static Boolean verificarDniInvalido(String DNI) throws exceptions.DniInvalido {
		exceptions.DniInvalido exc1 = new exceptions.DniInvalido();
		Pattern pat = Pattern.compile("^([0-9])*$");
        Matcher mat = pat.matcher(DNI);
        
        if (DNI.charAt(0)=='0') {
        	throw exc1;
        }
        
        if (!(mat.matches())) {
        	throw exc1;
        }
     
        return true;
	}
	
	
	
	
	
	
}
