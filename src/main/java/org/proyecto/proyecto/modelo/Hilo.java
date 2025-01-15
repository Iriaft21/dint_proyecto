package org.proyecto.proyecto.modelo;

import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;

public class Hilo {

    private String nombre;
    private String marca;
    private String cantidad;

    public Hilo(String nombre, String marca, String cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public enum TipoError {
        FORMATO, NEGATIVO, NUMEXCESIVO, SIN_ERROR
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean datosVacios(){
        if(cantidad == null || cantidad.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || marca == null || marca.trim().isEmpty()){
            return true;
        }
        return false;
    }

    public TipoError validarCantidad() {
        if(!cantidad.matches("-?\\d+")){
            return TipoError.FORMATO;
        }else{
            int intCantidad = Integer.parseInt(cantidad);
            if( intCantidad<= 0){
                return TipoError.NEGATIVO;
            }else if(intCantidad >= 100){
                return TipoError.NUMEXCESIVO;
            }else{
                return TipoError.SIN_ERROR;
            }
        }
    }

    @Override
    public String toString() {
        return "Hilo numero " + nombre + ", marca " + marca + ", cantidad " + cantidad;
    }
}
