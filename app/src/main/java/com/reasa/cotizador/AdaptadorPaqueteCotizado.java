package com.reasa.cotizador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ruben on 24/09/15.
 */
public class AdaptadorPaqueteCotizado extends ArrayAdapter<Paquete> {

    public ArrayList<Paquete> paquetes;
    public Context contexto;
    public double costoViaje;

    public AdaptadorPaqueteCotizado(Context contexto, ArrayList<Paquete> paquetes, double costoViaje){
        super(contexto, -1 , paquetes);
        this.paquetes   = paquetes;
        this.costoViaje = costoViaje;
        this.contexto   = contexto;
    }

    @Override
    public int getCount() {
        return paquetes.size();
    }

    @Override
    public Paquete getItem(int position) {
        return paquetes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) contexto.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.item_paquete_cotizado, parent, false);

        TextView alto        = (TextView) view.findViewById(R.id.alto);
        TextView ancho       = (TextView) view.findViewById(R.id.ancho);
        TextView profundidad = (TextView) view.findViewById(R.id.profundidad);
        TextView peso        = (TextView) view.findViewById(R.id.peso);
        TextView nombre      = (TextView) view.findViewById(R.id.nombre_paquete);
        TextView numeroPaquete = (TextView) view.findViewById(R.id.numero_paquete);

        DecimalFormat df = new DecimalFormat("#.00");
        alto.setText("Alto: " + df.format(paquetes.get(position).getAlto()) + " cm.");
        ancho.setText("Ancho: " + df.format(paquetes.get(position).getAncho()) + " cm.");
        profundidad.setText("Profundidad: " + df.format(paquetes.get(position).getProfundidad()) + " cm.");
        peso.setText("Peso: " + df.format(paquetes.get(position).getPeso()) + " kg.");
        nombre.setText("$" + df.format(paquetes.get(position).getPrecio(costoViaje)));
        numeroPaquete.setText("Paquete: " + position + 1);
        return view;
    }
}
