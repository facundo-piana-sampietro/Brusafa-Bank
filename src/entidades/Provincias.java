package entidades;

public class Provincias {
	private int IdProvincia_Prov;
	private String Descripcion_Prov;
	
	//Constructores
	public Provincias() {}

	public Provincias(int idProvincia_Prov, String descripcion_Prov) {
		IdProvincia_Prov = idProvincia_Prov;
		Descripcion_Prov = descripcion_Prov;
	}
	
	//Gets y Sets
	public int getIdProvincia_Prov() {
		return IdProvincia_Prov;
	}

	public void setIdProvincia_Prov(int idProvincia_Prov) {
		IdProvincia_Prov = idProvincia_Prov;
	}

	public String getDescripcion_Prov() {
		return Descripcion_Prov;
	}

	public void setDescripcion_Prov(String descripcion_Prov) {
		Descripcion_Prov = descripcion_Prov;
	}
	
	

	//ToString
	@Override
	public String toString() {
		return "Provincias [IdProvincia_Prov=" + IdProvincia_Prov + ", Descripcion_Prov=" + Descripcion_Prov + "]";
	}
	
	
}
