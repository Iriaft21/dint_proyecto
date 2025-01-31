import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestConexionBBDD {

    private static final String NOMBRE = "310";
    private static final String MARCA = "DMC";
    private static final String CANTIDAD = "1";
    private static final String URL = "jdbc:sqlite:data/baseDatos.db";
    private Connection conn;

    // Método que se ejecuta antes de cada prueba: Establecer la conexión a la base de datos
    @BeforeEach
    public void setUp() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error al establecer la conexión antes de la prueba.");
        }
    }

    // Método que se ejecuta después de cada prueba: Comprobar si la fila fue insertada correctamente
    @Test
    public void tearDown() {

        // Inserción del nuevo usuario
        String insertSql = "INSERT INTO hilo (nombre, marca, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, NOMBRE);
            pstmt.setString(2, MARCA);
            pstmt.setString(3, CANTIDAD);
            int filasAfectadas = pstmt.executeUpdate();  // Ejecuta la inserción

            System.out.println("Usuario insertado correctamente. El valor de filasAfectadas es="+filasAfectadas);
            // Verifica si la inserción fue exitosa
            assertTrue(filasAfectadas == 1, "La inserción no afectó exactamente una fila. Filas afectadas: " + filasAfectadas);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error al insertar o verificar el usuario.");
        }
    }

    @Test
    public void tearDown1() {

        // Consulta para obtener todos los usuarios e imprimirlos
        String selectAllSql = "SELECT NOMBRE, MARCA, CANTIDAD FROM hilo";
        try (PreparedStatement pstmt = conn.prepareStatement(selectAllSql)) {
            ResultSet rs = pstmt.executeQuery();

            System.out.println("LISTADO DE HILOS:");

            // Iterar sobre el ResultSet y mostrar los usuarios
            boolean foundHilo = false;
            while (rs.next()) {
                String nombreHilo = rs.getString("NOMBRE");
                String marca = rs.getString("MARCA");
                String cantidad = rs.getString("CANTIDAD");
                System.out.println("Nombre: " + nombreHilo + ", Marca: " + marca + ", Cantidad: " + cantidad);

                // Verifica si el usuario "pepe" está en el listado
                if ("prueba".equals(nombreHilo) && "nuevacontra".equals(marca) &&  "cantidad".equals(cantidad)) {
                    foundHilo = true;
                }
            }

            // Verifica si el usuario "pepe" está en el listado
            //assertTrue(foundPepe, "El usuario 'pepe' no fue encontrado en el listado.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error al insertar o verificar el usuario.");
        }
    }

    @Test
    public void tearDown3() {
        // Realizar una consulta para verificar si la fila fue insertada correctamente
        String selectSql = "SELECT COUNT(*) FROM HILO WHERE NOMBRE = ? AND  MARCA = ? AND CANTIDAD = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setString(1, NOMBRE);
            pstmt.setString(2, MARCA);
            pstmt.setString(3, CANTIDAD);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            assertTrue(count > 0, "El hilo debería haber sido insertado correctamente.");
            System.out.println("Han salido: "+count+" resultados - Debería de aparecer 1 resultado.");

            conn.close();
            System.out.println("Conexión cerrada.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error al cerrar la conexión después de la prueba.");
        }
    }
}
