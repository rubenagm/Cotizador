package com.reasa.cotizador;

/**
 * Created by ruben on 24/09/15.
 */
public class Paquete {

    private double alto; //cm.
    private double ancho; //cm.
    private double profundidad; //cm.
    private double peso; //Kg.
    private double pesoVolumetrico;

    public Paquete(){

    }

    public Paquete(double alto, double ancho, double profundidad, double peso){
        this.alto       = alto;
        this.ancho       = ancho;
        this.profundidad = profundidad;
        this.peso        = peso;
    }

    public double getPrecio(){


        return 1.0;
    }

    public void setAlto(double alto){
        this.alto = alto;
    }

    public void setAncho(double ancho){
        this.ancho = ancho;
    }

    public void setProfundidad(double profundidad){
        this.profundidad = profundidad;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public double getAlto(){
        return alto;
    }

    public double getAncho(){
        return ancho;
    }

    public double getProfundidad(){
        return profundidad;
    }

    public double getPeso(){
        return peso;
    }

    public void setPesoVolumetrico(double pesoVolumetrico){
        this.pesoVolumetrico = pesoVolumetrico;
    }

    public double getPesoVolumetrico(){
        return pesoVolumetrico;
    }
}
