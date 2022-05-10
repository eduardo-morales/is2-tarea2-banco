package py.com.ucom.is;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseDatosMemoria {

	private static BaseDatosMemoria bd = null;
	private static ArrayList<CuentaRegistro> cuentaRegistroTabla = new ArrayList<CuentaRegistro>();
	
	public static BaseDatosMemoria getInstancia() {
		if(bd == null) {
			bd =  new BaseDatosMemoria();
		}
		return bd;
	}
	
    public void insertarCuentaRegistro(CuentaRegistro registro) {
    	cuentaRegistroTabla.add(registro);
    }
    
    public ArrayList<CuentaRegistro> getCuentaRegistros() {
    	return cuentaRegistroTabla;
    }
	
}
