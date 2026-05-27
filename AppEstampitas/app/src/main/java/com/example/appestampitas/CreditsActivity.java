package com.example.appestampitas;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout pantalla = new FrameLayout(this);
        pantalla.setBackgroundResource(R.drawable.fondo_creditos);

        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.VERTICAL);
        tarjeta.setGravity(Gravity.CENTER);
        tarjeta.setPadding(55, 60, 55, 60);
        tarjeta.setBackground(crearFondo(Color.argb(215, 255, 255, 255), 45));

        FrameLayout.LayoutParams paramsTarjeta = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        paramsTarjeta.gravity = Gravity.CENTER;
        paramsTarjeta.setMargins(45, 0, 45, 0);
        tarjeta.setLayoutParams(paramsTarjeta);

        TextView titulo = new TextView(this);
        titulo.setText("Créditos");
        titulo.setTextSize(32);
        titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        titulo.setTextColor(Color.rgb(16, 31, 70));
        titulo.setGravity(Gravity.CENTER);

        TextView nombre = new TextView(this);
        nombre.setText("Pamela Ramírez Arevalo");
        nombre.setTextSize(24);
        nombre.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        nombre.setTextColor(Color.rgb(33, 99, 220));
        nombre.setGravity(Gravity.CENTER);
        nombre.setPadding(0, 32, 0, 30);

        TextView dedicatoria = new TextView(this);
        dedicatoria.setText(
                "Con mucho amor y dedicación para mi tortuguita vaga.\n" + "Disfruta cada avance de tu álbum. Te amo demasiado ❤\uFE0F"
        );
        dedicatoria.setTextSize(16);
        dedicatoria.setTextColor(Color.rgb(55, 65, 81));
        dedicatoria.setGravity(Gravity.CENTER);
        dedicatoria.setLineSpacing(6, 1);
        dedicatoria.setPadding(5, 5, 5, 35);
        dedicatoria.setGravity(Gravity.CENTER);
        dedicatoria.setLineSpacing(8,1);

        Button regresar = new Button(this);
        LinearLayout.LayoutParams paramsBoton = new LinearLayout.LayoutParams(
                500,
                150
        );
        paramsBoton.gravity = Gravity.CENTER;
        paramsBoton.setMargins(0, 20, 0, 0);

        regresar.setLayoutParams(paramsBoton);
        regresar.setText("Regresar al menú");
        regresar.setAllCaps(false);
        regresar.setTextSize(15);
        regresar.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        regresar.setTextColor(Color.WHITE);
        regresar.setBackground(crearFondo(Color.rgb(33, 99, 220), 35));
        regresar.setOnClickListener(v -> finish());

        tarjeta.addView(titulo);
        tarjeta.addView(nombre);
        tarjeta.addView(dedicatoria);
        tarjeta.addView(regresar);

        pantalla.addView(tarjeta);
        setContentView(pantalla);
    }

    private GradientDrawable crearFondo(int color, int radio) {
        GradientDrawable fondo = new GradientDrawable();
        fondo.setColor(color);
        fondo.setCornerRadius(radio);
        return fondo;
    }
}
