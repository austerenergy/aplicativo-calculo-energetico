package br.com.austerenergy.app_eficiencia;

/**
 * Created by Daniel on 09/04/2016.
 */
public class Lampada {

    private String modeloLampada;
    private int quantidade;
    private int horasLigadas;

    public String getModeloLampada() {
        return modeloLampada;
    }

    public void setModeloLampada(String modeloLampada) {
        this.modeloLampada = modeloLampada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getHorasLigadas() {
        return horasLigadas;
    }

    public void setHorasLigadas(int horasLigadas) {
        this.horasLigadas = horasLigadas;
    }

    public String toString()
    {
        return modeloLampada + " \nQuantidade: " + quantidade + " Horas Ligada: " + horasLigadas;
    }
}
