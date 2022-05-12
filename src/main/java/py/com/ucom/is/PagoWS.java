package py.com.ucom.is;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import py.com.ucom.is.dao.Cuenta;
import py.com.ucom.is.dao.Pago;

@WebService
public class PagoWS {

	@WebMethod
	public Respuesta registrarCliente(@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta) {

		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();

		Cuenta cuenta = new Cuenta();
		cuenta.setCedula(cedula);
		cuenta.setCuenta(numeroCuenta);

		int codRetorno = bd.insertarCuentaRegistro(cuenta);
		Respuesta respuesta = null;
		if (codRetorno > 0) {
			respuesta = new Respuesta(false, codRetorno, "El número de cuenta y cédula ya existe");
		}

		respuesta = new Respuesta(true, 0, "Registro insertado con éxito");

		return respuesta;

		// PagoRetorno retorno = new PagoRetorno(true, 0, "EXITO");
		// return retorno;
	}

	@WebMethod
	public ArrayList<Cuenta> listarCuentas() {

		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		ArrayList<Cuenta> cuentas = bd.getCuentaRegistros();

		return cuentas;
	}

	@WebMethod
	public Respuesta registrarPago(@WebParam(name = "cedula") String cedula,
			@WebParam(name = "numeroCuenta") Integer numeroCuenta, @WebParam(name = "numeroLinea") String numeroLinea,
			@WebParam(name = "montoFactura") String monto, @WebParam(name = "moneda") String moneda,
			@WebParam(name = "numeroFactura") Integer numeroFactura) {

		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();

		Pago pago = new Pago();
		pago.setCedula(cedula);
		pago.setCuenta(numeroCuenta);
		pago.setMoneda(moneda);
		pago.setNumeroFactura(numeroFactura);
		pago.setNumeroLinea(numeroLinea);

		Respuesta rsp = bd.insertarPago(pago);

		return rsp;

	}

	@WebMethod
	public ConsultaPagoResponse consultarPago(@WebParam(name = "factura") Integer factura) {
		
		BaseDatosMemoria bd = BaseDatosMemoria.getInstancia();
		ConsultaPagoResponse rsp = bd.consultarPago(factura);
		
		return rsp;

	}

}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoRetorno")
class Respuesta {
	Boolean exito;
	Integer codigoError;
	String descripionMensaje;

	public Respuesta() {
	}

	public Boolean getExito() {
		return exito;
	}

	public void setExito(Boolean exito) {
		this.exito = exito;
	}

	public Integer getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripionMensaje() {
		return descripionMensaje;
	}

	public void setDescripionMensaje(String descripionMensaje) {
		this.descripionMensaje = descripionMensaje;
	}

	public Respuesta(Boolean exito, Integer codigoError, String descripionMensaje) {
		super();
		this.exito = exito;
		this.codigoError = codigoError;
		this.descripionMensaje = descripionMensaje;
	}
}