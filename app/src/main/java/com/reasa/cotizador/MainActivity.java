package com.reasa.cotizador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public Button comenzar;
    public Spinner spinner;
    public String[] viajes = {"Culiacan - Guadalajara",
                                "Culiacan - Tepic",
                                    "Culiacan - Mazatlan",
                                        "Mazatlan - Tepic",
                                            "Mazatlan - Guadalajara",
                                                "Tepic - Guadalajara"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comenzar = (Button) findViewById(R.id.comenzar_cotizacion);

        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CotizarPaquetes.class);
                String viaje  = spinner.getSelectedItem().toString();
                intent.putExtra("Viaje", viaje);
                intent.putExtra("Costo", obtenerValor(viaje));
                startActivity(intent);
            }
        });

        //Spinner
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                                                this,
                                                    android.R.layout.simple_spinner_item,
                                                        viajes);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public double obtenerValor(String viaje){

        switch(viaje){
            case "Culiacan - Guadalajara":{
                return 3.635595;
            }
            case "Culiacan - Tepic":{
                return 2.7388149;
            }
            case "Culiacan - Mazatlan":{
                return 1.3088142;
            }
            case "Mazatlan - Tepic":{
                return 1.4300007;
            }
            case "Mazatlan - Guadalajara":{
                return 2.42373;
            }
            case "Tepic - Guadalajara":{
                return 1.4300007;
            }
            default:{
                return 1.0;
            }
        }
    }
}
