package entidades;

import java.sql.Timestamp;

public class Movimientos {
	private int Id_Mov;
	private Cuentas Cuenta_Mov;
	private TipoMovimientos TiposMov_Mov;
	private Timestamp Fecha_Mov;
	private String Detalle_Mov;
	private Cuentas CuentaDestino_Mov;
	private float Importe_Mov;
	private boolean Estado_Mov;
	
	//Constructores
	public Movimientos() {
		Cuenta_Mov = new Cuentas();
		TiposMov_Mov = new TipoMovimientos();
		CuentaDestino_Mov = new Cuentas();
	}
	

	public Movimientos(int id_Mov, Cuentas cuenta_Mov, TipoMovimientos tiposMov_Mov, Timestamp fecha_Mov2, String detalle_Mov,
			Cuentas cuentaDestino_Mov, float importe_Mov, boolean estado_Mov) {
		Id_Mov = id_Mov;
		Cuenta_Mov = cuenta_Mov;
		TiposMov_Mov = tiposMov_Mov;
		Fecha_Mov = fecha_Mov2;
		Detalle_Mov = detalle_Mov;
		CuentaDestino_Mov = cuentaDestino_Mov;
		Importe_Mov = importe_Mov;
		Estado_Mov = estado_Mov;
	}
	
	public Movimientos(Cuentas cuenta_Mov, TipoMovimientos tiposMov_Mov, String detalle_Mov, Cuentas cuentaDestino_Mov, float importe_Mov) {
		Cuenta_Mov = cuenta_Mov;
		TiposMov_Mov = tiposMov_Mov;
		Detalle_Mov = detalle_Mov;
		CuentaDestino_Mov = cuentaDestino_Mov;
		Importe_Mov = importe_Mov;
	}
	
	public Movimientos(Cuentas cuenta_Mov, TipoMovimientos tiposMov_Mov, String detalle_Mov, float importe_Mov) {
		Cuenta_Mov = cuenta_Mov;
		TiposMov_Mov = tiposMov_Mov;
		Detalle_Mov = detalle_Mov;
		Importe_Mov = importe_Mov;
	}



	//Gets y Sets
	public int getId_Mov() {
		return Id_Mov;
	}

	public void setId_Mov(int id_Mov) {
		Id_Mov = id_Mov;
	}

	public Cuentas getCuenta_Mov() {
		return Cuenta_Mov;
	}

	public void setCuenta_Mov(Cuentas cuenta_Mov) {
		Cuenta_Mov = cuenta_Mov;
	}

	public TipoMovimientos getTiposMov_Mov() {
		return TiposMov_Mov;
	}

	public void setTiposMov_Mov(TipoMovimientos tiposMov_Mov) {
		TiposMov_Mov = tiposMov_Mov;
	}

	public Timestamp getFecha_Mov() {
		return Fecha_Mov;
	}

	public void setFecha_Mov(Timestamp fecha_Mov) {
		Fecha_Mov = fecha_Mov;
	}

	public String getDetalle_Mov() {
		return Detalle_Mov;
	}

	public void setDetalle_Mov(String detalle_Mov) {
		Detalle_Mov = detalle_Mov;
	}

	
	
	public Cuentas getCuentaDestino_Mov() {
		return CuentaDestino_Mov;
	}


	public void setCuentaDestino_Mov(Cuentas cuentaDestino_Mov) {
		CuentaDestino_Mov = cuentaDestino_Mov;
	}


	public float getImporte_Mov() {
		return Importe_Mov;
	}

	public void setImporte_Mov(float importe_Mov) {
		Importe_Mov = importe_Mov;
	}

	public boolean isEstado_Mov() {
		return Estado_Mov;
	}

	public void setEstado_Mov(boolean estado_Mov) {
		Estado_Mov = estado_Mov;
	}


	
	//toString
	@Override
	public String toString() {
		return "Movimientos [Id_Mov=" + Id_Mov + ", Cuenta_Mov=" + Cuenta_Mov.getNro_Cuentas() + ", TiposMov_Mov=" + TiposMov_Mov.getId_TiposMov()
				+ ", Fecha_Mov=" + Fecha_Mov + ", Detalle_Mov=" + Detalle_Mov + ", CuentaDestino_Mov="
				+ CuentaDestino_Mov + ", Importe_Mov=" + Importe_Mov + ", Estado_Mov=" + Estado_Mov + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CuentaDestino_Mov == null) ? 0 : CuentaDestino_Mov.hashCode());
		result = prime * result + ((Cuenta_Mov == null) ? 0 : Cuenta_Mov.hashCode());
		result = prime * result + ((Detalle_Mov == null) ? 0 : Detalle_Mov.hashCode());
		result = prime * result + (Estado_Mov ? 1231 : 1237);
		result = prime * result + ((Fecha_Mov == null) ? 0 : Fecha_Mov.hashCode());
		result = prime * result + Id_Mov;
		result = prime * result + Float.floatToIntBits(Importe_Mov);
		result = prime * result + ((TiposMov_Mov == null) ? 0 : TiposMov_Mov.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimientos other = (Movimientos) obj;
		if (CuentaDestino_Mov == null) {
			if (other.CuentaDestino_Mov != null)
				return false;
		} else if (!CuentaDestino_Mov.equals(other.CuentaDestino_Mov))
			return false;
		if (Cuenta_Mov == null) {
			if (other.Cuenta_Mov != null)
				return false;
		} else if (!Cuenta_Mov.equals(other.Cuenta_Mov))
			return false;
		if (Detalle_Mov == null) {
			if (other.Detalle_Mov != null)
				return false;
		} else if (!Detalle_Mov.equals(other.Detalle_Mov))
			return false;
		if (Estado_Mov != other.Estado_Mov)
			return false;
		if (Fecha_Mov == null) {
			if (other.Fecha_Mov != null)
				return false;
		} else if (!Fecha_Mov.equals(other.Fecha_Mov))
			return false;
		if (Id_Mov != other.Id_Mov)
			return false;
		if (Float.floatToIntBits(Importe_Mov) != Float.floatToIntBits(other.Importe_Mov))
			return false;
		if (TiposMov_Mov == null) {
			if (other.TiposMov_Mov != null)
				return false;
		} else if (!TiposMov_Mov.equals(other.TiposMov_Mov))
			return false;
		return true;
	}
	
	
}
