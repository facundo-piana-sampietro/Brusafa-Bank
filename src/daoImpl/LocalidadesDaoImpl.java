package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import dao.LocalidadesDao;
import entidades.Localidades;
import entidades.Provincias;

public class LocalidadesDaoImpl implements LocalidadesDao {
	@Override
	public ArrayList<Localidades> GetAllLocalidadesPorProv(int idProv) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Localidades> localidades= new ArrayList<Localidades>();
		Conexion conexion = Conexion.getConexion();
		String consulta =  
		"SELECT Localidades.* ,Provincias.Descripcion_Prov from Localidades INNER JOIN Provincias ON IdProvincia_Loc = IdProvincia_Prov ";
		if(idProv != -1)
			consulta += " WHERE idprovincia_loc = " + idProv; 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
				localidades.add(getLocalidades(resultSet));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return localidades;
	}	
	@Override
	public int buscarNumLoc(String descripcion) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		int numLoc=0;
		Conexion conexion = Conexion.getConexion();
		System.out.println(descripcion);
		String consulta = 
		"SELECT IdLocalidad_Loc FROM Localidades WHERE Descripcion_Loc = '" + descripcion + "'";
		System.out.println(consulta);
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			resultSet.next();
			numLoc = resultSet.getInt("IdLocalidad_Loc");
		} 
		catch (SQLException e) 
		{
			System.out.println("Numero de localidad vacío");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(numLoc);
		return numLoc;
	}

	private Localidades getLocalidades(ResultSet resultSet) throws SQLException
	{
		
		int IdLocalidades_Loc = resultSet.getInt("IdLocalidad_Loc");
		int IdProvincia_Prov = resultSet.getInt("IdProvincia_Loc");
		String Descripcion_Prov= resultSet.getString("Descripcion_Prov");
		String Descripcion_Loc= resultSet.getString("Descripcion_Loc");
		
		return new Localidades(new Provincias (IdProvincia_Prov, Descripcion_Prov), IdLocalidades_Loc, Descripcion_Loc);
	}

		
}
