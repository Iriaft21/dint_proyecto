package org.proyecto.proyecto.utils;


/**
 * Esta clase es un enumerado para albergar las constantes de la aplicación.
 */
public enum Constantes {
    //nombres de los archivos fxml que contienen las distintas pantallas del programa
    PAGINA_INICIAL("menu-view.fxml"),
    PAGINA_PANTALLA_INVENTARIO("inventario-view.fxml"),
    PAGINA_PANTALLA_CALCULADORA("calculadora-view.fxml"),
    PAGINA_PANTALLA_RESULTADO_CALCULADORA("resultadoCalculo-view.fxml"),
    PAGINA_PANTALLA_PROYECTOS("proyectos-view.fxml"),
    PAGINA_PANTALLA_FORMULARIO_PROYECTO("formularioProyecto-view.fxml"),
    PAGINA_PANTALLA_DETALLES_PROYECTO("detallesProyecto-view.fxml"),
    PAGINA_PANTALLA_ACTUALIZAR_PROGRESO("actualizarProgreso-view.fxml"),
    //Titulo de las diferentes pantallas
    TITULO_PAGINA_INICIAL("Menú"),
    TITULO_PANTALLA_INVENTARIO("Inventario"),
    TITULO_PANTALLA_CALCULADORA("Calculadora"),
    TITULO_PANTALLA_RESULTADO_CALCULADORA("Resultados"),
    TITULO_PANTALLA_PROYECTOS("Lista de proyectos"),
    TITULO_PANTALLA_DETALLES_PROYECTO("Detalle del proyecto"),
    TITULO_PANTALLA_FORMULARIO_PROYECTO("Crear nuevo proyecto"),
    TITULO_PANTALLA_ACTUALIZAR_PROGRESO("Actualizar progreso"),
    //Titulo de los diferentes avisos, asi como el texto de los avisos en si
    TITULO_AVISO_DATOS_VACIOS("Datos vacíos"),
    AVISO_DATOS_VACIOS("Alguno de los datos se encuentra vacío, rellene el formulario completo"),
    TITULO_AVISO_ERROR_FORMATO("Error de formato"),
    AVISO_ERROR_FORMATO("La cantidad debe ser un valor/formato válido."),
    TITULO_AVISO_NUMERO_NEGATIVO("Número no válido"),
    AVISO_NUMERO_NEGATIVO("Debes introducir un número entero positivo."),
    TITULO_AVISO_PUNTADAS("Nº de puntadas inválido"),
    AVISO_PUNTADAS("Las puntadas totales no pueden ser mayores al área del bordado."),
    TITULO_AVISO_SIN_HILO("Sin hilo"),
    AVISO_SIN_HILO("Ya no hay más unidades de este hilo. Si aún te hace falta, pasa por una merceria o cómpralo en el siguiente enlace: \n"),
    TITULO_AVISO_DEMASIADAS_UNIDADES("Demasiadas unidades añadidas"),
    AVISO_DEMASIADAS_UNIDADES("Se han añadido demasidas unidades de golpe. Asegúrate de que esté bien y no sobrepase las 99 unidades"),
    TITULO_PROYECTO_CREADO("Proyecto creado"),
    AVISO_PROYECTO_CREADO("Proyecto creado y añadido a la lista. Acuérdese de modificar el progreso en caso de que lo haya empezado."),
    TITULO_PROYECTO_MODIFICADO("Proyecto modificado"),
    AVISO_PROYECTO_MODIFICADO("Proyecto modificado correctamente.");

    private final String descripcion;

    /**
     * Constructor para inicializar la constante con una descripción.
     *
     * @param descripcion La descripción asociada a la constante.
     */
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción de la constante.
     *
     * @return La descripción de la constante.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
