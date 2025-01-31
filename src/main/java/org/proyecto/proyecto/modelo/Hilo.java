package org.proyecto.proyecto.modelo;

import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.Utils;

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

    /**
     * Constructor para inicializar un objeto Hilo con su id, nombre, marca y cantidad
     *
     * @param id       El id del hilo
     * @param nombre   El nombre del hilo
     * @param marca    La marca del hilo
     * @param cantidad La cantidad del hilo
     */
    public Hilo(String id,String nombre, String marca, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    /**
     * Constructor para inicializar un objeto Hilo sin pasarle atributos
     */
    public Hilo() {
    }

    /**
     * Inserta un nuevo hilo en la base de datos.
     *
     * @param hilo El objeto Hilo que contiene la información del hilo a insertar
     * @return true si se insertó correctamente, false en caso contrario
     */
    @Override
    public boolean insertarHilo(Hilo hilo) {
        // Ejecuta la consulta utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_INSERTAR_HILO.getDescripcion(),
                hilo.getNombre(),
                hilo.getMarca(),
                hilo.getCantidad());
        // Imprime el número de filas insertadas en la consola
        System.out.println(Constantes.FILAS_INSERTADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se insertó al menos una fila; false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Obtiene todos los hilos de la base de datos
     *
     * @return Una lista que contiene todos los hilos en la base de datos
     */
    @Override
    public List<Hilo> obtenerTodosHilos() {
        // Lista para almacenar los hilos
        List<Hilo> hilos = new ArrayList<>();
        // Ejecuta la consulta y obtiene el ResultSet
        try (ResultSet rs = Utils.ejecutarQuery(Constantes.CONSULTA_MOSTRAR_HILOS.getDescripcion())) {
            while (rs.next()) {
                // Crea un nuevo objeto Hilo con los datos obtenidos
                Hilo hilo = new Hilo(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("cantidad"));
                // Añade el hilo a la lista
                hilos.add(hilo);
            }
        } catch (SQLException e) {
            // Muestra un mensaje de error en caso de excepción SQL
            System.out.println(Constantes.ERROR_BBDD.getDescripcion() + e.getMessage());
        }
        // Retorna la lista de hilos
        return hilos;
    }

    /**
     * Elimina un hilo de la base de datos
     *
     * @param id El ID del hilo que se va a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean eliminarHilo(String id) {
        // Ejecuta la consulta de eliminación utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_BORRAR_HILO.getDescripcion(), id);
        // Imprime el número de filas eliminadas en la consola
        System.out.println(Constantes.FILAS_ELIMINADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se eliminó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Actualiza un hilo de la base de datos
     *
     * @param id El ID del hilo que se va a modificar
     * @param nombre El nuevo nombre del hilo
     * @param marca La nueva marca del hilo
     * @param cantidad La nueva cantidad del hilo
     * @return true si se modificó correctamente, false en caso contrario
     */
    @Override
    public boolean actualizarHilo(String id, String nombre, String marca, String cantidad) {
        // Ejecuta la consulta de modificación utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_ACTUALIZAR_HILO.getDescripcion(), nombre, marca, cantidad, id);
        // Imprime el número de filas modificadas en la consola
        System.out.println(Constantes.FILAS_MODIFICADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se modificó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
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

    /**
     * Obtiene el id del hilo
     *
     * @return El id del hilo
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del hilo
     *
     * @param id El id del hilo
     */
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
        if(!cantidad.matches(Constantes.PATRON_VALIDAR_HILO.getDescripcion())){
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
