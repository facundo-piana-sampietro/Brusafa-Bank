package exceptions;
import java.io.IOException;

public class ImporteNegativo extends IOException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "El importe no puede ser negativo!";
	}

}
