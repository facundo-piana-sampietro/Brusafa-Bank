package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import conexion.Conexion;
import dao.CuentasDao;
import entidades.Cuentas;
import entidades.Localidades;
import entidades.Movimientos;
import entidades.Provincias;
import entidades.TipoCuentas;
import entidades.TipoMovimientos;
import entidades.Usuarios;

public class CuentasDaoImpl implements CuentasDao
{

	@Override
	public ArrayList<Cuentas> obtenerTodos() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Cuentas> cuentas= new ArrayList<Cuentas>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from cuentas inner join tipocuentas " + 
		"	on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"		on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"			on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"				on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cuentas.add(getCuentas(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public ArrayList<Cuentas> cuentasXPropietario(String DNI) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Cuentas> cuentas= new ArrayList<Cuentas>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from cuentas inner join tipocuentas " + 
		"	on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"		on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"			on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"				on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
		"where  usuarios.DNI_Usr = '" + DNI + "'"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cuentas.add(getCuentas(resultSet));
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public ArrayList<Cuentas> cuentasXFechaCreacion(Date fecha1, Date fecha2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Cuentas> cuentas= new ArrayList<Cuentas>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from cuentas inner join tipocuentas " + 
		"	on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"		on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"			on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"				on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
		"where  cuentas.FechaCreacion_Cuentas between '" + fecha1.toString() + "' and '" + fecha1.toString() + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cuentas.add(getCuentas(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuentas;
	}

	
	private Cuentas getCuentas(ResultSet resultSet) throws SQLException
	{
		
		//Datos cuenta
		int nro_Cuentas = resultSet.getInt("Nro_Cuentas");
		LocalDateTime fechaCreacion_Cuentas = resultSet.getTimestamp("FechaCreacion_Cuentas").toLocalDateTime();
		String cBU_Cuentas = resultSet.getString("CBU_Cuentas"); 
		float saldo_Cuentas = resultSet.getFloat("Saldo_Cuentas");
		boolean estado_Cuentas = resultSet.getBoolean("Estado_Cuentas");
		
		//Datos tipo de cuenta
		int Id_TipoCuenta = resultSet.getInt("Id_TipoCuenta");
		String Descripcion_TipoCuenta = resultSet.getString("Descripcion_TipoCuenta");
		
		//Datos usuario de la cuenta
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
		
		return new Cuentas(
				nro_Cuentas,
				new Usuarios(DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, Estado_Usr),
				fechaCreacion_Cuentas,
				new TipoCuentas(Id_TipoCuenta,Descripcion_TipoCuenta),
				cBU_Cuentas,
				saldo_Cuentas,
				estado_Cuentas
				);
	}

	@Override
	public ArrayList<Cuentas> cuentasXTipo(TipoCuentas tipoCuentas) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Cuentas> cuentas = new ArrayList<Cuentas>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from cuentas inner join tipocuentas " + 
		"	on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"		on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"			on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"				on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
		"where  tipocuentas.Id_TipoCuenta = '" + tipoCuentas.getId_TipoCuenta()  + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cuentas.add(getCuentas(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public boolean insert(Cuentas cuenta) {
			System.out.println("cuenta en dao: " + cuenta);
			boolean r=false;
			Connection cn = null;
			try {
				cn = Conexion.getConexion().getSQLConexion();
				CallableStatement st = cn.prepareCall("CALL SPAgregarCuentas(?,?,?)");
				st.setString(1,cuenta.getUsuario_Cuentas().getDNI_Usr());
				st.setInt(2,cuenta.getTipoCuenta_Cuentas().getId_TipoCuenta());
				st.setString(3,cuenta.getCBU_Cuentas());
				if (st.executeUpdate()>0) r=true;
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			  finally {
				
			}
			
			System.out.println("res de insert: " + r);
			return r;
		}
	
	@Override
	public boolean delete(int id) {
			boolean r=false;
			Connection cn = null;
			try {
				cn = Conexion.getConexion().getSQLConexion();
				CallableStatement st = cn.prepareCall("CALL SPEliminarCuentas(?)");
				st.setInt(1,id);
				if (st.executeUpdate()>0) r=true;
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			  finally {
				
			}
			return r;
		}

	@Override
	public boolean existeCuenta(String CBU) {
		String query = "SELECT * FROM Cuentas WHERE CBU_Cuentas= '" +CBU+ "' AND Estado_Cuentas = 1";
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
			String CBU = resultSet.getString("CBU_Cuentas");
			if (!CBU.isEmpty())existe= true;
		} 
		catch (SQLException e) 
		{
			existe=false;
		}
		return existe;
    }

	@Override
	public boolean modificate(Cuentas cuenta) {
		boolean r=false;
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement st = cn.prepareCall("CALL SPActualizarCuentas(?,?,?,?)");
			st.setInt(1,cuenta.getNro_Cuentas());
			st.setInt(2,cuenta.getTipoCuenta_Cuentas().getId_TipoCuenta());
			st.setString(3,cuenta.getCBU_Cuentas());
			st.setFloat(4, cuenta.getSaldo_Cuentas());
			if (st.executeUpdate()>0) r=true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		  finally {
			
		}
		return r;
	}

	@Override
	public Cuentas cuentaxNro(int nro) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Cuentas cuenta= new Cuentas();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
				"Select * from cuentas inner join tipocuentas " + 
				"	on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
				"		on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
				"			on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
				"				on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
				"where Nro_Cuentas = " + nro;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			resultSet.next();		
				cuenta=(getCuentas(resultSet));

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuenta;
	}
}

