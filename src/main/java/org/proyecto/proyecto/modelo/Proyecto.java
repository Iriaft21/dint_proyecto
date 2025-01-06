package org.proyecto.proyecto.modelo;

import javafx.scene.image.Image;

public class Proyecto {

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

    public float getProgreso() {
        return progreso;
    }

    public void setProgreso(float progreso) {
        this.progreso = progreso;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int ancho) {
        this.largo = ancho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiseniador() {
        return diseniador;
    }

    public void setDiseniador(String diseñador) {
        this.diseniador = diseniador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPuntadasTotales() {
        return puntadasTotales;
    }

    public void setPuntadasTotales(int puntadasTotales) {
        this.puntadasTotales = puntadasTotales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public float calcularProgreso(int puntadasHechas){
        int puntadasRegistradas =+ puntadasHechas;
        return ((float) puntadasRegistradas / puntadasTotales) * 100;
    }

    @Override
    public String toString() {
        return "Nombre del proyecto: " + nombre +
                ", descripcion: " + (descripcion != null? descripcion : "Sin descripción") +
                ", diseñador: " + (diseniador != null? diseniador : "Desconocido") +
                ", alto: " + alto +
                ", largo: " + largo +
                ", estado: " + estado +
                ", puntadas totales" + puntadasTotales +
                ", progreso: " + progreso + "% " +
                ", fecha de inicio: " + (fechaInicio != null? fechaInicio : "Sin especificar" ) +
                ", fecha de fin: " + (fechaFin != null? fechaFin : "Sin especificar" ) +
                ", imagen: " + (imagen!= null? imagen : "Sin imagen");
    }
}
