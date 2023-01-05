package entidades;

public class TipoMovimientos {
	private int Id_TiposMov;
    private String Descripcion_TiposMov;
	
    //Constructores
    
    public TipoMovimientos() {}
    
    public TipoMovimientos(int id_TiposMov, String descripcion_TiposMov) {
		super();
		Id_TiposMov = id_TiposMov;
		Descripcion_TiposMov = descripcion_TiposMov;
	}
    
    //Gets y Sets
	public int getId_TiposMov() {
		return Id_TiposMov;
	}
	public void setId_TiposMov(int id_TiposMov) {
		Id_TiposMov = id_TiposMov;
	}
	public String getDescripcion_TiposMov() {
		return Descripcion_TiposMov;
	}
	public void setDescripcion_TiposMov(String descripcion_TiposMov) {
		Descripcion_TiposMov = descripcion_TiposMov;
	}
	
	//toString
	@Override
	public String toString() {
		return "TipoMovimientos [Id_TiposMov=" + Id_TiposMov + ", Descripcion_TiposMov=" + Descripcion_TiposMov + "]";
	}
   
}
