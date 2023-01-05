package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


import conexion.Conexion;
import dao.CuentasDao;
import dao.MovimientosDao;
import entidades.Cuentas;
import entidades.Localidades;
import entidades.Movimientos;
import entidades.Provincias;
import entidades.TipoCuentas;
import entidades.TipoMovimientos;
import entidades.Usuarios;

public class MovimientosDaoImpl implements MovimientosDao
{
	private CuentasDao cd = new CuentasDaoImpl();

	@Override
	public ArrayList<Movimientos> obtenerTodos() 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Movimientos> movimientos= new ArrayList<Movimientos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from movimientos inner join tiposmovimientos " + 
		"	on (movimientos.IdTiposMov_Mov = tiposmovimientos.Id_TiposMov) inner join cuentas " + 
		"		on (movimientos.NroCuenta_Mov = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"			on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join Usuarios " + 
		"				on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"					on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"						on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				movimientos.add(getMovimientos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public ArrayList<Movimientos> MovxUsuario(Usuarios usuario) //Por nombre y apellido de usuario
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Movimientos> movimientos= new ArrayList<Movimientos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from movimientos inner join tiposmovimientos " + 
		"	on (movimientos.IdTiposMov_Mov = tiposmovimientos.Id_TiposMov) inner join cuentas " + 
		"		on (movimientos.NroCuenta_Mov = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"			on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join Usuarios " + 
		"				on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"					on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"						on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)"  + 
		"where usuarios.DNI_Usr = '" + usuario.getDNI_Usr() + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				movimientos.add(getMovimientos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public ArrayList<Movimientos> MovxTipo(TipoMovimientos tipo)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Movimientos> movimientos= new ArrayList<Movimientos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"" + 
		"where tiposmovimientos = '" + tipo.getDescripcion_TiposMov() + "'"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				movimientos.add(getMovimientos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public ArrayList<Movimientos> MovxFecha(String fecha1, String fecha2) 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Movimientos> movimientos= new ArrayList<Movimientos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from movimientos inner join tiposmovimientos " + 
		"	on (movimientos.IdTiposMov_Mov = tiposmovimientos.Id_TiposMov) inner join cuentas " + 
		"		on (movimientos.NroCuenta_Mov = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"			on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"				on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"					on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"						on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
		"where movimientos.Fecha_Mov between '" + fecha1 + "' and '" + fecha2 + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				movimientos.add(getMovimientos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimientos;
	}
	
	@Override
	public ArrayList<Movimientos> MovxCuenta(int nroCuenta) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Movimientos> movimientos= new ArrayList<Movimientos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from movimientos inner join tiposmovimientos " + 
		"	on (movimientos.IdTiposMov_Mov = tiposmovimientos.Id_TiposMov) inner join cuentas " + 
		"		on (movimientos.NroCuenta_Mov = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"			on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"				on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"					on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"						on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov)" + 
		"where movimientos.NroCuenta_Mov = " + nroCuenta + " OR movimientos.NroCuentaDestino_Mov = " +nroCuenta + ";"; 

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				movimientos.add(getMovimientos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movimientos;
	}
	
	
	
	private Movimientos getMovimientos(ResultSet resultSet) throws SQLException
	{
		
		//Datos cuenta del movimiento
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
		
		//Datos tipo de movimiento
		
		int Id_TiposMov = resultSet.getInt("Id_TiposMov");
		String Descripcion_TiposMov = resultSet.getString("Descripcion_TiposMov");
		
		//Datos movimiento
		
		int Id_Mov = resultSet.getInt("Id_Mov");
		Timestamp Fecha_Mov = resultSet.getTimestamp("Fecha_Mov");
		String Detalle_Mov = resultSet.getString("Detalle_Mov");
		Cuentas CuentaDestino = cd.cuentaxNro(resultSet.getInt("NroCuentaDestino_Mov"));
		float Importe_Mov = resultSet.getFloat("Importe_Mov");
		boolean Estado_Mov = resultSet.getBoolean("Estado_Mov");
		
		return new Movimientos(
				Id_Mov,
				new Cuentas(
						nro_Cuentas,
						new Usuarios(DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, Estado_Usr),
						fechaCreacion_Cuentas,
						new TipoCuentas(Id_TipoCuenta,Descripcion_TipoCuenta),
						cBU_Cuentas,
						saldo_Cuentas,
						estado_Cuentas
						),
				new TipoMovimientos(Id_TiposMov,Descripcion_TiposMov),
				Fecha_Mov,	
				Detalle_Mov,
				CuentaDestino,
				Importe_Mov,
				Estado_Mov
				);
	}

	@Override
	public boolean insert(Movimientos movimiento) {
		boolean r=false;
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement st = cn.prepareCall("CALL SPAgregarMovimiento(?,?,?,?,?)");
			st.setInt(1,movimiento.getCuenta_Mov().getNro_Cuentas());
			st.setInt(2, movimiento.getTiposMov_Mov().getId_TiposMov());
			st.setString(3, movimiento.getDetalle_Mov());
			if(movimiento.getCuentaDestino_Mov() == null) 
				st.setNull(4, Types.INTEGER);
			else
				st.setInt(4,movimiento.getCuentaDestino_Mov().getNro_Cuentas());	
			st.setFloat(5, movimiento.getImporte_Mov());
			if (st.executeUpdate()>0) r=true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		  finally {
			
		}
		System.out.println("resultado: " + r);
		return r;
	}

	
	
}
