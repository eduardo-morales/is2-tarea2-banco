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
		if(bd == null) {
			bd =  new BaseDatosMemoria();
		}
		return bd;
	}
	
    public int insertarCuentaRegistro(Cuenta registro) {
    	//validar que no se repita cedula o numero de cuenta
    	for (Cuenta registroGuardado : cuentaRegistroTabla) {
			if(registroGuardado.getCedula().equalsIgnoreCase(registro.getCedula())
					&& registroGuardado.getCuenta().intValue()== registro.getCuenta().intValue()) {
				
				return 1000;
			}
		}
    	
    	cuentaRegistroTabla.add(registro);
    	return 0;
    }
    
    public ArrayList<Cuenta> getCuentaRegistros() {
    	return cuentaRegistroTabla;
    }
	
    public int insertarPago(Pago pago) {
    	Boolean valido = false;
		for (Cuenta registroGuardado : cuentaRegistroTabla) {
			if(registroGuardado.getCedula().equalsIgnoreCase(pago.getCedula())
					&& registroGuardado.getCuenta().intValue()== pago.getCuenta().intValue()) {
				
				valido = true;
			}
		}
		if(!valido) {
			return 1000;
			
		}
		
    	pagoTabla.add(pago);
    	return 0;
    }
}
