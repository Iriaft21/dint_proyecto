package org.proyecto.proyecto.prueba;

import java.sql.*;

public class selectHilos {

    public static void main(String[] args) {
        //Connection conn = ConexionSQLite.conectar();
        String url = "jdbc:sqlite:data/baseDatos.db";
        String query = "SELECT * FROM hilo ";
        // Conexión a la base de datos y ejecución de la consulta
        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();
            // Verificar si el usuario fue encontrado
            while (rs.next()) {
                // Obtener los valores del resultado
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                String cantidad = rs.getString("cantidad");
                // Mostrar los resultados
                System.out.println("Id del hilo: " + id);
                System.out.println("nombre: " + nombre);
                System.out.println("marca: " + marca);
                System.out.println("cantidad: " + cantidad);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
