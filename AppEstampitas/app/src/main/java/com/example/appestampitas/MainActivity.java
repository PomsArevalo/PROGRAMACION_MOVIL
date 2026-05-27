package com.example.appestampitas;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    Button btnAlbum, btnCreditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout pantalla = new FrameLayout(this);
        pantalla.setBackgroundResource(R.drawable.fondo_menu);

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
        titulo.setText("World Cup 2026");
        titulo.setTextSize(34);
        titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        titulo.setTextColor(Color.rgb(16, 31, 70));
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(0, 0, 0, 12);

        TextView subtitulo = new TextView(this);
        subtitulo.setText("Álbum Panini Fifa ");
        subtitulo.setTextSize(17);
        subtitulo.setTextColor(Color.rgb(82, 94, 115));
        subtitulo.setGravity(Gravity.CENTER);
        subtitulo.setPadding(0, 0, 0, 80);

        btnAlbum = crearBoton("Álbum");
        btnCreditos = crearBoton("Créditos");

        tarjeta.addView(titulo);
        tarjeta.addView(subtitulo);
        tarjeta.addView(btnAlbum);
        tarjeta.addView(btnCreditos);

        pantalla.addView(tarjeta);
        setContentView(pantalla);

        btnAlbum.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AlbumActivity.class))
        );

        btnCreditos.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CreditsActivity.class))
        );
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(this);
        boton.setText(texto);
        boton.setAllCaps(false);
        boton.setTextSize(15);
        boton.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        boton.setTextColor(Color.WHITE);
        boton.setBackground(crearFondo(Color.rgb(33, 99, 220), 35));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                500,
                150
        );
        params.setMargins(0, 20, 0, 20);
        boton.setLayoutParams(params);

        return boton;
    }

    private GradientDrawable crearFondo(int color, int radio) {
        GradientDrawable fondo = new GradientDrawable();
        fondo.setColor(color);
        fondo.setCornerRadius(radio);
        return fondo;
    }
}