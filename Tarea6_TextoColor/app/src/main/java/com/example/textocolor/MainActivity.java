package com.example.textocolor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

    Spinner comboColores;
    EditText textoCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comboColores = findViewById(R.id.comboopciones);
        textoCambio = findViewById(R.id.textocambio);

        String[] colores = {"Rojo", "Verde", "Azul", "Negro", "Morado"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colores
        );

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboColores.setAdapter(adaptador);

        comboColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> padre, View vista, int posicion, long id) {
                cambiarColor(posicion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> padre) {

            }
        });
    }

    public void cambiarColor(int posicion) {
        switch (posicion) {
            case 0:
                textoCambio.setTextColor(Color.RED);
                break;

            case 1:
                textoCambio.setTextColor(Color.GREEN);
                break;

            case 2:
                textoCambio.setTextColor(Color.BLUE);
                break;

            case 3:
                textoCambio.setTextColor(Color.BLACK);
                break;

            case 4:
                textoCambio.setTextColor(Color.rgb(128, 0, 128));
                break;
        }
    }
}