package com.reasa.cotizador;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ruben on 25/09/15.
 */
public class PasoParametros {
    public ArrayList<String> cotizacion;
    public ArrayList<String> alto;
    public ArrayList<String> ancho;
    public ArrayList<String> profundidad;
    public ArrayList<String> peso;

    public PasoParametros(){
        cotizacion  = new ArrayList<>();
        alto        = new ArrayList<>();
        ancho       = new ArrayList<>();
        profundidad = new ArrayList<>();
        peso        = new ArrayList<>();
    }

    public void agregarDatos(String cotizacion,
                                String alto,
                                     String ancho,
                                        String profundidad,
                                            String peso){
        this.cotizacion.add(cotizacion);
        this.alto.add(alto);
        this.ancho.add(ancho);
        this.profundidad.add(profundidad);
        this.peso.add(peso);
    }

    public ArrayList<String> getCotizacion(){
        return cotizacion;
    }

    public ArrayList<String> getAlto(){
        return alto;
    }

    public ArrayList<String> getAncho(){
        return ancho;
    }

    public ArrayList<String> getProfundidad(){
        return profundidad;
    }

    public ArrayList<String> getPeso(){
        return peso;
    }
}
