package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import clases.prestamos;
import conexion.conexion;

public class consultas_prestamos {
	
	
	public boolean guardarPrestamo(prestamos prestamo) {
	    String sql = "INSERT INTO prestamos (id_empleado, nombres_empleado, apellidos_empleado, cuenta_empleado, " +
	                 "direccion_empleado, cargo_empleado, area_empleado, fecha_aprobacion, monto_solicitado, plazo_pago, tasa_interes, " +
	                 "interes_deducible_mensual, letra_mensual, motivo_prestamo, autorizado1, cargo_autorizado1, " +
	                 "autorizado2, cargo_autorizado2) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    conexion conn = new conexion();

	    try (Connection con = conn.conectar();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setString(1, prestamo.getId_empleado());
	        pst.setString(2, prestamo.getNombres_empleado());
	        pst.setString(3, prestamo.getApellidos_empleado());
	        pst.setString(4, prestamo.getCuenta_empleado());
	        pst.setString(5, prestamo.getDireccion_empleado());
	        pst.setString(6, prestamo.getCargo_empleado());
	        pst.setString(7, prestamo.getArea_empleado());
	        pst.setDate(8, new java.sql.Date(prestamo.getFecha_aprobacion().getTime()));
	        pst.setInt(9, prestamo.getMonto_solicitado());
	        pst.setInt(10, prestamo.getPlazo_pago());
	        pst.setInt(11, prestamo.getTasa_interes());
	        pst.setDouble(12, prestamo.getInteres_deducible_mensual());
	        pst.setDouble(13, prestamo.getLetra_mensual());
	        pst.setString(14, prestamo.getMotivo_prestamo());
	        pst.setString(15, prestamo.getAutorizado1());
	        pst.setString(16, prestamo.getCargo_autorizado1());
	        pst.setString(17, prestamo.getAutorizado2());
	        pst.setString(18, prestamo.getCargo_autorizado2());

	        pst.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.err.println("Error al guardar préstamo: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean actualizarPrestamo(prestamos prestamo) {
	    String sql = "UPDATE prestamos SET nombres_empleado = ?, apellidos_empleado = ?, id_empleado = ?, cuenta_empleado = ?, " +
	                 "direccion_empleado = ?, cargo_empleado = ?, area_empleado = ?, fecha_aprobacion = ?, " +
	                 "monto_solicitado = ?, plazo_pago = ?, tasa_interes = ?, interes_deducible_mensual = ?, " +
	                 "letra_mensual = ?, motivo_prestamo = ?, autorizado1 = ?, cargo_autorizado1 = ?, " +
	                 "autorizado2 = ?, cargo_autorizado2 = ? WHERE id = ?";
	    conexion conn = new conexion();
	    try (Connection con = conn.conectar();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setString(1, prestamo.getNombres_empleado());
	        pst.setString(2, prestamo.getApellidos_empleado());
	        pst.setString(3, prestamo.getId_empleado());
	        pst.setString(4, prestamo.getCuenta_empleado());
	        pst.setString(5, prestamo.getDireccion_empleado());
	        pst.setString(6, prestamo.getCargo_empleado());
	        pst.setString(7, prestamo.getArea_empleado());
	        pst.setDate(8, new java.sql.Date(prestamo.getFecha_aprobacion().getTime()));
	        pst.setInt(9, prestamo.getMonto_solicitado());
	        pst.setInt(10, prestamo.getPlazo_pago());
	        pst.setInt(11, prestamo.getTasa_interes());
	        pst.setDouble(12, prestamo.getInteres_deducible_mensual());
	        pst.setDouble(13, prestamo.getLetra_mensual());
	        pst.setString(14, prestamo.getMotivo_prestamo());
	        pst.setString(15, prestamo.getAutorizado1());
	        pst.setString(16, prestamo.getCargo_autorizado1());
	        pst.setString(17, prestamo.getAutorizado2());
	        pst.setString(18, prestamo.getCargo_autorizado2());
	        pst.setInt(19, prestamo.getId());

	        return pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	
	public boolean eliminarPrestamo(int id) {
	    String sql = "DELETE FROM prestamos WHERE id = ?";
	    conexion conn = new conexion();
	    try (Connection con = conn.conectar();
	         PreparedStatement pst = con.prepareStatement(sql)) {
	        
	        pst.setInt(1, id);
	        return pst.executeUpdate() > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al eliminar el préstamo: " + e.getMessage(), 
	                                      "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}


	
	public List<prestamos> obtenerPrestamosPorEmpleado(String idEmpleado) {
	    List<prestamos> prestamos = new ArrayList<>();
	    conexion con = new conexion();
	    Connection conexion = null;

	    try {
	        conexion = con.conectar();
	        String sql = "SELECT * FROM prestamos WHERE id_empleado = ?";
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setString(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            prestamos prest = new prestamos();
	            prest.setId(rs.getInt("id"));
	            prest.setId_empleado(rs.getString("id_empleado"));
	            prest.setNombres_empleado(rs.getString("nombres_empleado"));
	            prest.setApellidos_empleado(rs.getString("apellidos_empleado"));
	            prest.setCuenta_empleado(rs.getString("cuenta_empleado"));
	            prest.setDireccion_empleado(rs.getString("direccion_empleado"));
	            prest.setCargo_empleado(rs.getString("cargo_empleado"));
	            prest.setArea_empleado(rs.getString("area_empleado"));
	            prest.setFecha_aprobacion(rs.getDate("fecha_aprobacion"));
	            prest.setMonto_solicitado(rs.getInt("monto_solicitado"));
	            prest.setPlazo_pago(rs.getInt("plazo_pago"));
	            prest.setTasa_interes(rs.getInt("tasa_interes"));
	            prest.setInteres_deducible_mensual(rs.getDouble("interes_deducible_mensual"));
	            prest.setLetra_mensual(rs.getDouble("letra_mensual"));
	            prest.setMotivo_prestamo(rs.getString("motivo_prestamo"));
	            prest.setAutorizado1(rs.getString("autorizado1"));
	            prest.setCargo_autorizado1(rs.getString("cargo_autorizado1"));
	            prest.setAutorizado2(rs.getString("autorizado2"));
	            prest.setCargo_autorizado2(rs.getString("cargo_autorizado2"));
	            prest.setTotal_intereses(rs.getDouble("total_intereses"));
	            prest.setTotal_pagar(rs.getDouble("total_pagar"));

	            prestamos.add(prest);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        con.desconectar(conexion);
	    }

	    return prestamos;
	}





}
