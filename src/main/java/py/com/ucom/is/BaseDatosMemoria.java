package py.com.ucom.is;

import java.util.ArrayList;
import java.util.HashMap;

import py.com.ucom.is.dao.Cuenta;
import py.com.ucom.is.dao.Pago;

public class BaseDatosMemoria {

	private static BaseDatosMemoria bd = null;
	private static ArrayList<Cuenta> cuentaRegistroTabla = new ArrayList<Cuenta>();
	private static ArrayList<Pago> pagoTabla = new ArrayList<Pago>();

	public static BaseDatosMemoria getInstancia() {
		if (bd == null) {
			bd = new BaseDatosMemoria();
		}
		return bd;
	}

	public int insertarCuentaRegistro(Cuenta registro) {
		// validar que no se repita cedula o numero de cuenta
		for (Cuenta registroGuardado : cuentaRegistroTabla) {
			if (registroGuardado.getCedula().equalsIgnoreCase(registro.getCedula())
					&& registroGuardado.getCuenta().intValue() == registro.getCuenta().intValue()) {

				return 1000;
			}
		}

		cuentaRegistroTabla.add(registro);
		return 0;
	}

	public ArrayList<Cuenta> getCuentaRegistros() {
		return cuentaRegistroTabla;
	}
	
	public ConsultaPagoResponse consultarPago(Integer nroFactura) {
		ConsultaPagoResponse rsp = new ConsultaPagoResponse();
		rsp.setEstado("NO_PAGADO");
		
		for (Pago registroPago : pagoTabla) {
			if(registroPago.getNumeroFactura().intValue()==nroFactura.intValue()) {
				rsp.setEstado("PAGADO");
				break;
			}
		}
		
		return rsp;
		
	}
	
	

	public Respuesta insertarPago(Pago pago) {
		Boolean valido = false;
		Respuesta rsp = new Respuesta(true,0, "Pago realizado con exito");
		
		//valida que la cuenta existe
		for (Cuenta registroGuardado : cuentaRegistroTabla) {
			if (registroGuardado.getCedula().equalsIgnoreCase(pago.getCedula())
					&& registroGuardado.getCuenta().intValue() == pago.getCuenta().intValue()) {

				valido = true;
				break;
			}
		}
		if (!valido) {
			rsp.setExito(false);
			rsp.setCodigoError(1000);
			rsp.setDescripionMensaje("Persona no registrada");
			return rsp;
		}
		
		//busca factura ya pagada
		for (Pago registroGuardado : pagoTabla) {
			if(registroGuardado.getNumeroFactura().intValue()==pago.getNumeroFactura().intValue()) {
				valido = false;
				break;
			}
		}
		
		
		if (!valido) {
			rsp.setExito(false);
			rsp.setCodigoError(2000);
			rsp.setDescripionMensaje("Pago ya realizado");
			return rsp;
		}

		pagoTabla.add(pago);
		return rsp;
	}
}

class ConsultaPagoResponse{
	private String estado;
	private String fechaProceso;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
}
