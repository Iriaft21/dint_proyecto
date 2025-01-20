package org.proyecto.proyecto.modelo;

/**
 * Clase Hilo que representa un hilo con su nombre, marca y cantidad
 * Permite validar los datos
 */
public class Hilo {

    //atributos de la clase
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
        if(!cantidad.matches("-?\\d+")){
            return TipoError.FORMATO;
        }else{
            int intCantidad = Integer.parseInt(cantidad);
            if( intCantidad<= 0){
                return TipoError.NEGATIVO;
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
