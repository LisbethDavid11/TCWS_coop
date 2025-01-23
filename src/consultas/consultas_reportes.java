package consultas;

import clases.apotaciones;
import clases.empleados;
import clases.prestamos;
import conexion.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class consultas_reportes extends conexion {

    public List<empleados> obtenerTodosLosEmpleados() {
        List<empleados> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                empleados empleado = new empleados();
                empleado.setId(rs.getInt("id"));
                empleado.setId_empleado(rs.getInt("id_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                lista.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<apotaciones> obtenerAportacionesPorEmpleado(int idEmpleado) {
        List<apotaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM aportaciones WHERE id_empleado = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idEmpleado);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    apotaciones aportacion = new apotaciones();
                    aportacion.setMes(rs.getString("mes"));
                    aportacion.setAportacion(rs.getDouble("aportacion"));
                    aportacion.setTotal(rs.getDouble("total"));
                    aportacion.setCuota(rs.getDouble("cuota"));
                    aportacion.setInteres(rs.getDouble("interes"));
                    lista.add(aportacion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<prestamos> obtenerPrestamosPorEmpleado(int idEmpleado) {
        List<prestamos> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE id_empleado = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idEmpleado);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    prestamos prestamo = new prestamos();
                    prestamo.setMotivo_prestamo(rs.getString("motivo_prestamo"));
                    prestamo.setTasa_interes(rs.getInt("tasa_interes"));
                    prestamo.setPlazo_pago(rs.getInt("plazo_pago"));
                    prestamo.setLetra_mensual(rs.getDouble("letra_mensual"));
                    prestamo.setMonto_solicitado(rs.getInt("monto_solicitado"));
                    prestamo.setFecha_aprobacion(rs.getDate("fecha_aprobacion"));
                    lista.add(prestamo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
