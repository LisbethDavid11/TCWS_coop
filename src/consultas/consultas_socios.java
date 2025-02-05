package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import clases.socios;
import conexion.conexion;


//TCWS
public class consultas_socios extends conexion {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean guardar_empleado(socios empleado, Date fecha_nacimiento, Date fecha_inicio, Date fecha_renuncia) {
	    PreparedStatement ps = null;
	    Connection con = conectar();

	    String sql = "INSERT INTO socios (id_empleado, identidad_empleado, nombres_empleado, apellidos_empleado, sexo_empleado, nacimiento_empleado, civil_empleado, direccion_empleado, "
	            + "tel_empleado, correo_empleado, cargo_empleado, area_empleado, inicio_empleado, renuncia_empleado, fotografia_empleado, cuenta_empleado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	    try {
	        ps = con.prepareStatement(sql);
	        java.sql.Date sqlDate = new java.sql.Date(fecha_nacimiento.getTime());
	        java.sql.Date sqlDate2 = new java.sql.Date(fecha_inicio.getTime());
	        java.sql.Date sqlDate3 = null;
	        if (fecha_renuncia != null) {
	            sqlDate3 = new java.sql.Date(fecha_renuncia.getTime());
	        }

	        ps.setString(1, empleado.getId_empleado());
	        ps.setString(2, empleado.getIdentidad_empleado());
	        ps.setString(3, empleado.getNombres_empleado());
	        ps.setString(4, empleado.getApellidos_empleado());
	        ps.setString(5, empleado.getSexo_empleado());
	        ps.setDate(6, sqlDate);
	        ps.setString(7, empleado.getCivil_empleado());
	        ps.setString(8, empleado.getDireccion_empleado());
	        ps.setString(9, empleado.getTel_empleado());
	        ps.setString(10, empleado.getCorreo_empleado());
	        ps.setString(11, empleado.getCargo_empleado());
	        ps.setString(12, empleado.getArea_empleado());
	        ps.setDate(13, sqlDate2);
	        ps.setDate(14, sqlDate3);  // Puede ser null
	        ps.setString(15, empleado.getFotografia_empleado());
	        ps.setString(16, empleado.getCuenta_empleado());

	        ps.execute();
	        return true;

	    } catch (SQLException e) {
	        System.err.println(e);
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.err.println(e);
	        }
	    }
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean eliminar_empleado(int id) {
	    PreparedStatement ps = null;
	    Connection con = conectar();
	    
	    String sql = "DELETE FROM socios WHERE id = ?";  
	    
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        int rowsDeleted = ps.executeUpdate();
	        return rowsDeleted > 0;  
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	        
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	///////////////////////////////////////////////////////////////
    public socios obtenerEmpleadoPorId(int idEmpleado) {
        conexion conex = new conexion();
        socios empleado = null;

        try {
            String sql = "SELECT * FROM socios WHERE id_empleado = ?";
            PreparedStatement pst = conex.conectar().prepareStatement(sql);
            pst.setInt(1, idEmpleado); 

            ResultSet rs = pst.executeQuery(); 

            if (rs.next()) {
                empleado = new socios(); 

                empleado.setId(rs.getInt("id"));
                empleado.setId_empleado(rs.getString("id_empleado"));
                empleado.setIdentidad_empleado(rs.getString("identidad_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                empleado.setSexo_empleado(rs.getString("sexo_empleado"));
                empleado.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                empleado.setCivil_empleado(rs.getString("civil_empleado"));
                empleado.setDireccion_empleado(rs.getString("direccion_empleado"));
                empleado.setTel_empleado(rs.getString("tel_empleado"));
                empleado.setCorreo_empleado(rs.getString("correo_empleado"));
                empleado.setCargo_empleado(rs.getString("cargo_empleado"));
                empleado.setArea_empleado(rs.getString("area_empleado"));
                empleado.setInicio_empleado(rs.getDate("inicio_empleado"));
                empleado.setRenuncia_empleado(rs.getDate("renuncia_empleado"));
                empleado.setFotografia_empleado(rs.getString("fotografia_empleado"));
                empleado.setCuenta_empleado(rs.getString("cuenta_empleado"));
            }

            rs.close(); // Cerramos ResultSet y PreparedStatement.
            pst.close();
            conex.desconectar(null); // Desconectamos la conexión.

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar los datos del socios", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return empleado; 
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String empleadoExiste(String id_empleado, String identidad_empleado) {
        String campoDuplicado = null;
        try (Connection conn = new conexion().conectar();
             PreparedStatement pst = conn.prepareStatement("SELECT id_empleado, identidad_empleado FROM socios WHERE id_empleado = ? OR identidad_empleado = ?")) {

            pst.setString(1, id_empleado);
            pst.setString(2, identidad_empleado);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (rs.getString("id_empleado") == id_empleado) {
                    campoDuplicado = "Id de socios";
                } else if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
                    campoDuplicado = "Identidad de socios";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return campoDuplicado;
    }

	    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String empleadoExisteParaActualizar(int id, int nuevoIdEmpleado, String identidad_empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar(); 

        String sql = "SELECT * FROM socios WHERE (identidad_empleado = ? OR id_empleado = ?) AND id != ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, identidad_empleado);
            ps.setInt(2, nuevoIdEmpleado);
            ps.setInt(3, id); // Se excluye el socios que se está editando

            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
                    return "Identidad";
                } else if (rs.getInt("id_empleado") == nuevoIdEmpleado) {
                    return "Id del socios";
                }
            }
            return null; 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                desconectar(con); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    public boolean actualizar_empleado(socios emp, String idOriginal, String nuevoIdEmpleado, Date fechaNacimiento, Date fechaInicio, Date fechaRenuncia) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = conectar();
            
            String sql = "UPDATE socios SET " +
                         "id_empleado=?, identidad_empleado=?, nombres_empleado=?, apellidos_empleado=?, " +
                         "sexo_empleado=?, nacimiento_empleado=?, civil_empleado=?, direccion_empleado=?, " +
                         "tel_empleado=?, correo_empleado=?, cargo_empleado=?, area_empleado=?, " +
                         "inicio_empleado=?, renuncia_empleado=?, fotografia_empleado=?, cuenta_empleado=? " +
                         "WHERE id=?";
            
            ps = con.prepareStatement(sql);
            
            int i = 1;
            ps.setString(i++, nuevoIdEmpleado);
            ps.setString(i++, emp.getIdentidad_empleado());
            ps.setString(i++, emp.getNombres_empleado());
            ps.setString(i++, emp.getApellidos_empleado());
            ps.setString(i++, emp.getSexo_empleado());
            ps.setDate(i++, new java.sql.Date(fechaNacimiento.getTime()));
            ps.setString(i++, emp.getCivil_empleado());
            ps.setString(i++, emp.getDireccion_empleado());
            ps.setString(i++, emp.getTel_empleado());
            ps.setString(i++, emp.getCorreo_empleado());
            ps.setString(i++, emp.getCargo_empleado());
            ps.setString(i++, emp.getArea_empleado());
            ps.setDate(i++, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(i++, fechaRenuncia != null ? new java.sql.Date(fechaRenuncia.getTime()) : null);
            ps.setString(i++, emp.getFotografia_empleado());
            ps.setString(i++, emp.getCuenta_empleado());
            ps.setString(i++, idOriginal); 
            
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar socios: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }



    public boolean existeIdEmpleado(String idEmpleado, String idOriginal) {
        String query = "SELECT COUNT(*) FROM socios WHERE id_empleado = ? AND id <> ?";
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, idEmpleado);
            stmt.setString(2, idOriginal); // Excluir el registro del socios actual
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, el ID existe en otro socios
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra, devuelve falso
    }


    
    public boolean existeIdentidadEmpleado(String identidadEmpleado, String idOriginal) {
        String query = "SELECT COUNT(*) FROM socios WHERE identidad_empleado = ? AND id <> ?";
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, identidadEmpleado);
            stmt.setString(2, idOriginal); // Excluir el registro del socios actual
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, la identidad existe en otro socios
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra, devuelve falso
    }




    
    public String obtenerIdentidadEmpleado(String idOriginal) {
        String identidadEmpleado = null;
        String query = "SELECT identidad_empleado FROM socios WHERE id = ?"; // Aquí asumo que "id" es la columna de la tabla que almacena el ID original.
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, idOriginal); // El ID original del socios
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                identidadEmpleado = rs.getString("identidad_empleado"); // Obtener la identidad del socios
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return identidadEmpleado; // Devuelve la identidad, o null si no se encuentra
    }
    
    
    
    public List<socios> obtenerNombresEmpleados() {
        List<socios> empleados = new ArrayList<>();
        conexion con = new conexion();
        Connection conexion = null;

        try {
            conexion = con.conectar();
            String sql = "SELECT id_empleado, nombres_empleado, apellidos_empleado FROM socios";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                socios emp = new socios();
                emp.setId_empleado(rs.getString("id_empleado"));
                emp.setNombres_empleado(rs.getString("nombres_empleado"));
                emp.setApellidos_empleado(rs.getString("apellidos_empleado"));
                empleados.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.desconectar(conexion);
        }

        return empleados;
    }
    
    
    
    public socios obtenerDatosEmpleadoPorId(String idEmpleado) {
        socios emp = null;
        conexion con = new conexion();
        Connection conexion = null;

        try {
            conexion = con.conectar();
            String sql = "SELECT * FROM socios WHERE id_empleado = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new socios();
                emp.setId_empleado(rs.getString("id_empleado"));
                emp.setIdentidad_empleado(rs.getString("identidad_empleado"));
                emp.setNombres_empleado(rs.getString("nombres_empleado"));
                emp.setApellidos_empleado(rs.getString("apellidos_empleado"));
                emp.setTel_empleado(rs.getString("tel_empleado"));
                emp.setCuenta_empleado(rs.getString("cuenta_empleado"));
                emp.setCargo_empleado(rs.getString("cargo_empleado"));
                emp.setArea_empleado(rs.getString("area_empleado"));
                emp.setInicio_empleado(rs.getDate("inicio_empleado"));
                emp.setRenuncia_empleado(rs.getDate("renuncia_empleado"));
                emp.setFotografia_empleado(rs.getString("fotografia_empleado"));
            } else {
                System.out.println("No se encontraron datos para el id_empleado: " + idEmpleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.desconectar(conexion);
        }

        return emp;
    }



}
