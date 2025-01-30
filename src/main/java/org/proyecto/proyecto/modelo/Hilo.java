package org.proyecto.proyecto.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Hilo que representa un hilo con su nombre, marca y cantidad
 * Permite validar los datos
 */
public class Hilo implements DatabaseOperationsHilo {

    //atributos de la clase
    private String id;
    private String nombre;
    private String marca;
    private String cantidad;

    /**
     * Constructor para inicializar un objeto Hilo con su nombre, marca y cantidad
     *
     * @param nombre   El nombre del hilo
     * @param marca    La marca del hilo
     * @param cantidad La cantidad del hilo
     */
    public Hilo(String nombre, String marca, String cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public Hilo(String id,String nombre, String marca, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public Hilo() {
    }

    @Override
    public boolean insertarHilo(Hilo hilo) {
        String url = "jdbc:sqlite:data/baseDatos.db";
        String nombreH = hilo.getNombre();
        String marcaH = hilo.getMarca();
        String cantidadH = hilo.getCantidad();

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO hilo (nombre, marca, cantidad) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, marca);
                pstmt.setString(3, cantidad);
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Hilo> obtenerTodosHilos() {
        ArrayList<Hilo> hilos = new ArrayList<>();
        String url = "jdbc:sqlite:data/baseDatos.db";
        String query = "SELECT * FROM hilo ";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Obtener los valores del resultado
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                String cantidad = rs.getString("cantidad");
                // Mostrar los resultados
//                System.out.println("Id del hilo: " + id);
//                System.out.println("nombre: " + nombre);
//                System.out.println("marca: " + marca);
//                System.out.println("cantidad: " + cantidad);
                Hilo hilo = new Hilo(id, nombre, marca, cantidad);
                hilos.add(hilo);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return hilos;
    }

    @Override
    public boolean eliminarHilo(String id) {
        String url = "jdbc:sqlite:data/baseDatos.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "DELETE FROM hilo WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarHilo(String id, String nombre, String nuevoPwd, String cantidad) {
        String url = "jdbc:sqlite:data/baseDatos.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "UPDATE hilo SET nombre = ?, marca = ?, cantidad = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, marca);
                pstmt.setString(3, cantidad);
                pstmt.setString(4, id);
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " + filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Enumeración de los diferentes tipos de errores que pueden surgir al validar la cantidad
     */
    public enum TipoError {
        FORMATO, NEGATIVO, NUMEXCESIVO, SIN_ERROR
    }

    //getter y setters
    /**
     * Obtiene el nombre del hilo
     *
     * @return El nombre del hilo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hilo
     *
     * @param nombre El nombre del hilo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la marca del hilo
     *
     * @return La marca del hilo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del hilo
     *
     * @param marca La marca del hilo
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene la cantidad del hilo
     *
     * @return La cantidad del hilo
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del hilo
     *
     * @param cantidad La cantidad del hilo
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica si alguno de los datos del hilo está vacío.
     *
     * @return true si algún dato está vacío, de lo contrario false.
     */
    public boolean datosVacios(){
        if(cantidad == null || cantidad.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || marca == null || marca.trim().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Valida la cantidad del hilo.
     *
     * @return El tipo de error encontrado al validar la cantidad.
     */
    public TipoError validarCantidad() {
        //verifica que la cantidad tenga cierto formato
        if(!cantidad.matches("-?\\d+")){
            return TipoError.FORMATO;
        }else{
            int intCantidad = Integer.parseInt(cantidad);
            //verifica que la cantidad no sea un numero negativo
            if( intCantidad< 0){
                return TipoError.NEGATIVO;
                //Tampoco que sea un numero demasiado exagerado
            }else if(intCantidad >= 100){
                return TipoError.NUMEXCESIVO;
            }else{
                return TipoError.SIN_ERROR;
            }
        }
    }

    /**
     * Representación en forma de cadena del objeto Hilo.
     *
     * @return Una cadena que describe el hilo.
     */
    @Override
    public String toString() {
        return "Hilo numero " + nombre + ", marca " + marca + ", cantidad " + cantidad;
    }
}
