package entidades;

public class TipoCuentas {
	private int Id_TipoCuenta;
    private String Descripcion_TipoCuenta;
    public TipoCuentas(){}

    //Constructores
    public TipoCuentas(int id_TipoCuenta, String descripcion_TipoCuenta) {
		super();
		Id_TipoCuenta = id_TipoCuenta;
		Descripcion_TipoCuenta = descripcion_TipoCuenta;
	}

    //Gets y Sets
	public int getId_TipoCuenta() {
		return Id_TipoCuenta;
	}

	public void setId_TipoCuenta(int id_TipoCuenta) {
		Id_TipoCuenta = id_TipoCuenta;
	}

	public String getDescripcion_TipoCuenta() {
		return Descripcion_TipoCuenta;
	}

	public void setDescripcion_TipoCuenta(String descripcion_TipoCuenta) {
		Descripcion_TipoCuenta = descripcion_TipoCuenta;
	}

	//toString
	@Override
	public String toString() {
		return "TipoCuentas [Id_TipoCuenta=" + Id_TipoCuenta + ", Descripcion_TipoCuenta=" + Descripcion_TipoCuenta
				+ "]";
	}    
}
