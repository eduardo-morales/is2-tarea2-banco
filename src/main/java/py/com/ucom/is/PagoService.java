package py.com.ucom.is;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class PagoService {

	@WebMethod
	public PagoServiceRetorno registrarCliente(@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta) {

		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		
		CuentaRegistro cuenta = new CuentaRegistro();
		cuenta.setCedula(cedula);
		cuenta.setCuenta(numeroCuenta);
			
		bd.insertarCuentaRegistro(cuenta);
		PagoServiceRetorno retorno = new PagoServiceRetorno(true, 123, "");
		return retorno;
	}
}


class PagoServiceRetorno {
	Boolean exito;
	Integer codigoError;
	String errorDescipcion;
	
	public PagoServiceRetorno(Boolean exito, Integer codigoError, String errorDescipcion) {
		super();
		this.exito = exito;
		this.codigoError = codigoError;
		this.errorDescipcion = errorDescipcion;
	}
}