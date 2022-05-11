package py.com.ucom.is;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import py.com.ucom.is.dao.Cuenta;
import py.com.ucom.is.dao.Pago;

@WebService
public class PagoWS {

	@WebMethod
	public String registrarCliente(@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta) {

		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		
		Cuenta cuenta = new Cuenta();
		cuenta.setCedula(cedula);
		cuenta.setCuenta(numeroCuenta);
			
		int codRetorno  = bd.insertarCuentaRegistro(cuenta);
		if(codRetorno>0) {
			return "El número de cuenta y cédula ya existe";
		}
		
		return "Registro insertado con éxito";
		
		//PagoRetorno retorno = new PagoRetorno(true, 0, "EXITO");
		//return retorno;
	}
	
	@WebMethod
	public ArrayList<Cuenta> listarCuentas() {

		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		ArrayList<Cuenta> cuentas = bd.getCuentaRegistros();
		
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
	
		Pago pago = new Pago();
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoRetorno")
class PagoRetorno implements Serializable{
	Boolean exito;
	Integer codigoError;
	String errorDescipcion;
	
	
	
	public PagoRetorno(Boolean exito, Integer codigoError, String errorDescipcion) {
		super();
		this.exito = exito;
		this.codigoError = codigoError;
		this.errorDescipcion = errorDescipcion;
	}
}