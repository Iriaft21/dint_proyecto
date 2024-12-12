package org.proyecto.proyecto.modelo;

public class Hilo {

    private String numero;
    private String marca;
    private String cantidad;

    public Hilo(String numero, String marca, String cantidad) {
        this.numero = numero;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Hilo numero " + numero + ", marca " + marca + ", cantidad " + cantidad;
    }
}
