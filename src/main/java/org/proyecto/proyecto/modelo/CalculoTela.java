package org.proyecto.proyecto.modelo;

public class CalculoTela {

    private int alto;
    private int ancho;
    private float tipoTela;
    private int tela_bordes;
    private int tela_acabado;

    public CalculoTela(int alto, int ancho, float tipoTela, int tela_bordes, int tela_acabado) {
        this.alto = alto;
        this.ancho = ancho;
        this.tipoTela = tipoTela;
        this.tela_bordes = tela_bordes;
        this.tela_acabado = tela_acabado;
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

    public float getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(float tipoTela) {
        this.tipoTela = tipoTela;
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

    @Override
    public String toString() {
        return "CalculoTela{" +
                "alto=" + alto +
                ", ancho=" + ancho +
                ", tipoTela=" + tipoTela +
                ", tela_bordes=" + tela_bordes +
                ", tela_acabado=" + tela_acabado +
                '}';
    }
}
