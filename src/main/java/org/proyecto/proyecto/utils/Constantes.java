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
    AVISO_PROYECTO_MODIFICADO("Proyecto modificado correctamente."),
    URL_BBDD("jdbc:sqlite:data/baseDatos.db"),
    DESCRIPCION_VACIA("Sin descripción"),
    DISENIADOR_VACIO("No especificado"),
    ESTADO_PROYECTO_REUNIENDO("Reuniendo materiales"),
    ESTADO_PROYECTO_REUNIDOS("Materiales reunidos"),
    ESTADO_PROYECTO_EN_PROCESO("En proceso"),
    ESTADO_PROYECTO_COMPLETADO("Completado"),
    IMG_CANDADO("src/main/resources/imagenesProyecto/candado.png"),
    ARCHIVO_CSS("src/main/java/css/application.css"),
    STRING_FILE("file:///"),
    FORMATO_FECHA("yyyy-MM-dd"),
    STRING_NULL("null"),
    STRING_REDONDEAR("%.2f"),
    LABEL_NOMBRE("Nombre: "),
    LABEL_ESTADO("Estado: "),
    LABEL_PROGRESO("Progreso: "),
    LABEL_PROGRESO_PORCENTAJE("%"),
    URL_INVALIDA("Invalid URL: "),
    MARCA_DMC("DMC"),
    MARCA_ANCHOR("Anchor"),
    MARCA_KREINIK("Kreinik"),
    MARCA_MADEIRA("Madeira"),
    MARCA_OTROS("Otros"),
    TITULO_HIPERVINCULO("Comprar hilos"),
    URL_HIPERVINCULO("https://www.casacenina.es/hilos-y-hilados.html"),
    PATRON_EXTRAER_HILOS("\\((\\d+,?\\d*) hilos/cm\\)"),
    CT_11("11 ct (4,2 hilos/cm)"),
    CT_12("12 ct (4,6 hilos/cm)"),
    CT_14("14 ct (5,4 hilos/cm)"),
    CT_16("16ct (6,2 hilos/cm)"),
    CT_18("18 ct (7 hilos/cm)"),
    CT_20("20 ct (7,8 hilos/cm)"),
    CT_22("22 ct (8,5 hilos/cm)"),
    CT_24("24 ct (9,3 hilos/cm)"),
    TITULO_FILE_CHOOSER("Seleccionar imagen"),
    FILTRO_FILE_CHOOSER("Archivos de Imagen"),
    TIPO_ARCHIVO1_FILE_CHOOSER("*.png"),
    TIPO_ARCHIVO12_FILE_CHOOSER("*.jpg"),
    TIPO_ARCHIVO3_FILE_CHOOSER("*.jpeg"),
    THROW_CAMPOS_VACIOS("El campo no puede estar vacío"),
    THROW_SOLO_NUMEROS("El campo debe contener solo números"),
    PATRON_VALIDAR("-?\\d+"),
    CONSULTA_INSERTAR_HILO("INSERT INTO hilo (nombre, marca, cantidad) VALUES (?, ?, ?)"),
    CONSULTA_MOSTRAR_HILOS( "SELECT * FROM hilo"),
    ERROR_BBDD("Error al conectar con la base de datos: "),
    CONSULTA_BORRAR_HILO("DELETE FROM hilo WHERE id = ?"),
    CONSULTA_ACTUALIZAR_HILO("UPDATE hilo SET nombre = ?, marca = ?, cantidad = ? WHERE id = ?"),
    FILAS_INSERTADAS("Filas insertadas: "),
    FILAS_ELIMINADAS("Filas eliminadas: "),
    FILAS_MODIFICADAS("Filas actualizadas: "),
    PATRON_VALIDAR_HILO("-?\\d+"),
    CONSULTA_INSERTAR_PROYECTO("INSERT INTO proyecto (nombre, descripcion, diseniador, alto, largo, estado, progreso, puntadasTotales, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    CONSULTA_MOSTRAR_PROYECTOS("SELECT * FROM proyecto"),
    CONSULTA_BORRAR_PROYECTOS("DELETE FROM proyecto WHERE id = ?"),
    CONSULTA_ACTUALIZAR_PROYECTO("UPDATE proyecto SET nombre = ?, descripcion = ?, diseniador = ?, alto = ?, largo = ?, estado = ?, progreso = ?, puntadasTotales = ?, fechaInicio = ?, fechaFin = ? WHERE id = ?"),
    CONSULTA_ACTUALIZAR_PROGRESO("UPDATE proyecto SET estado = ?, progreso = ? WHERE id = ?");

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
