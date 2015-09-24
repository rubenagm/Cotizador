package com.reasa.cotizador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CotizarPaquetes extends AppCompatActivity {

    Button cotizar;
    Button agregarPaquete;
    EditText alto;
    EditText ancho;
    EditText profundidad;
    EditText peso;
    EditText cantidad;
    ArrayList<Paquete> paquetes;
    Context contexto;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar_paquetes);

        cantidad.setText("1");
        contexto = this.getApplicationContext();
        paquetes = new ArrayList<>();
        listView = (ListView) findViewById(R.id.paquetes);

        //ListView
        AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(contexto, paquetes);
        listView.setAdapter(adaptadorPaqueteCotizado);

        //Inicializan los editText
        alto        = (EditText) findViewById(R.id.paquete_alto);
        peso        = (EditText) findViewById(R.id.paquete_peso);
        ancho       = (EditText) findViewById(R.id.paquete_ancho);
        profundidad = (EditText) findViewById(R.id.paquete_profundidad);
        cantidad    = (EditText) findViewById(R.id.paquete_cantidad);

        String viaje       = getIntent()
                                .getExtras()
                                    .getString("Viaje"
                                            , "No se puede reconocer el viaje");
        double precioViaje = getIntent()
                                .getExtras()
                                    .getDouble("Costo", 1.0);

        cotizar = (Button) findViewById(R.id.cotizar_envio);

        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CotizarPaquetes.this, Cotizacion.class);
                startActivity(intent);
            }
        });

        agregarPaquete = (Button) findViewById(R.id.agregar_paquete);

        agregarPaquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int cantidadPaquetes = Integer.parseInt(cantidad.getText().toString());

                    for (int x = 0; x < cantidadPaquetes; x++) {
                        Paquete paquete = new Paquete(
                                Double.parseDouble(alto.getText().toString()),
                                    Double.parseDouble(ancho.getText().toString()),
                                        Double.parseDouble(profundidad.getText().toString()),
                                            Double.parseDouble(peso.getText().toString())
                        );

                        paquetes.add(paquete);
                    }

                    AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(contexto, paquetes);
                    listView.setAdapter(adaptadorPaqueteCotizado);
                    
                } catch (NumberFormatException e) {
                    Toast.makeText(contexto,
                        "Tienes un error al momento de convertir el formato",
                            Toast.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    Toast.makeText(contexto,
                        "Hay un error en el NULL POINTER EXCEPTION",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cotizar_paquetes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
