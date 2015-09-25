package com.reasa.cotizador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ruben on 25/09/15.
 */
public class AdapterSpinner extends ArrayAdapter<String> {

    Context contexto;
    String[] viajes;

    public AdapterSpinner(Context contexto, String[] viajes) {
        super(contexto, -1, viajes);

        this.contexto = contexto;
        this.viajes   = viajes;
    }

    @Override
    public int getCount() {
        return viajes.length;
    }

    @Override
    public String getItem(int position) {
        return viajes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) contexto.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.item_spinner, parent, false);

        TextView nombre = (TextView) view.findViewById(R.id.nombre_spinner);

        nombre.setText(viajes[position]);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = (LayoutInflater) contexto.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_spinner, parent, false);
        }
        //put the data in it
        String item = viajes[position];
        if(item != null)
        {
            TextView text1 = (TextView) row.findViewById(R.id.nombre_spinner);
            text1.setText(item);
        }

        return row;
    }
}
