package org.proyecto.proyecto.prueba;

import java.sql.*;

public class selectProyecto {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:data/baseDatos.db";
        String query = "SELECT * FROM proyecto";

        // Conexión a la base de datos y ejecución de la consulta
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Verificar si se encontraron registros
            while (rs.next()) {
                // Obtener los valores del resultado
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String diseniador = rs.getString("diseniador");
                int alto = rs.getInt("alto");
                int largo = rs.getInt("largo");
                String estado = rs.getString("estado");
                float progreso = rs.getFloat("progreso");
                int puntadasTotales = rs.getInt("puntadasTotales");
                String fechaInicio = rs.getString("fechaInicio");
                String fechaFin = rs.getString("fechaFin");

                // Mostrar los resultados
                System.out.println("Id del proyecto: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Descripción: " + descripcion);
                System.out.println("Diseñador: " + diseniador);
                System.out.println("Alto: " + alto);
                System.out.println("Largo: " + largo);
                System.out.println("Estado: " + estado);
                System.out.println("Progreso: " + progreso +"%");
                System.out.println("Puntadas Totales: " + puntadasTotales);
                System.out.println("Fecha de Inicio: " + fechaInicio);
                System.out.println("Fecha de Fin: " + fechaFin);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
