package org.proyecto.proyecto.modelo;

import javafx.scene.image.Image;

import java.sql.*;
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

    public Proyecto(){
    }

    @Override
    public boolean insertarProyecto(Proyecto proyecto) {
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
        return false;
    }

    @Override
    public List<Proyecto> obtenerTodosProyectos() {
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

                Proyecto proyecto = new Proyecto(id, nombre, descripcion, diseniador, alto, largo, estado, progreso, puntadasTotales, fechaInicio, fechaFin);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public boolean eliminaProyecto(String id) {
        return false;
    }

    @Override
    public boolean actualizarProyecto() {
        return false;
    }

    @Override
    public boolean actualizarProgreso() {
        return false;
    }

    /**
     * Enumeración TipoError que representa los diferentes errores al validar algunos campos
     */
    public enum TipoError {
        ERRORBORDADO, NEGATIVO, SIN_ERROR
    }

    //getters y setters

    public String getId() {
        return id;
    }

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
