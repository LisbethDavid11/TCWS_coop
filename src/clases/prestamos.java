package clases;

import java.util.Date;

public class prestamos {
	
	int id;
    String id_empleado;
    String nombres_empleado;
    String apellidos_empleado;
    String cuenta_empleado;
    String direccion_empleado;
    String cargo_empleado;
    String area_empleado;
    Date fecha_aprobacion;
    int monto_solicitado;
    int plazo_pago;
    int tasa_interes;
    double interes_deducible_mensual;
    double letra_mensual;
    String motivo_prestamo;
    String autorizado1;
    String cargo_autorizado1;
    String autorizado2;
    String cargo_autorizado2;
    double total_intereses;
    double total_pagar;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}
	public String getNombres_empleado() {
		return nombres_empleado;
	}
	public void setNombres_empleado(String nombres_empleado) {
		this.nombres_empleado = nombres_empleado;
	}
	public String getApellidos_empleado() {
		return apellidos_empleado;
	}
	public void setApellidos_empleado(String apellidos_empleado) {
		this.apellidos_empleado = apellidos_empleado;
	}
	public String getCuenta_empleado() {
		return cuenta_empleado;
	}
	public void setCuenta_empleado(String cuenta_empleado) {
		this.cuenta_empleado = cuenta_empleado;
	}
	public String getDireccion_empleado() {
		return direccion_empleado;
	}
	public void setDireccion_empleado(String direccion_empleado) {
		this.direccion_empleado = direccion_empleado;
	}
	public String getCargo_empleado() {
		return cargo_empleado;
	}
	public void setCargo_empleado(String cargo_empleado) {
		this.cargo_empleado = cargo_empleado;
	}
	public String getArea_empleado() {
		return area_empleado;
	}
	public void setArea_empleado(String area_empleado) {
		this.area_empleado = area_empleado;
	}
	public Date getFecha_aprobacion() {
		return fecha_aprobacion;
	}
	public void setFecha_aprobacion(Date fecha_aprobacion) {
		this.fecha_aprobacion = fecha_aprobacion;
	}
	public int getMonto_solicitado() {
		return monto_solicitado;
	}
	public void setMonto_solicitado(int monto_solicitado) {
		this.monto_solicitado = monto_solicitado;
	}
	public int getPlazo_pago() {
		return plazo_pago;
	}
	public void setPlazo_pago(int plazo_pago) {
		this.plazo_pago = plazo_pago;
	}
	public int getTasa_interes() {
		return tasa_interes;
	}
	public void setTasa_interes(int tasa_interes) {
		this.tasa_interes = tasa_interes;
	}
	public double getInteres_deducible_mensual() {
		return interes_deducible_mensual;
	}
	public void setInteres_deducible_mensual(double interes_deducible_mensual) {
		this.interes_deducible_mensual = interes_deducible_mensual;
	}
	public double getLetra_mensual() {
		return letra_mensual;
	}
	public void setLetra_mensual(double letra_mensual) {
		this.letra_mensual = letra_mensual;
	}
	public String getMotivo_prestamo() {
		return motivo_prestamo;
	}
	public void setMotivo_prestamo(String motivo_prestamo) {
		this.motivo_prestamo = motivo_prestamo;
	}
	public String getAutorizado1() {
		return autorizado1;
	}
	public void setAutorizado1(String autorizado1) {
		this.autorizado1 = autorizado1;
	}
	public String getCargo_autorizado1() {
		return cargo_autorizado1;
	}
	public void setCargo_autorizado1(String cargo_autorizado1) {
		this.cargo_autorizado1 = cargo_autorizado1;
	}
	public String getAutorizado2() {
		return autorizado2;
	}
	public void setAutorizado2(String autorizado2) {
		this.autorizado2 = autorizado2;
	}
	public String getCargo_autorizado2() {
		return cargo_autorizado2;
	}
	public void setCargo_autorizado2(String cargo_autorizado2) {
		this.cargo_autorizado2 = cargo_autorizado2;
	}
	
	// Método para establecer el total de intereses
	public void setTotal_intereses(double total_intereses) {
	    this.total_intereses = total_intereses;
	}

	// Método para obtener el total de intereses
	public double getTotal_intereses() {
	    return total_intereses;
	}

	// Método para establecer el total a pagar
	public void setTotal_pagar(double total_pagar) {
	    this.total_pagar = total_pagar;
	}

	// Método para obtener el total a pagar
	public double getTotal_pagar() {
	    return total_pagar;
	}
	
	
    
	
	
}
