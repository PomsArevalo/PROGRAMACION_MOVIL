package com.example.calculadora;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calculadora extends Activity implements View.OnClickListener {

    Button[] btnDigitos = new Button[10];

    Button btnSuma, btnResta, btnMultiplicacion, btnDivision;
    Button btnPunto, btnIgual, btnLimpiar;

    EditText pantalla;
    TextView txtOperacion;

    double op1, op2, res;

    String operacion = "";

    boolean pintarPunto = true;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        // PANEL PRINCIPAL
        LinearLayout panelPrincipal = new LinearLayout(this);
        panelPrincipal.setOrientation(LinearLayout.VERTICAL);
        panelPrincipal.setBackgroundColor(Color.parseColor("#121212"));
        panelPrincipal.setPadding(20,20,20,20);

        // PANEL PANTALLA
        LinearLayout panelPantalla = new LinearLayout(this);
        panelPantalla.setOrientation(LinearLayout.VERTICAL);
        panelPantalla.setBackgroundColor(Color.parseColor("#1E1E1E"));
        panelPantalla.setPadding(20,20,20,20);

        // TEXTO OPERACIÓN
        txtOperacion = new TextView(this);
        txtOperacion.setTextColor(Color.GRAY);
        txtOperacion.setTextSize(24);
        txtOperacion.setGravity(Gravity.END);

        // PANTALLA
        pantalla = new EditText(this);
        pantalla.setTextColor(Color.WHITE);
        pantalla.setBackgroundColor(Color.parseColor("#1E1E1E"));
        pantalla.setTextSize(42);
        pantalla.setGravity(Gravity.END);
        pantalla.setEnabled(false);

        panelPantalla.addView(txtOperacion);
        panelPantalla.addView(pantalla);

        // PANEL BOTONES
        LinearLayout panelBotones = new LinearLayout(this);
        panelBotones.setOrientation(LinearLayout.VERTICAL);

        // FILAS
        LinearLayout fila1 = new LinearLayout(this);
        LinearLayout fila2 = new LinearLayout(this);
        LinearLayout fila3 = new LinearLayout(this);
        LinearLayout fila4 = new LinearLayout(this);
        LinearLayout fila5 = new LinearLayout(this);

        // CONFIGURAR FILAS
        fila1.setWeightSum(4);
        fila2.setWeightSum(4);
        fila3.setWeightSum(4);
        fila4.setWeightSum(4);
        fila5.setWeightSum(4);

        // CREAR BOTONES NUMÉRICOS
        for(int i = 0; i <= 9; i++){

            btnDigitos[i] = crearBoton(""+i,
                    "#2D2D2D",
                    Color.WHITE);

            btnDigitos[i].setOnClickListener(this);
        }

        // BOTÓN LIMPIAR
        btnLimpiar = crearBoton("C",
                "#D32F2F",
                Color.WHITE);

        // BOTÓN DIVISIÓN
        btnDivision = crearBoton("/",
                "#FF9800",
                Color.WHITE);

        // BOTÓN MULTIPLICACIÓN
        btnMultiplicacion = crearBoton("×",
                "#FF9800",
                Color.WHITE);

        // BOTÓN RESTA
        btnResta = crearBoton("-",
                "#FF9800",
                Color.WHITE);

        // BOTÓN SUMA
        btnSuma = crearBoton("+",
                "#FF9800",
                Color.WHITE);

        // BOTÓN IGUAL
        btnIgual = crearBoton("=",
                "#4CAF50",
                Color.WHITE);

        // BOTÓN PUNTO
        btnPunto = crearBoton(".",
                "#2D2D2D",
                Color.WHITE);

        // LISTENERS
        btnLimpiar.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnMultiplicacion.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnSuma.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnPunto.setOnClickListener(this);

        // FILA 1
        fila1.addView(btnLimpiar);
        fila1.addView(btnDivision);
        fila1.addView(btnMultiplicacion);
        fila1.addView(btnResta);

        // FILA 2
        fila2.addView(btnDigitos[7]);
        fila2.addView(btnDigitos[8]);
        fila2.addView(btnDigitos[9]);
        fila2.addView(btnSuma);

        // FILA 3
        fila3.addView(btnDigitos[4]);
        fila3.addView(btnDigitos[5]);
        fila3.addView(btnDigitos[6]);

        // FILA 4
        fila4.addView(btnDigitos[1]);
        fila4.addView(btnDigitos[2]);
        fila4.addView(btnDigitos[3]);

        // FILA 5
        fila5.addView(btnDigitos[0]);
        fila5.addView(btnPunto);
        fila5.addView(btnIgual);

        // AGREGAR FILAS
        panelBotones.addView(fila1);
        panelBotones.addView(fila2);
        panelBotones.addView(fila3);
        panelBotones.addView(fila4);
        panelBotones.addView(fila5);

        // AGREGAR TODO
        panelPrincipal.addView(panelPantalla);
        panelPrincipal.addView(panelBotones);

        setContentView(panelPrincipal);
    }

    // CREAR BOTONES BONITOS
    public Button crearBoton(String texto, String colorFondo, int colorTexto){

        Button btn = new Button(this);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        200,
                        1
                );

        params.setMargins(10,10,10,10);

        btn.setLayoutParams(params);

        btn.setText(texto);
        btn.setTextSize(24);

        btn.setBackgroundColor(Color.parseColor(colorFondo));
        btn.setTextColor(colorTexto);

        return btn;
    }

    @Override
    public void onClick(View v) {

        // NÚMEROS
        for(int i = 0; i <= 9; i++){

            if(v.equals(btnDigitos[i])){

                pantalla.setText(
                        pantalla.getText() + "" + i
                );
            }
        }

        // PUNTO
        if(v.equals(btnPunto)){

            if(pintarPunto){

                pantalla.setText(
                        pantalla.getText() + "."
                );

                pintarPunto = false;
            }
        }

        // SUMA
        if(v.equals(btnSuma)){
            guardarOperacion("+");
        }

        // RESTA
        if(v.equals(btnResta)){
            guardarOperacion("-");
        }

        // MULTIPLICACIÓN
        if(v.equals(btnMultiplicacion)){
            guardarOperacion("×");
        }

        // DIVISIÓN
        if(v.equals(btnDivision)){
            guardarOperacion("/");
        }

        // IGUAL
        if(v.equals(btnIgual)){
            calcularResultado();
        }

        // LIMPIAR
        if(v.equals(btnLimpiar)){

            pantalla.setText("");
            txtOperacion.setText("");

            op1 = 0;
            op2 = 0;
            res = 0;

            operacion = "";

            pintarPunto = true;
        }
    }

    // GUARDAR OPERACIÓN
    public void guardarOperacion(String op){

        if(!pantalla.getText().toString().equals("")){

            op1 = Double.parseDouble(
                    pantalla.getText().toString()
            );

            operacion = op;

            txtOperacion.setText(
                    op1 + " " + operacion
            );

            pantalla.setText("");

            pintarPunto = true;
        }
    }

    // CALCULAR RESULTADO
    public void calcularResultado(){

        if(!pantalla.getText().toString().equals("")){

            op2 = Double.parseDouble(
                    pantalla.getText().toString()
            );

            switch (operacion){

                case "+":
                    res = op1 + op2;
                    break;

                case "-":
                    res = op1 - op2;
                    break;

                case "×":
                    res = op1 * op2;
                    break;

                case "/":

                    if(op2 != 0){
                        res = op1 / op2;
                    }else{
                        pantalla.setText("Error");
                        return;
                    }

                    break;
            }

            txtOperacion.setText(
                    op1 + " " + operacion + " " + op2
            );

            pantalla.setText(String.valueOf(res));

            pintarPunto = true;
        }
    }
}