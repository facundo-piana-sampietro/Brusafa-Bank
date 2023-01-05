package entidades;

import java.time.LocalDateTime;


public class Cuentas {
	private int Nro_Cuentas;
	private Usuarios Usuario_Cuentas;
	private LocalDateTime FechaCreacion_Cuentas;
	private TipoCuentas TipoCuenta_Cuentas;
	private String CBU_Cuentas;
	private float Saldo_Cuentas;
	private boolean Estado_Cuentas;
	
	//Constructores
	public Cuentas() {
		TipoCuenta_Cuentas = new TipoCuentas();
		Usuario_Cuentas = new Usuarios();
	}
	
	public Cuentas(int nro_Cuentas, Usuarios Usuario_Cuentas, LocalDateTime fechaCreacion_Cuentas,
	TipoCuentas tipoCuenta_Cuentas, String cBU_Cuentas, float saldo_Cuentas, boolean estado_Cuentas) {
		Nro_Cuentas = nro_Cuentas;
		this.Usuario_Cuentas = Usuario_Cuentas;
		FechaCreacion_Cuentas = fechaCreacion_Cuentas;
		TipoCuenta_Cuentas = tipoCuenta_Cuentas;
		CBU_Cuentas = cBU_Cuentas;
		Saldo_Cuentas = saldo_Cuentas;
		Estado_Cuentas = estado_Cuentas;
	}
	
	//Gets y Sets
	public int getNro_Cuentas() {
		return Nro_Cuentas;
	}

	public void setNro_Cuentas(int nro_Cuentas) {
		Nro_Cuentas = nro_Cuentas;
	}

	public Usuarios getUsuario_Cuentas() {
		return Usuario_Cuentas;
	}

	public void setUsuario_Cuentas(Usuarios Usuario_Cuentas) {
		this.Usuario_Cuentas = Usuario_Cuentas;
	}

	public LocalDateTime getFechaCreacion_Cuentas() {
		return FechaCreacion_Cuentas;
	}

	public void setFechaCreacion_Cuentas(LocalDateTime fechaCreacion_Cuentas) {
		FechaCreacion_Cuentas = fechaCreacion_Cuentas;
	}

	public TipoCuentas getTipoCuenta_Cuentas() {
		return TipoCuenta_Cuentas;
	}

	public void setTipoCuenta_Cuentas(TipoCuentas tipoCuenta_Cuentas) {
		TipoCuenta_Cuentas = tipoCuenta_Cuentas;
	}

	public String getCBU_Cuentas() {
		return CBU_Cuentas;
	}

	public void setCBU_Cuentas(String cBU_Cuentas) {
		CBU_Cuentas = cBU_Cuentas;
	}

	public float getSaldo_Cuentas() {
		return Saldo_Cuentas;
	}

	public void setSaldo_Cuentas(float saldo_Cuentas) {
		Saldo_Cuentas = saldo_Cuentas;
	}

	public boolean isEstado_Cuentas() {
		return Estado_Cuentas;
	}

	public void setEstado_Cuentas(boolean estado_Cuentas) {
		Estado_Cuentas = estado_Cuentas;
	}
	
	//toString
	@Override
	public String toString() {
		return "Cuentas [Nro_Cuentas=" + Nro_Cuentas + ", DNI_Cuentas=" + Usuario_Cuentas.getDNI_Usr()  + ", FechaCreacion_Cuentas="
				+ FechaCreacion_Cuentas + ", TipoCuenta_Cuentas=" + TipoCuenta_Cuentas + ", CBU_Cuentas=" + CBU_Cuentas
				+ ", Saldo_Cuentas=" + Saldo_Cuentas + ", Estado_Cuentas=" + Estado_Cuentas + "]";
	}
	
	public static Boolean verificarImporte(float importe) throws exceptions.ImporteNegativo {
		exceptions.ImporteNegativo exc1 = new exceptions.ImporteNegativo();
		
        if (importe < 0.0f) {
        	throw exc1;
        }
     
        return true;
	}
	
	
	
	
}
