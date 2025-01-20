package org.proyecto.proyecto.modelo;

/**
 * Clase CalculoTela que representa los distintos valores de una tela
 * que hacen falta para realizar un calculo
 *
 * Contiene métodos para validar y calcular las dimesiones del bordado y la tela
 */
public class CalculoTela {

    //atributos de la clase
    private int alto;
    private int largo;
    private float ctTela;
    private int tela_bordes;
    private int tela_acabado;

    /**
     * Constructor para inicializar un objeto CalculoTela con atributos
     *
     * @param alto        La altura del bordado
     * @param largo       El largo del bordado
     * @param ctTela      El conteo de la tela
     * @param tela_bordes Cuanta tela se desea dejar como borde
     * @param tela_acabado Cuanta tela se desea dejar como acabado
     */
    public CalculoTela(int alto, int largo, float ctTela, int tela_bordes, int tela_acabado) {
        this.alto = alto;
        this.largo = largo;
        this.ctTela = ctTela;
        this.tela_bordes = tela_bordes;
        this.tela_acabado = tela_acabado;
    }

    //getters y setters
    /**
     * Enumeración TipoError que representa los diferentes errores que pueden surgir al validar las dimensiones de la tela
     */
    public enum TipoError {
        NEGATIVO, SIN_ERROR
    }


    /**
     * Obtiene la altura del bordado
     *
     * @return La altura del bordado
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece la altura del bordado
     *
     * @param alto La altura del bordado
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Obtiene el largo del bordado
     *
     * @return El largo del bordado
     */
    public int getLargo() {
        return largo;
    }

    /**
     * Establece el largo del bordado
     *
     * @param largo El largo del bordado
     */
    public void setLargo(int largo) {
        this.largo = largo;
    }

    /**
     * Obtiene el conteo de la tela
     *
     * @return El conteo de la tela
     */
    public float getCtTela() {
        return ctTela;
    }

    /**
     * Establece el conteo de la tela
     *
     * @param ctTela El conteo de la tela
     */
    public void setCtTela(float ctTela) {
        this.ctTela = ctTela;
    }

    /**
     * Obtiene el tamaño de la tela a dejar en los bordes
     *
     * @return El tamaño de la tela a dejar en los bordes
     */
    public int getTela_bordes() {
        return tela_bordes;
    }

    /**
     * Establece el tamaño de la tela a dejar en los bordes
     *
     * @param tela_bordes El tamaño de la tela a dejar en los bordes
     */
    public void setTela_bordes(int tela_bordes) {
        this.tela_bordes = tela_bordes;
    }

    /**
     * Obtiene el tamaño de la tela a dejar en el acabado
     *
     * @return El tamaño de la tela a dejar en el acabado
     */
    public int getTela_acabado() {
        return tela_acabado;
    }

    /**
     * Establece el tamaño de la tela a dejar en el acabado
     *
     * @param tela_acabado El tamaño de la tela a dejar en el acabado
     */
    public void setTela_acabado(int tela_acabado) {
        this.tela_acabado = tela_acabado;
    }

    //metodos relacionados con los calculos de la tela
    /**
     * Calcula el tamaño del bordado en altura
     *
     * @return El tamaño del bordado en altura
     */
    public float tamanhoBordadoAlto(){
        return (alto / ctTela);
    }

    /**
     * Calcula el tamaño del bordado en largo
     *
     * @return El tamaño del bordado en largo
     */
    public float tamanhoBordadoLargo(){
        return (largo / ctTela);
    }

    /**
     * Calcula el tamaño total de la tela en altura, incluyendo bordes y acabado
     *
     * @return El tamaño total de la tela en altura.
     */
    public float tamanhoTelaAlto(){
        return (alto / ctTela) + (tela_bordes * 2) + (tela_acabado * 2) + 3.50f;
    }

    /**
     * Calcula el tamaño total de la tela en largo, incluyendo bordes y acabado
     *
     * @return El tamaño total de la tela en largo.
     */
    public float tamanhoTelaLargo(){
        return (largo / ctTela) + (tela_bordes * 2) + (tela_acabado * 2) + 3.50f;
    }

    /**
     * Valida los parámetros
     *
     * @return El tipo de error encontrado al validar
     */
    public CalculoTela.TipoError validar(){
        // Verifica si alguno de los valores es negativo o cero.
        if(alto <= 0 || largo <= 0 || tela_acabado < 0 || ctTela < 0){
            return TipoError.NEGATIVO;
        }else{
            return TipoError.SIN_ERROR;
        }
    }

    /**
     * Representación en forma de cadena del objeto CalculoTela.
     *
     * @return Una cadena que describe las características del bordado y la tela.
     */
    @Override
    public String toString() {
        return "Características del bordado y la tela: " +
                "alto " + alto +
                ", largo " + largo +
                ", conteo de la tela " + ctTela +
                ", tela a dejar en los bordes " + tela_bordes +
                ", tela a dejar en el acabado " + tela_acabado;
    }
}