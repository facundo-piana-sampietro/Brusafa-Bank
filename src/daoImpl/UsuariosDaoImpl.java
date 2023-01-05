package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import dao.UsuariosDao;
import entidades.Localidades;
import entidades.Provincias;
import entidades.Usuarios;

public class UsuariosDaoImpl implements UsuariosDao{

	public Boolean insert(Usuarios usuario) {	
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = cn.prepareCall("CALL SPAgregarUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("daoimpl: " + usuario);
			
			cst.setString(1, usuario.getDNI_Usr());
			cst.setString(2, usuario.getCUIL_Usr());
			cst.setString(3, usuario.getNombre_Usr());
			cst.setString(4, usuario.getApellido_Usr());
			cst.setString(5, usuario.getSexo_Usr());
			cst.setString(6,usuario.getNacionalidad_Usr());
			cst.setObject(7, usuario.getFechaNacimiento_Usr()); 
			cst.setString(8,usuario.getDireccion_Usr());
			cst.setInt(9,usuario.getProvincia_Usr().getIdProvincia_Prov());
			cst.setInt(10, usuario.getLocalidad_Usr().getIdLocalidad_Loc());
			cst.setString(11,usuario.getEmail_Usr());
			cst.setString(12, usuario.getTelefono_Usr());
			cst.setBoolean(13, usuario.isTipo_Usr());
			cst.setString(14,usuario.getUsuario_Usr());
			cst.setString(15, usuario.getContrasenia_Usr());
			
			int i = cst.executeUpdate();
			if(i > 0) 
				return true;
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	@Override
	public ArrayList<Usuarios> readALL() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
			"SELECT *, Descripcion_Prov, Descripcion_Loc "
			+ "FROM Usuarios INNER JOIN Provincias ON IdProvincia_Prov = IdProvincia_Usr "
			+ "INNER JOIN Localidades ON IdLocalidad_Usr = IdLocalidad_Loc";
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuarios.add(getUsuario(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuarios;
	}	
	
	@Override
	public Usuarios readOne(String nombreUsuario) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Usuarios usuario=new Usuarios();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT *,Descripcion_Prov, Descripcion_Loc FROM Usuarios INNER JOIN Provincias ON IdProvincia_Prov = IdProvincia_Usr INNER JOIN Localidades ON IdLocalidad_Usr = IdLocalidad_Loc WHERE Usuario_Usr = '" +nombreUsuario+ "'");
			resultSet = statement.executeQuery();
			resultSet.next();
			usuario=getUsuario(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		
		return usuario;
	}
	
	private Usuarios getUsuario(ResultSet resultSet) throws SQLException
	{
		String DNI_Usr = resultSet.getString("DNI_Usr");
		String CUIL_Usr = resultSet.getString("CUIL_Usr");
		String Nombre_Usr = resultSet.getString("Nombre_Usr");
		String Apellido_Usr = resultSet.getString("Apellido_Usr");
		String Sexo_Usr = resultSet.getString("Sexo_Usr");
		String Nacionalidad_Usr  = resultSet.getString("Nacionalidad_Usr");
		LocalDate FechaNacimiento_Usr = resultSet.getDate("FechaNacimiento_Usr").toLocalDate();
		String Direccion_Usr= resultSet.getString("Direccion_Usr");
		Provincias Provincia_Usr = new Provincias(resultSet.getInt("IdProvincia_Usr"), resultSet.getString("Descripcion_Prov"));
		Localidades Localidad_Usr = new Localidades(Provincia_Usr, resultSet.getInt("IdLocalidad_Usr"), resultSet.getString("Descripcion_Loc"));
		String Email_Usr = resultSet.getString("Email_Usr");
		String Telefono_Usr = resultSet.getString("Telefono_Usr");
		boolean Tipo_Usr = resultSet.getBoolean("Tipo_Usr");
		String Usuario_Usr = resultSet.getString("Usuario_Usr");
		String Contrasenia_Usr = resultSet.getString("Contrasenia_Usr");
		boolean Estado_Usr = resultSet.getBoolean("Estado_Usr");
		return new Usuarios(DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, Estado_Usr);
	}

	@Override
	public boolean existeUsuario(String user, String contra) {
		String query = "SELECT * FROM Usuarios WHERE Usuario_usr = BINARY '" +user+ "' AND Contrasenia_Usr = BINARY '" +contra+ "' AND Estado_Usr = 1" ;
		if (existe(query)) return true;
		return false;
	}

	
	public static Boolean existe(String consulta)
    {
        PreparedStatement statement;
		ResultSet resultSet;
		boolean existe=false;
		try 
		{
			statement = Conexion.getConexion().getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			resultSet.next();
			String user = resultSet.getString("Usuario_Usr");
			if (!user.isEmpty())existe= true;
		} 
		catch (SQLException e) 
		{
			existe=false;
		}
		return existe;
    }


	@Override
	public boolean delete(String dni) {
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = cn.prepareCall("CALL SPEliminarUsuario(?)");
			cst.setString(1, dni);
			
			
			if(cst.executeUpdate() > 0) 
				return true;
			return false;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean edit(Usuarios usuario) {
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = cn.prepareCall("CALL SPActualizarUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			cst.setString(1, usuario.getDNI_Usr());
			cst.setString(2, usuario.getNombre_Usr());
			cst.setString(3, usuario.getApellido_Usr());
			cst.setString(4, usuario.getSexo_Usr());
			cst.setString(5,usuario.getNacionalidad_Usr());
			cst.setObject(6, usuario.getFechaNacimiento_Usr()); 
			cst.setString(7,usuario.getDireccion_Usr());
			cst.setInt(8,usuario.getProvincia_Usr().getIdProvincia_Prov());
			cst.setInt(9, usuario.getLocalidad_Usr().getIdLocalidad_Loc());
			cst.setString(10,usuario.getEmail_Usr());
			cst.setString(11, usuario.getTelefono_Usr());
			cst.setBoolean(12, usuario.isTipo_Usr());
			cst.setString(13, usuario.getContrasenia_Usr());
			
			int i = cst.executeUpdate();
			System.out.println("resultado: " + i);
			if(i > 0) 
				return true;
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
