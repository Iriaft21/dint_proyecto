import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *   Clase de prueba para comprobar la conexión a la base de datos y la manipulación de datos en las tablas "hilo" y "proyecto"
 */
public class TestConexionBBDD {

    private static final String URL = "jdbc:sqlite:data/baseDatosPrueba.db";
    private Connection conn;

    private static final String NOMBRE_HILO = "310";
    private static final String MARCA_HILO = "DMC";
    private static final String CANTIDAD_HILO = "1";

    private static final String NOMBRE_PROYECTO = "PruebaBD";
    private static final String DESCRIPCION_PROYECTO = "Descripción";
    private static final String DISENIADOR_PROYECTO = "Diseñador";
    private static final int ALTO_PROYECTO = 150;
    private static final int LARGO_PROYECTO = 150;
    private static final String ESTADO_PROYECTO = "En proceso";
    private static final Float PROGRESO_PROYECTO = 50.0f;
    private static final int PUNTADAS_TOTALES_PROYECTO = 17500;

    /**
     * Método que se ejecuta antes de cada prueba para establecer la conexión a la base de datos
     *
     * @throws Exception si ocurre un error al establecer la conexión
     */
    @BeforeEach
    public void setUp() {
        try {
            // Intentamos establecer la conexión a la base de datos utilizando la URL proporcionada
            conn = DriverManager.getConnection(URL);
            // Imprimir un mensaje en la consola para confirmar que la conexión se ha establecido correctamente
            System.out.println("Conexión establecida.");
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Fallar la prueba explícitamente en caso de error al establecer la conexión
            Assertions.fail("Error al establecer la conexión antes de la prueba.");
        }
    }

    /**
     * Método de prueba para insertar un hilo en la base de datos y comprobar si la inserción fue exitosa
     *
     * @throws Exception si ocurre un error durante la inserción
     */
    @Test
    public void tearDown() {
        // Consulta de inserción de un nuevo hilo en la tabla
        String insertSql = "INSERT INTO hilo (nombre, marca, cantidad) VALUES (?, ?, ?)";
        // Utilizar PreparedStatement para prevenir inyecciones SQL
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            // Establecemos los valores de los parámetros en la consulta
            pstmt.setString(1, NOMBRE_HILO);
            pstmt.setString(2, MARCA_HILO);
            pstmt.setString(3, CANTIDAD_HILO);
            // Ejecutamos la inserción y obtener el número de filas afectadas
            int filasAfectadas = pstmt.executeUpdate();
            // Imprimimos el resultado en la consola
            System.out.println("Hilo insertado correctamente. El valor de filasAfectadas es="+filasAfectadas);
            // Verificamos si la inserción fue exitosa
            Assertions.assertTrue(filasAfectadas == 1, "La inserción no afectó exactamente una fila. Filas afectadas: " + filasAfectadas);
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcar la prueba como fallida explícitamente en caso de error al insertar o verificar el hilo
            Assertions.fail("Error al insertar o verificar el hilo.");
        }
    }

    /**
     * Método de prueba para comprobar si un hilo específico se encuentra en la base de datos
     *
     * @throws Exception si ocurre un error durante la ejecución de la consulta
     */
    @Test
    public void  tearDown1() {
        // Consulta para obtener todos los hilos e imprimirlos
        String selectAllSql = "SELECT NOMBRE, MARCA, CANTIDAD FROM hilo";
        // Utilizar PreparedStatement para ejecutar la consulta
        try (PreparedStatement pstmt = conn.prepareStatement(selectAllSql)) {
            // Ejecutar la consulta y obtener el ResultSet
            ResultSet rs = pstmt.executeQuery();
            System.out.println("LISTADO DE HILOS:");

            // Iterar sobre el ResultSet y mostrar los hilos
            boolean found310 = false;
            while (rs.next()) {
                // Obtener los valores de las columnas NOMBRE, MARCA y CANTIDAD
                String nombreHilo = rs.getString("NOMBRE");
                String marca = rs.getString("MARCA");
                String cantidad = rs.getString("CANTIDAD");
                // Imprimir los detalles del hilo en la consola
                System.out.println("Nombre: " + nombreHilo + ", Marca: " + marca + ", Cantidad: " + cantidad);

                // Verifica si el hilo 310 de marca DMC está en el listado
                if ("310".equals(nombreHilo) && "DMC".equals(marca)) {
                    found310 = true;
                }
            }
            // Verifica si el el hilo 310 de marca DMC está en el listado
            Assertions.assertTrue(found310, "El hilo 310 de marca DMC no fue encontrado en el listado");
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcar la prueba como fallida explícitamente en caso de error al insertar o verificar el hilo
            Assertions.fail("Error al insertar o verificar el hilo");
        }
    }

    /**
     * Método de prueba para comprobar si un hilo específico se encuentra en la base de datos.
     *
     * @throws Exception si ocurre un error durante la verificación
     */
    @Test
    public void tearDown2() {
        // Realizamos una consulta para verificar si la fila fue insertada correctamente
        String selectSql = "SELECT COUNT(*) FROM HILO WHERE NOMBRE = ? AND  MARCA = ? AND CANTIDAD = ?";
        // Utilizamos PreparedStatement para ejecutar la consulta
        try (PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            // Establecemos los valores de los parámetros en la consulta
            pstmt.setString(1, NOMBRE_HILO);
            pstmt.setString(2, MARCA_HILO);
            pstmt.setString(3, CANTIDAD_HILO);
            // Ejecutamos la consulta y obtenemos el ResultSet
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            // Verificamos que el hilo ha sido insertado correctamente
            Assertions.assertTrue(count > 0, "El hilo debería haber sido insertado correctamente");
            System.out.println("Han salido: "+count+" resultados - Debería de aparecer 1 resultado");
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al insertar o verificar el hilo
            Assertions.fail("Error al cerrar la conexión después de la prueba");
        }
    }

    /**
     * Método de prueba para verificar si la fila del hilo fue insertada correctamente en la base de datos.
     *
     * @throws Exception si ocurre un error durante la eliminación
     */
    @Test
    public void tearDown3(){
        try {
            // Eliminamos el hilo insertado durante la prueba
            String deleteSql = "DELETE FROM hilo WHERE NOMBRE = ? AND MARCA = ? AND CANTIDAD = ?";
            int filasEliminadas = 0;
            // Utilizamos PreparedStatement para ejecutar la consulta de eliminación
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
                pstmt.setString(1, NOMBRE_HILO);
                pstmt.setString(2, MARCA_HILO);
                pstmt.setString(3, CANTIDAD_HILO);
                // Ejecutamos la eliminación y obtenemos el número de filas afectadas
                filasEliminadas = pstmt.executeUpdate();
                // Imprimimos el resultado en la consola
                System.out.println("Hilo eliminado correctamente.");
                Assertions.assertTrue(filasEliminadas > 0, "El hilo debería haber sido insertado correctamente");
                System.out.println("Han salido: "+ filasEliminadas +" resultados - Debería de aparecer 1 resultado");
            }
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al eliminar el hilo
            Assertions.fail("Error al eliminar el hilo");
        }
    }

    /**
     * Inserta un nuevo proyecto en la base de datos y verifica que se haya insertado correctamente
     *
     * @throws SQLException si ocurre un error durante la inserción
     */
    @Test
    public void tearDown4() {
        try {
            // Consulta SQL para insertar un nuevo proyecto
            String sql = "INSERT INTO proyecto (nombre, descripcion, diseniador, alto, largo, estado, progreso, puntadasTotales, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // Utilizamos PreparedStatement para ejecutar la consulta de inserción
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecemos los valores de los parámetros en la consulta
                pstmt.setString(1, NOMBRE_PROYECTO);
                pstmt.setString(2, DESCRIPCION_PROYECTO);
                pstmt.setString(3, DISENIADOR_PROYECTO);
                pstmt.setInt(4, ALTO_PROYECTO);
                pstmt.setInt(5, LARGO_PROYECTO);
                pstmt.setString(6, ESTADO_PROYECTO);
                pstmt.setFloat(7, PROGRESO_PROYECTO);
                pstmt.setInt(8, PUNTADAS_TOTALES_PROYECTO);
                pstmt.setString(9, null);
                pstmt.setString(10, null);
                // Ejecutamos la inserción y obtenemos el número de filas afectadas
                int filasAfectadas = pstmt.executeUpdate();
                // Imprimimos el resultado en la consola
                System.out.println("Filas insertadas: " + filasAfectadas);
                // Verificamos que se haya insertado exactamente una fila
                Assertions.assertEquals(1, filasAfectadas, "Debería haberse insertado una fila");
            }
        } catch (SQLException e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al insertar el proyecto
            Assertions.fail("Error al insertar el proyecto");
        }
    }

    /**
     * Verifica todos los proyectos en la base de datos e imprime sus detalles
     * Verifica si el proyecto "PruebaBD" está en el listado.
     *
     * @throws SQLException si ocurre un error durante la ejecución de la consulta
     */
    @Test
    public void tearDown5() {
        // Consulta SQL para obtener todos los proyectos
        String query = "SELECT * FROM proyecto";
        // Utilizamos PreparedStatement para ejecutar la consulta
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            // Ejecutamos la consulta y obtener el ResultSet
            ResultSet rs = pstmt.executeQuery();
            System.out.println("LISTADO DE PROYECTOS:");

            // Iteramos sobre el ResultSet y mostramos los proyectos
            boolean foundPruebaBD = false;
            while (rs.next()) {
                // Obtenemos los valores de las columnas del ResultSet
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
                // Imprimimos los detalles del proyecto en la consola
                System.out.println("Nombre: " + nombre + ", descripción: " + descripcion + ", diseñador: " + diseniador + ", alto: " + alto +
                        ", largo: " + largo + ", estado: " + estado + ", progreso: " + progreso + ", puntadas totales: " + puntadasTotales + ", fecha de inicio: " +
                        fechaInicio + ", fecha de fin: " + fechaFin);
                // Verificamos si el proyecto con nombre PruebaBD está en el listado
                if ("PruebaBD".equals(nombre)) {
                    foundPruebaBD = true;
                }
            }
            // Verificamos si el proyecto con nombre PruebaBD está en el listado
            Assertions.assertTrue(foundPruebaBD, "El proyecto PruebaBD no fue encontrado en el listado");
        } catch (SQLException e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al insertar o verificar el proyecto
            Assertions.fail("Error al insertar o verificar el proyecto");
        }
    }

    /**
     * Verifica si la fila del proyecto fue insertada correctamente en la base de datos
     *
     * @throws SQLException si ocurre un error durante la ejecución de la consulta
     */
    @Test
    public void tearDown6() {
        // Realizamos una consulta para verificar si la fila fue insertada correctamente
        String selectSql = "SELECT COUNT(*) FROM PROYECTO WHERE NOMBRE = ?";
        // Utilizamos PreparedStatement para ejecutar la consulta
        try (PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            // Establecemos los valores de los parámetros en la consulta
            pstmt.setString(1, NOMBRE_PROYECTO);
            // Ejecutamos la consulta y obtener el ResultSet
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            // Verificamos que el proyecto ha sido insertado correctamente
            Assertions.assertTrue(count > 0, "El proyecto debería haber sido insertado correctamente");
            System.out.println("Han salido: "+count+" resultados - Debería de aparecer 1 resultado");
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al insertar o verificar el proyecto
            Assertions.fail("Error al cerrar la conexión después de la prueba");
        }
    }

    /**
     * Elimina un proyecto de la base de datos y verifica que se haya eliminado correctamente
     *
     * @throws SQLException si ocurre un error durante la ejecución de la consulta
     */
    @Test
    public void tearDown7() {
        try {
            // Consulta SQL para eliminar un proyecto por su nombre
            String sql = "DELETE FROM proyecto WHERE nombre = ?";
            // Utilizamos PreparedStatement para ejecutar la consulta de eliminación
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Establecemos el valor del parámetro en la consulta
                pstmt.setString(1, NOMBRE_PROYECTO);
                // Ejecutamos la eliminación y obtenemos el número de filas afectadas
                int filasAfectadas = pstmt.executeUpdate();
                // Imprimimos el resultado en la consola
                System.out.println("Filas eliminadas: " + filasAfectadas);
                // Verificamos que se haya eliminado al menos una fila
                Assertions.assertTrue(filasAfectadas > 0, "Debería haberse eliminado al menos una fila");
                System.out.println("Han salido: "+ filasAfectadas +" resultados - Debería de aparecer 1 resultado");
            }
        } catch (SQLException e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida en caso de error al eliminar el proyecto
            Assertions.fail("Error al eliminar el proyecto");
        }
    }

    /**
     * Cierra la conexión de la base de datos después de cada prueba.
     *
     * @throws Exception si ocurre un error durante el cierre de la conexión.
     */
    @AfterEach
    public void tearDownAfterEach() {
        try {
            // Cerramos la conexión
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada en @AfterAll");
            }
        } catch (Exception e) {
            // En caso de que haya un fallo, imprimimos la causa
            e.printStackTrace();
            // Marcamos la prueba como fallida explícitamente en caso de error al cerrar la conexión
            Assertions.fail("Error al cerrar la conexión después de la prueba");
        }
    }
}