package clases;

public class apotaciones {
	int id_aportaciones;               
    String id_empleado;
    String nombres_empleado;
    String apellidos_empleado;
    String mes;                        
    String año;                               
    double aportacion;                      
    double interes;                     
    double cuota;                       
    double total;
    java.sql.Date fecha_registro;
    
    
	public int getId_aportaciones() {
		return id_aportaciones;
	}
	public void setId_aportaciones(int id_aportaciones) {
		this.id_aportaciones = id_aportaciones;
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
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAño() {
		return año;
	}
	public void setAño(String año) {
		this.año = año;
	}
	public double getAportacion() {
		return aportacion;
	}
	public void setAportacion(double aportacion) {
		this.aportacion = aportacion;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getCuota() {
		return cuota;
	}
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public java.sql.Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(java.sql.Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
	
    
    
	
}
