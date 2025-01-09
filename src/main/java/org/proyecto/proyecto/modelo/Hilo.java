package org.proyecto.proyecto.modelo;

import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;

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

    //TODO poner validaciones aqui
//    private boolean valoresValidos(){
//        return !cantidadInvalida() && !datosVacios() && !formatoCantidadInvalido();
//    }
//
//    private boolean cantidadInvalida(){
//        if(Integer.parseInt(cantidad) <= 0){
//            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_NUMERO_NEGATIVO.getDescripcion(), Constantes.AVISO_NUMERO_NEGATIVO.getDescripcion());
//            return true;
//        }
//        return false;
//    }
//
//    private boolean datosVacios(){
//        if(cantidad == null || cantidad.trim().isEmpty() || numero == null || numero.trim().isEmpty() || marca == null || marca.trim().isEmpty()){
//            AlertaUtils.showAlertInformativa(Constantes.TITULO_AVISO_DATOS_VACIOS.getDescripcion(), Constantes.AVISO_DATOS_VACIOS.getDescripcion());
//            return true;
//        }
//        return false;
//    }
//
//    private boolean formatoCantidadInvalido(){
//        if(!cantidad.matches("\\d+")){
//            AlertaUtils.showAlertError(Constantes.TITULO_AVISO_ERROR_FORMATO.getDescripcion(), Constantes.AVISO_ERROR_FORMATO.getDescripcion());
//           return true;
//        }
//        return false;
//    }

    @Override
    public String toString() {
        return "Hilo numero " + numero + ", marca " + marca + ", cantidad " + cantidad;
    }
}
