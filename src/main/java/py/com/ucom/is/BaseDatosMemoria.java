package py.com.ucom.is;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseDatosMemoria {

	private static BaseDatosMemoria bd = null;
	private static ArrayList<CuentaRegistro> cuentaRegistroTabla = new ArrayList<CuentaRegistro>();
	private static ArrayList<PagoRegistro> pagoTabla = new ArrayList<PagoRegistro>();
	
	public static BaseDatosMemoria getInstancia() {
		if(bd == null) {
			bd =  new BaseDatosMemoria();
		}
		return bd;
	}
	
    public int insertarCuentaRegistro(CuentaRegistro registro) {
    	//validar que no se repita cedula o numero de cuenta
    	for (CuentaRegistro registroGuardado : cuentaRegistroTabla) {
			if(registroGuardado.getCedula().equalsIgnoreCase(registro.getCedula())
					&& registroGuardado.getCuenta().intValue()== registro.getCuenta().intValue()) {
				
				return 1000;
			}
		}
    	
    	cuentaRegistroTabla.add(registro);
    	return 0;
    }
    
    public ArrayList<CuentaRegistro> getCuentaRegistros() {
    	return cuentaRegistroTabla;
    }
	
    public int insertarPago(PagoRegistro pago) {
    	Boolean valido = false;
		for (CuentaRegistro registroGuardado : cuentaRegistroTabla) {
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
