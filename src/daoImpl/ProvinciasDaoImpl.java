package daoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import dao.ProvinciasDao;
import entidades.Localidades;
import entidades.Provincias;
import entidades.Usuarios;

public class ProvinciasDaoImpl implements ProvinciasDao{

	@Override
	public ArrayList<Provincias> readALL() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Provincias> provincias = new ArrayList<Provincias>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			statement = conexion.getSQLConexion().prepareStatement("SELECT * from Provincias");
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				provincias.add(getProvincia(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provincias;
	}	

	private Provincias getProvincia(ResultSet resultSet) throws SQLException
	{
		
		int IdProvincia_Prov = resultSet.getInt("IdProvincia_Prov");
		String Descripcion_Prov = resultSet.getString("Descripcion_Prov");
		
		return new Provincias(IdProvincia_Prov, Descripcion_Prov);
	}
		

}
