package org.proyecto.proyecto.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarProyecto {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:data/baseDatos.db";
        String nombre = "nombrePrueba";
        String descripcion = null;
        String diseniador = "SASticth";
        int alto = 154;
        int largo = 151;
        String estado = "Reuniendo materiales";
        float progreso = 0.0f;
        int puntadasTotales = 17500;
        String fechaInicio = null;
        String fechaFin = null;

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO proyecto (nombre, descripcion, diseniador, alto, largo, estado, progreso, puntadasTotales, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, descripcion);
                pstmt.setString(3, diseniador);
                pstmt.setInt(4, alto);
                pstmt.setInt(5, largo);
                pstmt.setString(6, estado);
                pstmt.setFloat(7, progreso);
                pstmt.setInt(8, puntadasTotales);
                pstmt.setString(9, fechaInicio);
                pstmt.setString(10, fechaFin);
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
