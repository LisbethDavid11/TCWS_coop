package consultas;

import clases.apotaciones;
import clases.socios;
import clases.prestamos;
import conexion.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class consultas_reportes extends conexion {

	 private conexion conexionBD = new conexion();
	
    public List<socios> obtenerTodosLosEmpleados() {
        List<socios> lista = new ArrayList<>();
        String sql = "SELECT * FROM socios";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                socios empleado = new socios();
                empleado.setId(rs.getInt("id"));
                empleado.setId_empleado(rs.getString("id_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                lista.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<apotaciones> obtenerAportacionesPorEmpleado(String idEmpleado) {
        List<apotaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM aportaciones WHERE id_empleado = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, idEmpleado);
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

    public List<prestamos> obtenerPrestamosPorEmpleado(String idEmpleado) {
        List<prestamos> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE id_empleado = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, idEmpleado);
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
    
    
    public List<prestamos> obtenerPrestamosMensuales(int mes, int anio) {
        List<prestamos> prestamos = new ArrayList<>();
        String query = "SELECT id, id_empleado, nombres_empleado, apellidos_empleado, fecha_aprobacion, " +
                       "monto_solicitado, total_pagar, plazo_pago, tasa_interes, letra_mensual " + // Añadidas las columnas faltantes
                       "FROM prestamos " +
                       "WHERE MONTH(fecha_aprobacion) = ? AND YEAR(fecha_aprobacion) = ?";

        try (Connection con = new conexion().conectar(); // Instancia de la clase conexion
             PreparedStatement ps = con.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            ps.setInt(1, mes);  // Mes seleccionado
            ps.setInt(2, anio); // Año seleccionado

            try (ResultSet rs = ps.executeQuery()) {
                // Iterar sobre los resultados de la consulta
                while (rs.next()) {
                    prestamos prestamo = new prestamos();
                    prestamo.setId(rs.getInt("id"));
                    prestamo.setId_empleado(rs.getString("id_empleado"));
                    prestamo.setNombres_empleado(rs.getString("nombres_empleado"));
                    prestamo.setApellidos_empleado(rs.getString("apellidos_empleado"));
                    prestamo.setFecha_aprobacion(rs.getDate("fecha_aprobacion"));
                    prestamo.setMonto_solicitado(rs.getInt("monto_solicitado"));
                    prestamo.setTotal_pagar(rs.getDouble("total_pagar"));
                    prestamo.setPlazo_pago(rs.getInt("plazo_pago")); // Asignar el valor de plazo_pago
                    prestamo.setTasa_interes(rs.getInt("tasa_interes")); // Asignar el valor de tasa_interes
                    prestamo.setLetra_mensual(rs.getDouble("letra_mensual")); // Asignar el valor de letra_mensual

                    // Agregar el préstamo a la lista
                    prestamos.add(prestamo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prestamos;
    }
    
    
   
    /////// consultas para reporte_general_mensual
    
    public List<Map<String, Object>> obtenerReporteMensual(int mes, int anio) {
        List<Map<String, Object>> reporte = new ArrayList<>();
        String query = "SELECT " +
                       "    s.id_empleado AS codigo_socio, " +
                       "    CONCAT_WS(' ', COALESCE(s.nombres_empleado, ''), COALESCE(s.apellidos_empleado, '')) AS nombre_completo, " +
                       "    COALESCE(a.aportacion, 0.0) AS aportacion_mensual, " +
                       "    IFNULL(MAX(p.monto_solicitado), 0.0) AS monto_solicitado, " +
                       "    IFNULL(MAX(p.fecha_aprobacion), MAX(a.fecha_registro)) AS fecha_solicitud, " +
                       "    IFNULL(MAX(p.plazo_pago), 0) AS plazo_meses, " +
                       "    IFNULL(MAX(p.tasa_interes), 0) AS tasa_interes, " +
                       "    IFNULL(MAX(p.monto_solicitado) * (MAX(p.tasa_interes) / 100), 0.0) AS interes, " +
                       "    IFNULL(MAX(p.letra_mensual), 0.0) AS cuota_mensual, " +
                       "    IFNULL(MAX(p.monto_solicitado) + (MAX(p.monto_solicitado) * (MAX(p.tasa_interes) / 100)), 0.0) AS total_pagar, " +
                       "    IFNULL((MAX(p.monto_solicitado) + (MAX(p.monto_solicitado) * (MAX(p.tasa_interes) / 100))) - MAX(p.monto_solicitado), 0.0) AS total_acumulado " +
                       "FROM socios s " +
                       "LEFT JOIN aportaciones a " +
                       "    ON s.id_empleado = a.id_empleado " +
                       "    AND a.año = ? " +
                       "    AND a.mes = ? " +
                       "LEFT JOIN prestamos p " +
                       "    ON s.id_empleado = p.id_empleado " +
                       "    AND DATE_ADD(p.fecha_aprobacion, INTERVAL p.plazo_pago MONTH) >= ? " +  // Solo préstamos vigentes
                       "    AND (YEAR(p.fecha_aprobacion) < ? OR (YEAR(p.fecha_aprobacion) = ? AND MONTH(p.fecha_aprobacion) <= ?)) " + // Préstamos iniciados antes o en el mes del reporte
                       "GROUP BY s.id_empleado, s.nombres_empleado, s.apellidos_empleado, a.aportacion " +
                       "ORDER BY s.id_empleado ASC";

        try (Connection conn = conexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, anio);
            ps.setString(2, obtenerNombreMes(mes)); // ✅ Convertimos el mes a texto antes de pasarlo
            ps.setDate(3, java.sql.Date.valueOf(anio + "-" + mes + "-01")); // Fecha límite para préstamos activos
            ps.setInt(4, anio);
            ps.setInt(5, anio);
            ps.setInt(6, mes);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.isBeforeFirst()) { 
                    System.out.println("No se encontraron registros en la base de datos.");
                } else {
                    while (rs.next()) {
                        Map<String, Object> fila = new HashMap<>();
                        fila.put("codigo_socio", rs.getString("codigo_socio"));
                        fila.put("nombre_completo", rs.getString("nombre_completo"));
                        fila.put("aportacion_mensual", rs.getBigDecimal("aportacion_mensual"));
                        fila.put("monto_solicitado", rs.getBigDecimal("monto_solicitado"));
                        fila.put("fecha_solicitud", rs.getDate("fecha_solicitud"));
                        fila.put("plazo_meses", rs.getInt("plazo_meses"));
                        fila.put("tasa_interes", rs.getBigDecimal("tasa_interes"));
                        fila.put("interes", rs.getBigDecimal("interes"));
                        fila.put("cuota_mensual", rs.getBigDecimal("cuota_mensual"));
                        fila.put("total_pagar", rs.getBigDecimal("total_pagar"));
                        fila.put("total_acumulado", rs.getBigDecimal("total_acumulado"));
                        reporte.add(fila);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reporte;
    }



    private String obtenerNombreMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return (mes >= 1 && mes <= 12) ? meses[mes - 1] : "Enero"; // Valor por defecto en caso de error
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /////// consultas para reporte_general_anual_ext
    
    public List<Map<String, Object>> obtenerReporteAnual(int anio) {
        List<Map<String, Object>> reporte = new ArrayList<>();

        String query = "SELECT " +
                "    s.id_empleado AS codigo_socio, " +
                "    CONCAT_WS(' ', COALESCE(s.nombres_empleado, ''), COALESCE(s.apellidos_empleado, '')) AS nombre_completo, " +
                "    COALESCE(a.aportacion, 0.00) AS aportacion_mensual, " +
                "    s.inicio_empleado AS inicio_empleado, " + // 🔹 Se agrega la columna inicio_empleado
                "    COALESCE((a.aportacion / (SELECT SUM(aportacion) FROM aportaciones WHERE año = ?)) * 100, 0) AS porcentaje_dividendos, " +
                "    ROUND((a.aportacion * 0.10), 2) AS total_dividendos, " +
                "    p.fecha_aprobacion AS fecha_solicitud, " +
                "    COALESCE(p.monto_solicitado, 0.00) AS monto_solicitado, " +
                "    COALESCE(p.plazo_pago, 0) AS plazo_meses, " +
                "    COALESCE(p.tasa_interes, 0) AS tasa_interes, " +
                "    ROUND(COALESCE(p.monto_solicitado * p.tasa_interes / 100, 0.00), 2) AS total_interes, " +
                "    ROUND(COALESCE(p.letra_mensual, 0.00), 2) AS pago_mensual, " +
                "    ROUND(COALESCE(p.monto_solicitado + (p.monto_solicitado * p.tasa_interes / 100), 0.00), 2) AS monto_total_pagar, " +
                "    ROUND(COALESCE((p.monto_solicitado * p.tasa_interes / 100), 0.00), 2) AS total_acumulado " +
                "FROM socios s " +
                "LEFT JOIN aportaciones a ON s.id_empleado = a.id_empleado AND a.año = ? " +
                "LEFT JOIN prestamos p ON s.id_empleado = p.id_empleado AND YEAR(p.fecha_aprobacion) = ? " +
                "GROUP BY " +
                "    s.id_empleado, s.nombres_empleado, s.apellidos_empleado, " +
                "    s.inicio_empleado, " + // 🔹 Se asegura de incluir en el GROUP BY
                "    p.fecha_aprobacion, p.monto_solicitado, p.plazo_pago, p.tasa_interes, a.aportacion, " +
                "    p.letra_mensual " +
                "ORDER BY s.id_empleado ASC";

        try (Connection conn = conexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, anio);
            ps.setInt(2, anio);
            ps.setInt(3, anio);

            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
            	    Map<String, Object> fila = new HashMap<>();
            	    fila.put("codigo_socio", rs.getString("codigo_socio"));
            	    fila.put("nombre_completo", rs.getString("nombre_completo"));
            	    fila.put("aportacion_mensual", rs.getBigDecimal("aportacion_mensual"));
            	    fila.put("inicio_empleado", rs.getDate("inicio_empleado")); // 🔹 Recuperar correctamente la fecha de inicio
            	    fila.put("porcentaje_dividendos", rs.getInt("porcentaje_dividendos"));
            	    fila.put("total_dividendos", rs.getBigDecimal("total_dividendos"));
            	    fila.put("fecha_solicitud", rs.getDate("fecha_solicitud"));
            	    fila.put("monto_solicitado", rs.getBigDecimal("monto_solicitado"));
            	    fila.put("plazo_meses", rs.getInt("plazo_meses"));
            	    fila.put("tasa_interes", rs.getInt("tasa_interes"));
            	    fila.put("total_interes", rs.getBigDecimal("total_interes"));
            	    fila.put("pago_mensual", rs.getBigDecimal("pago_mensual"));
            	    fila.put("monto_total_pagar", rs.getBigDecimal("monto_total_pagar"));
            	    fila.put("total_acumulado", rs.getBigDecimal("total_acumulado"));
            	    reporte.add(fila);
            	}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reporte;
    }



    
    public Map<String, Object> obtenerTotalesGenerales(int anio) {
        Map<String, Object> totales = new HashMap<>();

        String query = "SELECT " +
                       "COUNT(DISTINCT p.id) AS cantidad_prestamos, " +
                       "COALESCE(SUM(a.aportacion), 0.00) AS total_aportaciones, " +
                       "COALESCE(SUM(p.total_pagar), 0.00) AS total_mensual_prestamos, " +
                       "COALESCE(SUM(p.monto_solicitado), 0.00) AS total_prestamos " + // No recalculamos, tomamos el total de la columna
                       "FROM socios s " +
                       "LEFT JOIN aportaciones a ON s.id_empleado = a.id_empleado AND a.año = ? " +
                       "LEFT JOIN prestamos p ON s.id_empleado = p.id_empleado AND YEAR(p.fecha_aprobacion) = ?";

        try (Connection conn = conexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, anio);
            ps.setInt(2, anio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totales.put("cantidad_prestamos", rs.getInt("cantidad_prestamos"));
                    totales.put("total_aportaciones", rs.getBigDecimal("total_aportaciones")); // Coincide con "Aportación Mensual"
                    totales.put("total_mensual_prestamos", rs.getBigDecimal("total_mensual_prestamos")); // Coincide con "Monto Total a Pagar"
                    totales.put("total_prestamos", rs.getBigDecimal("total_prestamos")); // Coincide con "Monto Solicitado"
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totales;
    }



}
