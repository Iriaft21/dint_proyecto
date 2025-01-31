package org.proyecto.proyecto.modelo;

import javafx.scene.image.Image;
import org.proyecto.proyecto.utils.Constantes;
import org.proyecto.proyecto.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Proyecto que representa un proyecto de bordado
 *
 * Contiene métodos para calcular el progreso y para validar errores
 */
public class Proyecto implements DatabaseOperationsProyecto{

    //atributos de la clase
    private String id;
    private String nombre;
    private String descripcion;
    private String diseniador;
    private int alto;
    private int largo;
    private String estado;
    private float progreso;
    private int puntadasTotales;
    private String fechaInicio;
    private String fechaFin;
    private Image imagen;

    /**
     * Constructor para inicializar un objeto Proyecto con sus atributos
     *
     * @param nombre          El nombre del proyecto
     * @param descripcion     La descripción del proyecto
     * @param diseniador      El diseñador del proyecto
     * @param alto            La altura del proyecto
     * @param largo           La longitud del proyecto
     * @param estado          El estado del proyecto
     * @param puntadasTotales El número total de puntadas del proyecto
     * @param fechaInicio     La fecha de inicio del proyecto
     * @param fechaFin        La fecha de fin del proyecto
     * @param imagen          Una imagen del proyecto
     */
    public Proyecto(String nombre, String descripcion, String diseniador, int alto, int largo, String estado, int puntadasTotales, String fechaInicio, String fechaFin, Image imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diseniador = diseniador;
        this.alto = alto;
        this.largo = largo;
        this.estado = estado;
        this.puntadasTotales = puntadasTotales;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imagen = imagen;
    }

    /**
     * Constructor para inicializar un objeto Proyecto con sus atributos
     *
     * @param id              El id del proyecto
     * @param nombre          El nombre del proyecto
     * @param descripcion     La descripción del proyecto
     * @param diseniador      El diseñador del proyecto
     * @param alto            La altura del proyecto
     * @param largo           La longitud del proyecto
     * @param estado          El estado del proyecto
     * @param progreso        El progreso del proyecto
     * @param puntadasTotales El número total de puntadas del proyecto
     * @param fechaInicio     La fecha de inicio del proyecto
     * @param fechaFin        La fecha de fin del proyecto
     */
    public Proyecto(String id,String nombre, String descripcion, String diseniador, int alto, int largo, String estado, float progreso, int puntadasTotales, String fechaInicio, String fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diseniador = diseniador;
        this.alto = alto;
        this.largo = largo;
        this.estado = estado;
        this.progreso = progreso;
        this.puntadasTotales = puntadasTotales;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Constructor para inicializar un objeto Proyecto sin pasarle atributos
     */
    public Proyecto(){
    }

    /**
     * Inserta un nuevo proyecto en la base de datos.
     *
     * @param proyecto El objeto Proyecto que contiene la información del proyecto a insertar.
     * @return true si se insertó correctamente, false en caso contrario.
     */
    @Override
    public boolean insertarProyecto(Proyecto proyecto) {
        // Ejecuta la consulta utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_INSERTAR_PROYECTO.getDescripcion(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getDiseniador(),
                proyecto.getAlto(),
                proyecto.getLargo(),
                proyecto.getEstado(),
                proyecto.getProgreso(),
                proyecto.getPuntadasTotales(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin());
        // Imprime el número de filas insertadas en la consola
        System.out.println(Constantes.FILAS_INSERTADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se insertó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Obtiene todos los proyectos de la base de datos
     *
     * @return Una lista de objetos Proyecto que representan todos los proyectos en la base de datos
     */
    @Override
    public List<Proyecto> obtenerTodosProyectos() {
        // Lista para almacenar los proyectos
        List<Proyecto> proyectos = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            // Abre la conexión
            conn = Utils.conexion();
            // Prepara la declaración
            pstmt = conn.prepareStatement(Constantes.CONSULTA_MOSTRAR_PROYECTOS.getDescripcion());
            // Ejecuta la consulta
            rs = pstmt.executeQuery();
            // Itera sobre el ResultSet para extraer los datos de cada proyecto
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("diseniador"),
                        rs.getInt("alto"),
                        rs.getInt("largo"),
                        rs.getString("estado"),
                        rs.getFloat("progreso"),
                        rs.getInt("puntadasTotales"),
                        rs.getString("fechaInicio"),
                        rs.getString("fechaFin"));
                // Añade el proyecto a la lista
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            // Muestra un mensaje de error en caso de excepción SQL
            System.out.println(Constantes.ERROR_BBDD + e.getMessage());
        } finally {
            // Cierra el ResultSet, PreparedStatement y Connection en el bloque finally para asegurarse de que se cierran
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Devuelve la lista de proyectos
        return proyectos;
    }

    /**
     * Elimina un proyecto de la base de datos
     *
     * @param id El ID del proyecto que se va a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean eliminaProyecto(String id) {
        // Ejecuta la consulta de eliminación utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_BORRAR_PROYECTOS.getDescripcion(), id);
        // Imprime el número de filas eliminadas en la consola
        System.out.println(Constantes.FILAS_ELIMINADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se eliminó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Actualiza un proyecto existente en la base de datos
     *
     * @param proyecto El objeto Proyecto que contiene la información del proyecto a actualizar
     * @return true si se actualizó correctamente, false en caso contrario
     */
    @Override
    public boolean actualizarProyecto(Proyecto proyecto) {
        // Ejecuta la consulta de actualización utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar(Constantes.CONSULTA_ACTUALIZAR_PROYECTO.getDescripcion(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getDiseniador(),
                proyecto.getAlto(),
                proyecto.getLargo(),
                proyecto.getEstado(),
                proyecto.getProgreso(),
                proyecto.getPuntadasTotales(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.getId());
        // Imprime el número de filas modificadas en la consola
        System.out.println(Constantes.FILAS_MODIFICADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se modificó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Actualiza el progreso de un proyecto existente en la base de datos
     *
     * @param id El id de un proyecto
     * @param progreso  El progreso de un proyecto
     * @param estado El estado de un proyecto
     * @return true si se actualizó correctamente, false en caso contrario
     */
    @Override
    public boolean actualizarProgreso(String id, float progreso, String estado) {
        // Ejecuta la consulta de actualización utilizando el método Utils.actualizar
        int filasAfectadas = Utils.actualizar( Constantes.CONSULTA_ACTUALIZAR_PROGRESO.getDescripcion(), estado, progreso, id);
        // Imprime el número de filas modificadas en la consola
        System.out.println(Constantes.FILAS_MODIFICADAS.getDescripcion() + filasAfectadas);
        // Retorna true si se modificó al menos una fila, false en caso contrario
        return filasAfectadas > 0;
    }

    /**
     * Enumeración TipoError que representa los diferentes errores al validar algunos campos
     */
    public enum TipoError {
        ERRORBORDADO, NEGATIVO, SIN_ERROR
    }

    //getters y setters

    /**
     * Obtiene el id del proyecto
     *
     * @return El id del proyecto
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id del proyecto
     *
     * @param id El id del proyecto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el progreso del proyecto
     *
     * @return El progreso del proyecto
     */
    public float getProgreso() {
        return progreso;
    }

    /**
     * Establece el progreso del proyecto
     *
     * @param progreso El progreso del proyecto
     */
    public void setProgreso(float progreso) {
        this.progreso = progreso;
    }

    /**
     * Obtiene la altura del proyecto
     *
     * @return La altura del proyecto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece la altura del proyecto
     *
     * @param alto La altura del proyecto
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Obtiene la longitud del proyecto
     *
     * @return La longitud del proyecto
     */
    public int getLargo() {
        return largo;
    }

    /**
     * Establece la longitud del proyecto
     *
     * @param largo La longitud del proyecto
     */
    public void setLargo(int largo) {
        this.largo = largo;
    }

    /**
     * Obtiene la descripción del proyecto
     *
     * @return La descripción del proyecto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del proyecto
     *
     * @param descripcion La descripción del proyecto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el diseñador del proyecto
     *
     * @return El diseñador del proyecto
     */
    public String getDiseniador() {
        return diseniador;
    }

    /**
     * Establece el diseñador del proyecto
     *
     * @param diseniador El diseñador del proyecto
     */
    public void setDiseniador(String diseniador) {
        this.diseniador = diseniador;
    }

    /**
     * Obtiene el estado del proyecto
     *
     * @return El estado del proyecto
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del proyecto
     *
     * @param estado El estado del proyecto
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el número total de puntadas del proyecto
     *
     * @return El número total de puntadas del proyecto
     */
    public int getPuntadasTotales() {
        return puntadasTotales;
    }

    /**
     * Establece el número total de puntadas del proyecto
     *
     * @param puntadasTotales El número total de puntadas del proyecto
     */
    public void setPuntadasTotales(int puntadasTotales) {
        this.puntadasTotales = puntadasTotales;
    }

    /**
     * Obtiene el nombre del proyecto
     *
     * @return El nombre del proyecto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proyecto
     *
     * @param nombre El nombre del proyecto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de inicio del proyecto
     *
     * @return La fecha de inicio del proyecto
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del proyecto
     *
     * @param fechaInicio La fecha de inicio del proyecto
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin del proyecto
     *
     * @return La fecha de fin del proyecto
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin del proyecto
     *
     * @param fechaFin La fecha de fin del proyecto
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene la imagen del proyecto
     *
     * @return La imagen del proyecto
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen del proyecto
     *
     * @param imagen La imagen del proyecto
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Calcula el progreso del proyecto basado en las puntadas realizadas
     *
     * @param puntadasHechas El número de puntadas realizadas
     * @return El progreso del proyecto
     */
    public float calcularProgreso(int puntadasHechas){
        //se suman las puntadas hechas a las puntadas que ya estaban registradas de antes
        int puntadasRegistradas =+ puntadasHechas;
        //se devuelve el resultado, es decir el porcentaje de progreso
        return ((float) puntadasRegistradas / puntadasTotales) * 100;
    }

    /**
     * Valida los atributos del proyecto
     *
     * @return El tipo de error encontrado al validar
     */
    public Proyecto.TipoError validar(){
        //se verifica que los campos no sean números negativos
        if(alto <= 0 || largo <= 0 || puntadasTotales < 0){
            return Proyecto.TipoError.NEGATIVO;
        //tambien se verifica que las puntas totales no superen al alto por el ancho del bordado
        }else if (puntadasTotales > alto * largo){
            return Proyecto.TipoError.ERRORBORDADO;
        }else{
            return Proyecto.TipoError.SIN_ERROR;
        }
    }

    /**
     * Verifica si los campos nombre, diseñador o descripción están vacíos
     *
     * @return true si alguno de los campos está vacío, de lo contrario false
     */
    public boolean datosVacios(){
        //Verifica que ninguno de esos datos este vacio
        if(nombre.trim().isEmpty() || diseniador.trim().isEmpty() || descripcion.trim().isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Representación en forma de cadena del objeto Proyecto
     *
     * @return Una cadena que describe las características del proyecto
     */
    @Override
    public String toString() {
        return "Nombre del proyecto: " + nombre +
                ", descripcion: " +
                ", diseñador: " +
                ", alto: " + alto +
                ", largo: " + largo +
                ", estado: " + estado +
                ", puntadas totales: " + puntadasTotales +
                ", progreso: " + progreso + "% " +
                ", fecha de inicio: " + (fechaInicio != null? fechaInicio : "Sin especificar" ) +
                ", fecha de fin: " + (fechaFin != null? fechaFin : "Sin especificar" ) +
                ", imagen: " + (imagen!= null? imagen : "Sin imagen");
    }
}
