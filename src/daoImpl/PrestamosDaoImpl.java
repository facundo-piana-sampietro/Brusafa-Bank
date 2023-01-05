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
import dao.PrestamosDao;
import entidades.Cuentas;
import entidades.Localidades;
import entidades.Prestamos;
import entidades.Provincias;
import entidades.TipoCuentas;
import entidades.Usuarios;

public class PrestamosDaoImpl implements PrestamosDao
{
	@Override
	public boolean agregarPrestamo(Prestamos prestamo) {
		Connection cn = null;
		try {
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = cn.prepareCall("CALL SPAgregarPrestamo(?, ?, ?, ?, ?, ?)");
			System.out.println("prestamo: " + prestamo);
			
			cst.setString(1, prestamo.getUsuario_Pr().getDNI_Usr());
			cst.setInt(2, prestamo.getCuentaDestino_Pr().getNro_Cuentas() );
			cst.setFloat(3, prestamo.getImpSolicitado_Pr());
			cst.setFloat(4, prestamo.getImpResultante_Pr());
			cst.setInt(5, prestamo.getPlazoMeses_Pr());
			cst.setFloat(6,prestamo.getImpPagoAlMes_Pr());

			
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
	public ArrayList<Prestamos> obtenerTodos() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) where Autorizado_Pr = 0 and Estado_Pr = 1"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
			prestamos.forEach(System.out::println);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public ArrayList<Prestamos> prestXUsuario(Usuarios us) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas " + 
		"	on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"		on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"			on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"				on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"					on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) " + 
		"where usuarios.DNI_Usr = '" + us.getDNI_Usr() + "' and Estado_Pr = 1;"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public ArrayList<Prestamos> prestXFecha(Date fecha1, Date fecha2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas " + 
		"	on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"		on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"			on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"				on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"					on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) " + 
		"where prestamos.FechaSolicitado_Pr between '" + fecha1.toString() + "' and '" + fecha2.toString() + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
			prestamos.forEach(System.out::println);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public ArrayList<Prestamos> prestXImportePagar(float importe1, float importe2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas " + 
		"	on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"		on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"			on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"				on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"					on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) " + 
		"where prestamos.ImpResultante_Pr > '" + importe1 + "' and prestamos.ImpResultante_Pr < '" + importe2 + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
			prestamos.forEach(System.out::println);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public ArrayList<Prestamos> prestXImportePedido(float importe1, float importe2) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas " + 
		"	on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"		on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"			on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"				on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"					on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) " + 
		"where prestamos.ImpSolicitado_Pr > '" + importe1 + "' and prestamos.ImpSolicitado_Pr < '" + importe2 + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
			prestamos.forEach(System.out::println);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}

	@Override
	public ArrayList<Prestamos> prestXCantidadCuotas(int cuotas) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Prestamos> prestamos= new ArrayList<Prestamos>();
		Conexion conexion = Conexion.getConexion();
		String consulta = 
		"Select * from prestamos inner join cuentas " + 
		"	on (prestamos.NroCuentaDestino_Pr = cuentas.Nro_Cuentas) inner join tipocuentas " + 
		"		on (cuentas.IdTipoCuenta_Cuentas = tipocuentas.Id_TipoCuenta) inner join usuarios " + 
		"			on (cuentas.DNI_Cuentas = usuarios.DNI_Usr) inner join localidades " + 
		"				on (usuarios.IdLocalidad_Usr = localidades.IdLocalidad_Loc and usuarios.IdProvincia_Usr = localidades.IdProvincia_Loc) inner join provincias " + 
		"					on (localidades.IdProvincia_Loc = provincias.IdProvincia_Prov) " + 
		"where prestamos.CantCuotas_Pr = '" + cuotas + "';"; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prestamos.add(getPrestamos(resultSet));
			}
			prestamos.forEach(System.out::println);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestamos;
	}
	
	private Prestamos getPrestamos(ResultSet resultSet) throws SQLException
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
		
		//Datos prestamo
		
		int Id_Pr = resultSet.getInt("Id_Pr");
		Date FechaSolicitado_Pr = resultSet.getDate("FechaSolicitado_Pr");
		float ImpSolicitado_Pr = resultSet.getFloat("ImpSolicitado_Pr");
		float ImpResultante_Pr = resultSet.getFloat("ImpResultante_Pr");
		int PlazoMeses_Pr = resultSet.getInt("PlazoMeses_Pr");
		float ImpPagoAlMes_Pr = resultSet.getFloat("ImpPagoAlMes_Pr");
		int CantCuotas_Pr = resultSet.getInt("CantCuotas_Pr");
		boolean Autorizado_Pr = resultSet.getBoolean("Autorizado_Pr");
		boolean Estado_Pr = resultSet.getBoolean("Estado_Pr");
		
		
		return new Prestamos(
					Id_Pr,
					new Usuarios(
						DNI_Usr,
						CUIL_Usr,
						Nombre_Usr,
						Apellido_Usr,
						Sexo_Usr,
						Nacionalidad_Usr,
						FechaNacimiento_Usr,
						Direccion_Usr,
						Provincia_Usr,
						Localidad_Usr,
						Email_Usr,
						Telefono_Usr,
						Tipo_Usr,
						Usuario_Usr,
						Contrasenia_Usr,
						Estado_Usr 
					),
					new Cuentas(
						nro_Cuentas,
						new Usuarios(DNI_Usr, CUIL_Usr,Nombre_Usr,  Apellido_Usr, Sexo_Usr, Nacionalidad_Usr, FechaNacimiento_Usr, Direccion_Usr,Provincia_Usr, Localidad_Usr ,Email_Usr,Telefono_Usr,  Tipo_Usr, Usuario_Usr,  Contrasenia_Usr, Estado_Usr),
						fechaCreacion_Cuentas,
						new TipoCuentas(Id_TipoCuenta,Descripcion_TipoCuenta),
						cBU_Cuentas,
						saldo_Cuentas,
						estado_Cuentas
					),
					FechaSolicitado_Pr,
					ImpSolicitado_Pr,
					ImpResultante_Pr,
					PlazoMeses_Pr,
					ImpPagoAlMes_Pr,
					CantCuotas_Pr,
					Autorizado_Pr,
					Estado_Pr
				);
	}
	
	@Override
	public boolean modificarAutorizado(Prestamos prestamo, boolean aprobado)
	{
		boolean res=false;
		Connection cn = null;
		try 
		{
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement st = cn.prepareCall("CALL SPActualizarPrestamos(?,?,?,?,?,?,?,?,?)");
			st.setInt(1, prestamo.getId_Pr());
			st.setString(2, prestamo.getCuentaDestino_Pr().getUsuario_Cuentas().getDNI_Usr());
			st.setInt(3, prestamo.getCuentaDestino_Pr().getNro_Cuentas());
			st.setFloat(4, prestamo.getImpSolicitado_Pr());
			st.setFloat(5, prestamo.getImpResultante_Pr());
			st.setInt(6, prestamo.getPlazoMeses_Pr());
			st.setFloat(7, prestamo.getImpPagoAlMes_Pr());
			st.setInt(8, prestamo.getCantCuotas_Pr());
			st.setBoolean(9,aprobado);
			if (st.executeUpdate()>0) 
				res=true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
		}
		return res;
	}

	@Override
	public boolean eliminarPrestamo(Prestamos prestamo) {
		boolean r=false;
		Connection cn = null;
		try 
		{
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement st = cn.prepareCall("CALL SPEliminarPrestamos(?)");
			st.setInt(1, prestamo.getId_Pr());
			if (st.executeUpdate()>0) 
				r=true;
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
		}
		return r;
	}
	
	@Override
	public boolean modificarCuotas(Prestamos prestamo)
	{
		boolean res=false;
		Connection cn = null;
		try 
		{
			cn = Conexion.getConexion().getSQLConexion();
			CallableStatement st = cn.prepareCall("CALL SPActualizarCuotasPrestamos(?)");
			st.setInt(1, prestamo.getId_Pr());
			if (st.executeUpdate()>0) 
				res=true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}

	
}
