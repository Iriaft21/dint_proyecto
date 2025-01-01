package org.proyecto.proyecto.utils;

/**
 * Esta clase es un enumerado para albergar las constantes de la aplicación.
 */
public enum Constantes {
    PAGINA_INICIAL("menu-view.fxml"),
    PAGINA_PANTALLA_INVENTARIO("inventario-view.fxml"),
    PAGINA_PANTALLA_CALCULADORA("calculadora-view.fxml"),
    PAGINA_PANTALLA_RESULTADO_CALCULADORA("resultadoCalculo-view.fxml"),
    PAGINA_PANTALLA_PROYECTOS("proyectos-view.fxml"),
    TITULO_PAGINA_INICIAL("Menú"),
    TITULO_PANTALLA_INVENTARIO("Inventario"),
    TITULO_PANTALLA_CALCULADORA("Calculadora"),
    TITULO_PANTALLA_RESULTADO_CALCULADORA("Resultados"),
    TITULO_PANTALLA_PROYECTOS("Proyectos"),
    TITULO_AVISO_DATOS_VACIOS("Datos vacíos"),
    AVISO_DATOS_VACIOS("Alguno de los datos se encuentra vacío, rellene el formulario completo"),
    TITULO_AVISO_ERROR_FORMATO("Error de formato"),
    AVISO_ERROR_FORMATO("La cantidad debe ser un valor/formato válido."),
    TITULO_AVISO_NUMERO_NEGATIVO("Número no válido"),
    AVISO_NUMERO_NEGATIVO("Debes introducir un número entero.");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
