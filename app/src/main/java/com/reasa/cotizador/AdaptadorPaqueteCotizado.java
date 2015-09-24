package com.reasa.cotizador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ruben on 24/09/15.
 */
public class AdaptadorPaqueteCotizado extends ArrayAdapter<Paquete> {

    ArrayList<Paquete> paquetes;
    Context contexto;
    public AdaptadorPaqueteCotizado(Context contexto, ArrayList<Paquete> paquetes){
        super(contexto, -1 , paquetes);
        this.paquetes = paquetes;
        this.contexto = contexto;
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

        alto.setText(paquetes.get(position).getAlto() + "");
        ancho.setText(paquetes.get(position).getAncho() + "");
        profundidad.setText(paquetes.get(position).getProfundidad() + "");
        peso.setText(paquetes.get(position).getPeso() + "");

        return view;
    }
}
