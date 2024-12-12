package org.proyecto.proyecto.modelo;

public class WIP {

    private String nombre;
    private String descripcion;
    private String diseñador;
    private int alto;
    private int ancho;
    private String estado;
    private int progreso;

    public WIP(String nombre, String diseñador, int alto, int ancho, String estado) {
        this.nombre = nombre;
        this.diseñador = diseñador;
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

    @Override
    public String toString() {
        return "WIP{" +
                "alto=" + alto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", diseñador='" + diseñador + '\'' +
                ", ancho=" + ancho +
                ", estado='" + estado + '\'' +
                ", progreso=" + progreso +
                '}';
    }
}
