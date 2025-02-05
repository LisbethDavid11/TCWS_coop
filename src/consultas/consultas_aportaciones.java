package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.apotaciones;
import clases.prestamos;
import conexion.conexion;

public class consultas_aportaciones extends conexion {
	
	
	public List<apotaciones> obtenerDatosAportaciones(String mes, String anio) {
        List<apotaciones> listaAportaciones = new ArrayList<>();
        conexion con = new conexion();
        Connection conexion = null;

        try {
            // Usar la clase conexion para conectar a la base de datos
            conexion = con.conectar();

            // Consulta SQL para obtener los datos
            String sql = "SELECT * FROM aportaciones WHERE mes = ? AND año = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, mes);
            ps.setString(2, anio);

            ResultSet rs = ps.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                apotaciones aportacion = new apotaciones();
                aportacion.setId_aportaciones(rs.getInt("id_aportaciones"));
                aportacion.setId_empleado(rs.getString("id_empleado"));
                aportacion.setNombres_empleado(rs.getString("nombres_empleado"));
                aportacion.setApellidos_empleado(rs.getString("apellidos_empleado"));
                aportacion.setMes(rs.getString("mes"));
                aportacion.setAño(rs.getString("año"));
                aportacion.setAportacion(rs.getDouble("aportacion"));
                aportacion.setInteres(rs.getDouble("interes"));
                aportacion.setCuota(rs.getDouble("cuota"));
                aportacion.setTotal(rs.getDouble("total"));
                aportacion.setFecha_registro(rs.getDate("fecha_registro"));

                listaAportaciones.add(aportacion);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de aportaciones");
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            con.desconectar(conexion);
        }

        return listaAportaciones;
    }
	
	
	
	
	public List<String[]> obtenerEmpleadosActivos() {
	    List<String[]> empleados = new ArrayList<>();
	    String sql = "SELECT id_empleado, nombres_empleado, apellidos_empleado FROM socios";

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
	        while (rs.next()) {
	            empleados.add(new String[]{rs.getString("id_empleado"), rs.getString("nombres_empleado"), rs.getString("apellidos_empleado")});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return empleados;
	}

	
	
	public String[] obtenerMesYAnioAnterior(int mesActual, int anioActual) {
	    int mesAnterior = mesActual - 1;
	    int anioAnterior = anioActual;

	    if (mesAnterior == 0) { // Si es enero, retrocede a diciembre del año anterior
	        mesAnterior = 12;
	        anioAnterior -= 1;
	    }

	    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
	                      "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    return new String[]{meses[mesAnterior - 1], String.valueOf(anioAnterior)};
	}




	
	
	public double obtenerAportacionMesAnterior(String idEmpleado, String mes, String anio) {
	    String sql = "SELECT aportacion FROM aportaciones WHERE id_empleado = ? AND mes = ? AND año = ?";
	    double aportacion = 0.0;

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, idEmpleado);
	        pst.setString(2, mes); // Mes en formato texto
	        pst.setString(3, anio);

	        System.out.println("Ejecutando consulta: " + sql);
	        System.out.println("Parámetros: idEmpleado=" + idEmpleado + ", mes=" + mes + ", anio=" + anio);

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            aportacion = rs.getDouble("aportacion");
	        } else {
	            System.out.println("No se encontraron registros para los parámetros proporcionados.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return aportacion;
	}


	public apotaciones obtenerAportacionPorEmpleado(String idEmpleado, String mes, String anio) {
	    String sql = "SELECT * FROM aportaciones WHERE id_empleado = ? AND mes = ? AND año = ?";
	    apotaciones aportacion = null;

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, idEmpleado);
	        pst.setString(2, mes); // Mes en formato texto
	        pst.setString(3, anio);

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            aportacion = new apotaciones();
	            aportacion.setId_aportaciones(rs.getInt("id_aportaciones"));
	            aportacion.setId_empleado(rs.getString("id_empleado"));
	            aportacion.setNombres_empleado(rs.getString("nombres_empleado"));
	            aportacion.setApellidos_empleado(rs.getString("apellidos_empleado"));
	            aportacion.setMes(rs.getString("mes"));
	            aportacion.setAño(rs.getString("año"));
	            aportacion.setAportacion(rs.getDouble("aportacion"));
	            aportacion.setInteres(rs.getDouble("interes"));
	            aportacion.setCuota(rs.getDouble("cuota"));
	            aportacion.setTotal(rs.getDouble("total"));
	            aportacion.setFecha_registro(rs.getDate("fecha_registro"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return aportacion;
	}


	
	
	public double obtenerCuotaMesAnterior(String idEmpleado, String mes, String anio) {
	    String sql = "SELECT cuota FROM aportaciones WHERE id_empleado = ? AND mes = ? AND año = ?";
	    double cuota = 0.0;

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, idEmpleado);
	        pst.setString(2, mes);
	        pst.setString(3, anio);

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            cuota = rs.getDouble("cuota");
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cuota;
	}


	
	
	public double obtenerInteresMesAnterior(String idEmpleado, String mes, String anio) {
	    String sql = "SELECT interes FROM aportaciones WHERE id_empleado = ? AND mes = ? AND año = ?";
	    double interes = 0.0;

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, idEmpleado);
	        pst.setString(2, mes);
	        pst.setString(3, anio);

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            interes = rs.getDouble("interes");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return interes;
	}
	
	
	public boolean registrarAportacion(apotaciones aportacion) {
	    String sql = "INSERT INTO aportaciones (id_empleado, nombres_empleado, apellidos_empleado, mes, año, aportacion, interes, cuota, fecha_registro) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, aportacion.getId_empleado());
	        pst.setString(2, aportacion.getNombres_empleado());
	        pst.setString(3, aportacion.getApellidos_empleado());
	        pst.setString(4, aportacion.getMes());
	        pst.setString(5, aportacion.getAño());
	        pst.setDouble(6, aportacion.getAportacion());
	        pst.setDouble(7, aportacion.getInteres());
	        pst.setDouble(8, aportacion.getCuota());
	        pst.setDate(9, new java.sql.Date(aportacion.getFecha_registro().getTime()));
	        return pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public List<apotaciones> obtenerAportacionesPorMesYAño(String mes, String anio) {
	    List<apotaciones> lista = new ArrayList<>();
	    String sql = "SELECT año, mes, MIN(fecha_registro) AS fecha_registro " +
	                 "FROM aportaciones " +
	                 "WHERE año = ? AND (mes = ? OR ? = '') " +
	                 "GROUP BY año, mes";

	    conexion conn = new conexion();
	    try (PreparedStatement ps = conn.conectar().prepareStatement(sql)) {
	        ps.setString(1, anio);
	        ps.setString(2, mes);
	        ps.setString(3, mes);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                apotaciones aportacion = new apotaciones();
	                aportacion.setAño(rs.getString("año"));
	                aportacion.setMes(rs.getString("mes"));
	                aportacion.setFecha_registro(rs.getDate("fecha_registro"));
	                lista.add(aportacion);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}



	
	
	public boolean existeRegistro(String mes, String anio) {
	    String sql = "SELECT COUNT(*) FROM aportaciones WHERE mes = ? AND año = ?";
	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, mes);
	        pst.setString(2, anio);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Si el conteo es mayor a 0, existe un registro
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	
	public List<apotaciones> obtenerAportacionesAgrupadas() {
	    List<apotaciones> lista = new ArrayList<>();
	    String sql = "SELECT mes, año, MIN(fecha_registro) AS fecha_registro " +
	                 "FROM aportaciones " +
	                 "GROUP BY mes, año " +
	                 "ORDER BY año DESC, mes DESC";

	    try (Connection con = conectar();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {
	            apotaciones aportacion = new apotaciones();
	            aportacion.setMes(rs.getString("mes"));
	            aportacion.setAño(rs.getString("año"));
	            aportacion.setFecha_registro(rs.getDate("fecha_registro"));
	            lista.add(aportacion);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}
	
	
	public List<apotaciones> obtenerDetallesPorMesYAño(String mes, String anio) {
	    List<apotaciones> lista = new ArrayList<>();
	    String sql = "SELECT * FROM aportaciones WHERE mes = ? AND año = ?";

	    try (Connection con = conectar();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setString(1, mes);
	        pst.setString(2, anio);

	        try (ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	                apotaciones aportacion = new apotaciones();
	                aportacion.setId_aportaciones(rs.getInt("id_aportaciones"));
	                aportacion.setId_empleado(rs.getString("id_empleado"));
	                aportacion.setNombres_empleado(rs.getString("nombres_empleado"));
	                aportacion.setApellidos_empleado(rs.getString("apellidos_empleado"));
	                aportacion.setMes(rs.getString("mes"));
	                aportacion.setAño(rs.getString("año"));
	                aportacion.setAportacion(rs.getDouble("aportacion"));
	                aportacion.setInteres(rs.getDouble("interes"));
	                aportacion.setCuota(rs.getDouble("cuota"));
	                aportacion.setTotal(rs.getDouble("total"));
	                aportacion.setFecha_registro(rs.getDate("fecha_registro"));
	                lista.add(aportacion);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


	public boolean eliminarAportacionesPorMesYAño(String mes, String anio) {
	    String sql = "DELETE FROM aportaciones WHERE mes = ? AND año = ?";
	    try (Connection con = conectar();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, mes);
	        pstmt.setString(2, anio);

	        int filasAfectadas = pstmt.executeUpdate();
	        return filasAfectadas > 0; // Retorna true si se eliminaron registros
	    } catch (SQLException e) {
	        System.err.println("Error al eliminar aportaciones: " + e.getMessage());
	        return false;
	    }
	}
	
	

	
	public List<prestamos> obtenerPrestamosActivosPorMes(String idEmpleado, int mes, int anio) {
	    List<prestamos> prestamos = new ArrayList<>();
	    String sql = "SELECT * FROM prestamos " +
	                 "WHERE id_empleado = ? " +
	                 "AND DATE_ADD(fecha_aprobacion, INTERVAL plazo_pago MONTH) >= ? " +
	                 "AND MONTH(fecha_aprobacion) <= ? AND YEAR(fecha_aprobacion) <= ?";

	    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, idEmpleado); // Establece el ID del socios

	        // Fecha límite para calcular el plazo
	        String fechaSeleccionada = anio + "-" + mes + "-01"; // Primer día del mes seleccionado
	        java.sql.Date fechaInicioMes = java.sql.Date.valueOf(fechaSeleccionada);

	        pst.setDate(2, fechaInicioMes); // Fecha límite de vencimiento del préstamo
	        pst.setInt(3, mes);            // Filtro de mes
	        pst.setInt(4, anio);           // Filtro de año

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            prestamos prestamo = new prestamos();
	            prestamo.setId(rs.getInt("id"));
	            prestamo.setId_empleado(rs.getString("id_empleado"));
	            prestamo.setNombres_empleado(rs.getString("nombres_empleado"));
	            prestamo.setApellidos_empleado(rs.getString("apellidos_empleado"));
	            prestamo.setInteres_deducible_mensual(rs.getDouble("interes_deducible_mensual"));
	            prestamo.setLetra_mensual(rs.getDouble("letra_mensual"));
	            prestamo.setFecha_aprobacion(rs.getDate("fecha_aprobacion"));
	            prestamos.add(prestamo);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return prestamos;
	}

	
	

	public boolean actualizarAportacion(apotaciones aportacion) {
	    String sql = "UPDATE aportaciones SET aportacion = ?, interes = ?, cuota = ?, mes = ?, año = ?, fecha_registro = ? WHERE id_aportaciones = ?";
	    try (Connection con = conectar();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setDouble(1, aportacion.getAportacion());
	        pst.setDouble(2, aportacion.getInteres());
	        pst.setDouble(3, aportacion.getCuota());
	        pst.setString(4, aportacion.getMes());
	        pst.setInt(5, Integer.parseInt(aportacion.getAño()));
	        pst.setDate(6, aportacion.getFecha_registro());
	        pst.setInt(7, aportacion.getId_aportaciones());

	        return pst.executeUpdate() > 0; // Retorna true si se actualizó al menos un registro
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}


	
	
	public List<apotaciones> obtenerAportacionesPorEmpleado(String idEmpleado) {
	    List<apotaciones> aportaciones = new ArrayList<>();
	    conexion con = new conexion();
	    Connection conexion = null;

	    try {
	        conexion = con.conectar();
	        String sql = "SELECT * FROM aportaciones WHERE id_empleado = ?";
	        PreparedStatement ps = conexion.prepareStatement(sql);
	        ps.setString(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            apotaciones aportacion = new apotaciones();
	            aportacion.setId_aportaciones(rs.getInt("id_aportaciones"));
	            aportacion.setMes(rs.getString("mes"));
	            aportacion.setAño(rs.getString("año"));
	            aportacion.setAportacion(rs.getDouble("aportacion"));
	            aportacion.setInteres(rs.getDouble("interes")); // Agregado
	            aportacion.setCuota(rs.getDouble("cuota"));     // Agregado
	            aportacion.setTotal(rs.getDouble("total"));
	            aportaciones.add(aportacion);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        con.desconectar(conexion);
	    }

	    return aportaciones;
	}




}
