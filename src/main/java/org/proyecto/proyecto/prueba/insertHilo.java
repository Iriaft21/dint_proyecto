package org.proyecto.proyecto.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertHilo {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:data/baseDatos.db"; //Nombre del archivo de la base de datos SQLite
        String nombre = "310";
        String marca = "DMC";
        String cantidad = "1";
        // Conectar a la base de datos y realizar la inserción
        try (Connection conn = DriverManager.getConnection(url)) {
            // Sentencia SQL para insertar una nueva fila
            String sql = "INSERT INTO hilo (nombre, marca, cantidad) VALUES (?, ?, ?)";
            // Preparar la sentencia con los valores
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, marca);
                pstmt.setString(3, cantidad);
                // Ejecutar la inserción
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
