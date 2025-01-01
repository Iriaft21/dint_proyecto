package org.proyecto.proyecto.modelo;

import javafx.scene.image.Image;

public class Proyecto {

    private String nombre;
    private String descripcion;
    private String diseñador;
    private int alto;
    private int ancho;
    private String estado;
    private int progreso;
    private String fechaInicio;
    private String fechaFin;
    private Image imagen;

    public Proyecto(String nombre, int alto, int ancho, String estado) {
        this.nombre = nombre;
        this.alto = alto;
        this.ancho = ancho;
        this.estado = estado;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiseñador() {
        return diseñador;
    }

    public void setDiseñador(String diseñador) {
        this.diseñador = diseñador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Proyecto: " +
                "nombre " + nombre + '\'' +
                ", descripcion " + descripcion + '\'' +
                ", diseñador " + diseñador + '\'' +
                ", alto " + alto +
                ", ancho " + ancho +
                ", estado " + estado + '\'' +
                ", progreso " + progreso +
                ", fecha de inicio " + fechaInicio + '\'' +
                ", fecha de fin " + fechaFin + '\'' +
                ", imagen " + imagen;
    }
}
