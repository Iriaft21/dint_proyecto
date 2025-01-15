package org.proyecto.proyecto.modelo;

import org.proyecto.proyecto.utils.AlertaUtils;
import org.proyecto.proyecto.utils.Constantes;

public class CalculoTela {

    private int alto;
    private int largo;
    private float ctTela;
    private int tela_bordes;
    private int tela_acabado;

    public CalculoTela(int alto, int largo, float ctTela, int tela_bordes, int tela_acabado) {
        this.alto = alto;
        this.largo = largo;
        this.ctTela = ctTela;
        this.tela_bordes = tela_bordes;
        this.tela_acabado = tela_acabado;
    }

    public enum TipoError {
        NEGATIVO, SIN_ERROR
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

    public float getCtTela() {
        return ctTela;
    }

    public void setCtTela(float tipoTela) {
        this.ctTela = ctTela;
    }

    public int getTela_bordes() {
        return tela_bordes;
    }

    public void setTela_bordes(int tela_bordes) {
        this.tela_bordes = tela_bordes;
    }

    public int getTela_acabado() {
        return tela_acabado;
    }

    public void setTela_acabado(int tela_acabado) {
        this.tela_acabado = tela_acabado;
    }

    public float tamanhoBordadoAlto(){
        return (alto / ctTela);
    }

    public float tamanhoBordadoLargo(){
        return (largo / ctTela);
    }

    public float tamanhoTelaAlto(){
        return (alto / ctTela) + (tela_bordes * 2) + (tela_acabado * 2) + 3.50f;
    }

    public float tamanhoTelaLargo(){
        return (largo / ctTela) + (tela_bordes * 2) + (tela_acabado * 2) + 3.50f;
    }

    public CalculoTela.TipoError validar(){
        if(alto <= 0 || largo <= 0 || tela_acabado < 0 || ctTela < 0){
            return TipoError.NEGATIVO;
        }else{
            return TipoError.SIN_ERROR;
        }
    }

    @Override
    public String toString() {
        return "Características del bordado y la tela: " +
                "alto " + alto +
                ", largo " + largo +
                ", conteo de la tela " + ctTela +
                ", tela a dejar en los bordes " + tela_bordes +
                ", tela a dejar en el acabado " + tela_acabado;
    }
}