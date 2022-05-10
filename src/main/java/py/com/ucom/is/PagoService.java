package py.com.ucom.is;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class PagoService {

	@WebMethod
	public String registrarCliente(@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta) {

		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		
		CuentaRegistro cuenta = new CuentaRegistro();
		cuenta.setCedula(cedula);
		cuenta.setCuenta(numeroCuenta);
			
		int codRetorno  = bd.insertarCuentaRegistro(cuenta);
		if(codRetorno>0) {
			return "El número de cuenta y cédula ya existe";
		}
		
		return "Registro insertado con éxito";
		
		//PagoServiceRetorno retorno = new PagoServiceRetorno(true, 0, "EXITO");
		//return retorno;
	}
	
	@WebMethod
	public ArrayList<CuentaRegistro> listarCuentas() {

		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		ArrayList<CuentaRegistro> cuentas = bd.getCuentaRegistros();
		
		return cuentas;
	}
	
	@WebMethod
	public String registrarPago(
			@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta,
			@WebParam(name = "numeroLinea") String numeroLinea,
			@WebParam(name = "montoFactura") String monto,
			@WebParam(name = "moneda") String moneda,
			@WebParam(name = "numeroFactura") Integer numeroFactura
			) {
		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
	
		PagoRegistro pago = new PagoRegistro();
		pago.setCedula(cedula);
		pago.setCuenta(numeroCuenta);
		pago.setMoneda(moneda);
		pago.setNumeroFactura(numeroFactura);
		pago.setNumeroLinea(numeroLinea);
		
		int codigo = bd.insertarPago(pago);
		
		if(codigo>0) {
			return "la cuenta o documento no están registradas"; 
		}
		
		return "Pago registrado con éxito";
	
		
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