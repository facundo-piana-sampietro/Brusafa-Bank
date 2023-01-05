package entidades;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class Localidades {
	private Provincias Provincia_Loc;
	private int IdLocalidad_Loc;
	private String Descripcion_Loc;
	
	//Constructores
	public Localidades() {
		Provincia_Loc= new Provincias();
	}
	
	public Localidades(Provincias Provincia_Loc, int IdLocalidad_Loc, String Descripcion_Loc) {
		this.Provincia_Loc = Provincia_Loc;
		this.IdLocalidad_Loc = IdLocalidad_Loc;
		this.Descripcion_Loc = Descripcion_Loc;
	}
	
	public Provincias getProvincia() {
		return this.Provincia_Loc;
	}
	
	//Gets y Sets
	public Provincias getProvincia_Loc() {
		return Provincia_Loc;
	}

	public void setProvincia_Loc(Provincias provincia_Loc) {
		Provincia_Loc = provincia_Loc;
	}

	public int getIdLocalidad_Loc() {
		return IdLocalidad_Loc;
	}

	public void setIdLocalidad_Loc(int idLocalidad_Loc) {
		IdLocalidad_Loc = idLocalidad_Loc;
	}

	public String getDescripcion_Loc() {
		return Descripcion_Loc;
	}

	public void setDescripcion_Loc(String descripcion_Loc) {
		Descripcion_Loc = descripcion_Loc;
	}
	
	//toString
	@Override
	public String toString() {
		return "Localidades [Provincia_Loc=" + Provincia_Loc + ", IdLocalidad_Loc=" + IdLocalidad_Loc
				+ ", Descripcion_Loc=" + Descripcion_Loc + "]";
	}
	
	

	
	
	
}



