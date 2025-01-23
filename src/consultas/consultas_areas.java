package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import clases.areas;
import conexion.conexion;

public class consultas_areas extends conexion {

    public boolean guardarArea(areas area) {
        Connection con = conectar();
        String sql = "INSERT INTO areas (areas, fecha_creacion) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, area.getAreas());
            ps.setDate(2, new java.sql.Date(area.getFecha_creacion().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en la bd", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            desconectar(con);
        }
    }

    // Método para actualizar un cargo
    public boolean actualizarArea(areas area) {
        Connection con = conectar();
        String sql = "UPDATE areas SET areas = ?, fecha_creacion = ? WHERE id_areas = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, area.getAreas());
            ps.setDate(2, new java.sql.Date(area.getFecha_creacion().getTime()));
            ps.setInt(3, area.getId_areas());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar en la bd", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            desconectar(con);
        }
    }

    
    public boolean verificarExistenciaArea(String txtarea) {
        Connection con = conectar();
        String sql = "SELECT COUNT(*) FROM areas WHERE areas = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtarea);
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

    // Método para verificar si el nombre del area corresponde al mismo ID (para evitar duplicados al actualizar)
    public boolean isAreaIdMatch(int idArea, String txtareas) {
        Connection con = conectar();
        String sql = "SELECT COUNT(*) FROM areas WHERE id_areas = ? AND areas = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idArea);
            ps.setString(2, txtareas);
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
    
    public boolean eliminarArea(int idArea) {
        Connection con = conectar();
        String sql = "DELETE FROM areas WHERE id_areas = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idArea);
            
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el area en la bd ", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    public List<String> obtenerAreas() {
        List<String> areas = new ArrayList<>();
        Connection con = conectar();
        String sql = "SELECT areas FROM areas";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	areas.add(rs.getString("areas")); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener las áreas de la bd", "Error", JOptionPane.ERROR_MESSAGE);
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
        return areas;
    }


    
    
}
