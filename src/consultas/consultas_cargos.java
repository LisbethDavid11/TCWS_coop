package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import clases.cargos;
import conexion.conexion;

public class consultas_cargos extends conexion {

    // Método para guardar un cargo
    public boolean guardarCargo(cargos cargo) {
        Connection con = conectar();
        String sql = "INSERT INTO cargos (cargos, fecha_creacion) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cargo.getCargos());
            ps.setDate(2, new java.sql.Date(cargo.getFecha_creacion().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
            return false;
        } finally {
            desconectar(con);
        }
    }

    // Método para actualizar un cargo
    public boolean actualizarCargo(cargos cargo) {
        Connection con = conectar();
        String sql = "UPDATE cargos SET cargos = ?, fecha_creacion = ? WHERE id_cargos = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cargo.getCargos());
            ps.setDate(2, new java.sql.Date(cargo.getFecha_creacion().getTime()));
            ps.setInt(3, cargo.getId_cargos());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
            return false;
        } finally {
            desconectar(con);
        }
    }

    
    public boolean verificarExistenciaCargo(String txtcargo) {
        Connection con = conectar();
        String sql = "SELECT COUNT(*) FROM cargos WHERE cargos = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtcargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar(con);
        }
        return false; 
    }

    // Método para verificar si el nombre del cargo corresponde al mismo ID (para evitar duplicados al actualizar)
    public boolean isCargoIdMatch(int idCargo, String txtcargo) {
        Connection con = conectar();
        String sql = "SELECT COUNT(*) FROM cargos WHERE id_cargos = ? AND cargos = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCargo);
            ps.setString(2, txtcargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, el ID y nombre coinciden
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar(con);
        }
        return false;
    }
    
    public boolean eliminarCargo(int idCargo) {
        Connection con = conectar();
        String sql = "DELETE FROM cargos WHERE id_cargos = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCargo);
            
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cargo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<String> obtenerCargos() {
        List<String> cargos = new ArrayList<>();
        Connection con = conectar();
        String sql = "SELECT cargos FROM cargos";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cargos.add(rs.getString("cargos")); // Añade cada cargo a la lista
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los cargos", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cargos;
    }




    
    
}
