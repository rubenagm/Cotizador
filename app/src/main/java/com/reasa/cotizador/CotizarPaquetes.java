package com.reasa.cotizador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CotizarPaquetes extends AppCompatActivity {

    public Button cotizar;
    public Button agregarPaquete;
    public EditText alto;
    public EditText ancho;
    public EditText profundidad;
    public EditText peso;
    public EditText cantidad;
    public TextView cantidadPaqueteT;
    public ArrayList<Paquete> paquetes;
    public Context contexto;
    public ListView listView;
    public PasoParametros pasoParametros;
    public int contador = 0;
    public double costoViaje = 1.0;
    public String viajeTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizar_paquetes);
        pasoParametros = new PasoParametros();
        getSupportActionBar().hide();

        viajeTexto = getIntent().getStringExtra("Viaje");
        contexto = this.getApplicationContext();
        paquetes = new ArrayList<>();
        listView = (ListView) findViewById(R.id.paquetes);
        //ListView
        AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(contexto, paquetes, costoViaje);
        listView.setAdapter(adaptadorPaqueteCotizado);

        //Inicializan los editText
        alto        = (EditText) findViewById(R.id.paquete_alto);
        peso        = (EditText) findViewById(R.id.paquete_peso);
        ancho       = (EditText) findViewById(R.id.paquete_ancho);
        profundidad = (EditText) findViewById(R.id.paquete_profundidad);
        cantidad    = (EditText) findViewById(R.id.paquete_cantidad);

        alto.setGravity(Gravity.CENTER_HORIZONTAL);
        ancho.setGravity(Gravity.CENTER_HORIZONTAL);
        peso.setGravity(Gravity.CENTER_HORIZONTAL);
        profundidad.setGravity(Gravity.CENTER_HORIZONTAL);
        cantidad.setGravity(Gravity.CENTER_HORIZONTAL);

        cantidadPaqueteT = (TextView) findViewById(R.id.cantidad_paquetes);

        cantidad.setText("1");
        String viaje       = getIntent()
                                .getExtras()
                                    .getString("Viaje"
                                            , "No se puede reconocer el viaje");
        costoViaje = getIntent()
                                .getExtras()
                                    .getDouble("Costo", 1.0);

        cotizar = (Button) findViewById(R.id.cotizar_envio);

        Button limpiarLista = (Button) findViewById(R.id.limpiar_paquete);

        limpiarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paquetes.clear();
                cantidadPaqueteT.setText("Cantidad de paquetes agregados: 0 ");
                AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(contexto, paquetes, costoViaje);
                listView.setAdapter(adaptadorPaqueteCotizado);
            }
        });

        //Cambio de Activity
        cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paquetes.size() <= 0) {
                    Toast.makeText(contexto,
                            "Debes cargar al menos un paquete para que se pueda realizar el cálculo",
                                Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(CotizarPaquetes.this, Cotizacion.class);
                    intent.putStringArrayListExtra("NombrePaquetes", pasoParametros.getCotizacion());
                    intent.putStringArrayListExtra("AltoPaquetes", pasoParametros.getAlto());
                    intent.putStringArrayListExtra("AnchoPaquetes", pasoParametros.getAncho());
                    intent.putStringArrayListExtra("ProfundidadPaquetes", pasoParametros.getProfundidad());
                    intent.putStringArrayListExtra("PesoPaquetes", pasoParametros.getPeso());
                    intent.putExtra("CostoViaje", costoViaje);
                    intent.putExtra("Viaje", viajeTexto);
                    startActivity(intent);
                }
            }
        });

        agregarPaquete = (Button) findViewById(R.id.agregar_paquete);

        agregarPaquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alto.getText().equals("") ||
                        ancho.getText().equals("") ||
                            profundidad.getText().equals("") ||
                                peso.getText().equals("") ||
                                    cantidad.getText().equals("")) {
                    Toast.makeText(contexto,
                            "Debes llenar todos los campos para que se pueda realizar el cálculo",
                                Toast.LENGTH_LONG).show();
                } else {
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

                            pasoParametros.agregarDatos("Paquete: " + contador,
                                    paquete.getAlto() + "",
                                    paquete.getAncho() + "",
                                    paquete.getProfundidad() + "",
                                    paquete.getPeso() + "");
                            contador++;

                        }
                        cantidadPaqueteT.setText("Cantidad de paquetes agregados: " + paquetes.size());
                        AdaptadorPaqueteCotizado adaptadorPaqueteCotizado = new AdaptadorPaqueteCotizado(contexto, paquetes, costoViaje);
                        listView.setAdapter(adaptadorPaqueteCotizado);

                        alto.setText("");
                        ancho.setText("");
                        profundidad.setText("");
                        peso.setText("");
                        cantidad.setText("1");

                    } catch (NumberFormatException e) {
                        Toast.makeText(contexto,
                                "Tienes un error en los campos, por favor llena correctamente todos los campos con valores válidos",
                                Toast.LENGTH_LONG).show();
                    } catch (NullPointerException e) {
                        Toast.makeText(contexto,
                                "Hay un error en el NULL POINTER EXCEPTION",
                                Toast.LENGTH_LONG).show();
                    }
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
