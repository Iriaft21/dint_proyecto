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
    PAGINA_PANTALLA_FORMULARIO_PROYECTO("formularioProyecto-view.fxml"),
    PAGINA_PANTALLA_DETALLES_PROYECTO("detallesProyecto-view.fxml"),
    PAGINA_PANTALLA_ACTUALIZAR_PROGRESO("actualizarProgreso-view.fxml"),
    TITULO_PAGINA_INICIAL("Menú"),
    TITULO_PANTALLA_INVENTARIO("Inventario"),
    TITULO_PANTALLA_CALCULADORA("Calculadora"),
    TITULO_PANTALLA_RESULTADO_CALCULADORA("Resultados"),
    TITULO_PANTALLA_PROYECTOS("Lista de proyectos"),
    TITULO_PANTALLA_DETALLES_PROYECTO("Detalle del proyecto"),
    TITULO_PANTALLA_FORMULARIO_PROYECTO("Crear nuevo proyecto"),
    TITULO_PANTALLA_ACTUALIZAR_PROGRESO("Actualizar progreso"),
    TITULO_AVISO_DATOS_VACIOS("Datos vacíos"),
    AVISO_DATOS_VACIOS("Alguno de los datos se encuentra vacío, rellene el formulario completo"),
    TITULO_AVISO_ERROR_FORMATO("Error de formato"),
    AVISO_ERROR_FORMATO("La cantidad debe ser un valor/formato válido."),
    TITULO_AVISO_NUMERO_NEGATIVO("Número no válido"),
    AVISO_NUMERO_NEGATIVO("Debes introducir un número entero."),
    TITULO_AVISO_SIN_HILO("Sin hilo"),
    // TODO mirar lo del hipervinculo mas a fondo
    AVISO_SIN_HILO("Ya no hay más unidades de este hilo. Si aún te hace falta, pasa por una merceria o cómpralo en el siguiente enlace: \n" +
            "https://www.casacenina.es/hilos-y-hilados.html "),
    TITULO_PROYECTO_CREADO("Proyecto creado"),
    AVISO_PROYECTO_CREADO("Proyecto creado y añadido a la lista. Acuérdese de modificar el progreso en caso de que lo haya empezado.");
//    TITULO_ERROR_COPIAR_IMAGEN("Error"),
//    AVISO_ERROR_COPIAR_IMAGEN("Ha ocurrido un error al haber intentado copiar la imagen ");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
