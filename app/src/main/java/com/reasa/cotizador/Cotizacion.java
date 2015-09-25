package com.reasa.cotizador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cotizacion extends AppCompatActivity {

    public Button nuevaCotizacion;
    public ArrayList<Paquete> paquetes;
    public ListView listView;
    public Context context;
    public Double costoViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion);
        context = getApplicationContext();
        getSupportActionBar().hide();

        costoViaje = getIntent().getExtras().getDouble("CostoViaje", 1.0);
        String viaje = getIntent().getExtras().getString("Viaje", "");
        paquetes = new ArrayList<>();

        TextView totalCotizacion = (TextView) findViewById(R.id.total_cotizacion);
        TextView viajeDestino = (TextView) findViewById(R.id.viaje_destino);
        TextView textViewCantidad = (TextView) findViewById(R.id.cantidad_cotizacion);
        viajeDestino.setText("La cotización corresponde de: " + viaje);
        ArrayList<String> nombres = getIntent().getStringArrayListExtra("NombrePaquetes");
        ArrayList<String> alto    = getIntent().getStringArrayListExtra("AltoPaquetes");
        ArrayList<String> ancho   = getIntent().getStringArrayListExtra("AnchoPaquetes");
        ArrayList<String> profundidad = getIntent().getStringArrayListExtra("ProfundidadPaquetes");
        ArrayList<String> peso    = getIntent().getStringArrayListExtra("PesoPaquetes");

        for (int x = 0; x < nombres.size(); x++) {
            paquetes.add(new Paquete(Double.parseDouble(alto.get(x)),
                    Double.parseDouble(ancho.get(x)),
                        Double.parseDouble(profundidad.get(x)),
                            Double.parseDouble(peso.get(x))));
        }

        textViewCantidad.setText("Total de " + paquetes.size() + " paquete/s cotizados");
        listView = (ListView) findViewById(R.id.paquetes_cotizados);
        AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(context, paquetes, costoViaje);

        double total = 0;

        for (Paquete paquete : paquetes) {
            total += paquete.getPrecio(costoViaje);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        totalCotizacion.setText("$" + df.format(total));

        listView.setAdapter(adaptadorPaqueteCotizado);
        nuevaCotizacion = (Button) findViewById(R.id.nueva_cotizacion);

        nuevaCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cotizacion.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Spinner


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cotizacion, menu);
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
