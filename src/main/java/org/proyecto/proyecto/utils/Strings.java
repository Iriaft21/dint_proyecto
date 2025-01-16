package org.proyecto.proyecto.utils;

public enum Strings {

    TITULO_PAGINA_INICIAL("Gestor de punto de cruz"),
    BOTON_SALIR("Salir"),
    BOTON_ATRAS("Atrás"),
    TITULO_INVENTARIO("Inventario"),
    BOTON_ADD("Añadir"),
    BOTON_ELIMINAR("Eliminar"),
    LABEL_NOMBRE_HILO("Nombre:"),
    LABEL_CANTIDAD_HILO("Cantidad:"),
    LABEL_MARCA_HILO("Marca:"),
    MENU("Menú"),
    TITULO_CALCULADORA("Calculadora"),
    TITULO_CALCULADORA2("Calculadora de tela"),
    SUBTITULO_DIMENSIONES("Dimensiones del dibujo"),
    BOTON_LIMPIAR_CAMPOS("Limpiar campos"),
    BOTON_CALCULAR("Calcular"),
    LABEL_FORMULARIO1("Alto (en puntadas):"),
    LABEL_FORMULARIO2("Largo(en puntadas):"),
    LABEL_FORMULARIO3("Conteo de la tela:"),
    LABEL_FORMULARIO4("Tela a dejar como borde:"),
    LABEL_FORMULARIO5("Tela  a dejar para acabado:"),
    TITULO_RESULTADO_CALCULO("Resultado"),
    SUBTITULO_RESULTADO1("El tamaño del bordado sería:"),
    SUBTITULO_RESULTADO2("Necesitarás una tela de las siguientes dimensiones:"),
    RESULTADO1("cm de largo y "),
    RESULTADO2(" cm de alto."),
    RESULTADO3(" cm. de largo y  "),
    RESULTADO4(" cm. de alto."),
    TITULO_ACTUALIZAR_PROGRESO("Actualizar progreso"),
    LBL_RESPUESTA("Puntadas nuevas a añadir:"),
    BOTON_ACTUALIZAR("Actualizar"),
    TITULO_PROYECTOS("Proyectos"),
    TITULO_FORMULARIO_PROYECTO("Crear/ modificar proyecto"),
    BOTON_SELECCIONAR_IMAGEN("Seleccionar imagen"),
    BOTON_ADD_PROGRESO("Añadir progreso"),
    LABEL_NOMBRE("Nombre*:"),
    LABEL_DISENIADOR("Diseñador/a:"),
    LABEL_ESTADO("Estado*:"),
    LABEL_PUNTADAS_TOTALES("Puntadas totales*:"),
    LABEL_ALTO("Alto*:"),
    LABEL_LARGO("Largo*:"),
    LABEL_FECHA_INICIO("Fecha inicio:"),
    LABEL_FECHA_FIN("Fecha fin:"),
    LABEL_DESCRIPCION("Descripción:"),
    LABEL_NECECESARIOS("Los campos que contienen un * es necesario cubrirlos."),
    BOTON_CREAR("Crear")


    ;

    private final String descripcion;

    Strings(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
