package com.example.appestampitas;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AlbumActivity extends AppCompatActivity {

    // Total general de estampas del álbum
    final int TOTAL_ESTAMPAS = 980;

    // Guarda la cantidad de cada estampa.
    // 0 = no la tengo
    // 1 = la tengo
    // 2 = la tengo + 1 repetida
    // 3 = la tengo + 2 repetidas
    HashMap<String, Integer> estampas = new HashMap<>();

    // Permite guardar los datos aunque se cierre la aplicación
    SharedPreferences preferencias;

    // Contenedor donde se dibujan las secciones y estampas
    LinearLayout contenedor;

    // Guarda qué apartado está abierto
    String vistaActual = "todas";

    // Lista de secciones del álbum
    ArrayList<Seccion> secciones = new ArrayList<>();

    // Clase para organizar cada sección del álbum
    static class Seccion {
        String titulo;
        String prefijo;
        int cantidad;
        int especialesHasta;

        Seccion(String titulo, String prefijo, int cantidad, int especialesHasta) {
            this.titulo = titulo;
            this.prefijo = prefijo;
            this.cantidad = cantidad;
            this.especialesHasta = especialesHasta;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Archivo interno donde se guardan las estampas
        preferencias = getSharedPreferences("AppEstampitas", MODE_PRIVATE);

        // Primero se crea la estructura del álbum
        crearListaAlbum();

        // Luego se cargan las estampas guardadas
        cargarDatos();

        // Finalmente se crea la pantalla
        crearPantalla();
    }

    // Aquí se crean todas las secciones del álbum
    private void crearListaAlbum() {
        secciones.add(new Seccion("FWC - Especiales 🏆", "FWC", 9, 9));
        secciones.add(new Seccion("FWC - Historia 📜", "HIS", 11, 11));

        secciones.add(new Seccion("ALG - Argelia 🇩🇿", "ALG", 20, 1));
        secciones.add(new Seccion("ARG - Argentina 🇦🇷", "ARG", 20, 1));
        secciones.add(new Seccion("AUS - Australia 🇦🇺", "AUS", 20, 1));
        secciones.add(new Seccion("AUT - Austria 🇦🇹", "AUT", 20, 1));
        secciones.add(new Seccion("BEL - Bélgica 🇧🇪", "BEL", 20, 1));
        secciones.add(new Seccion("BIH - Bosnia y Herzegovina 🇧🇦", "BIH", 20, 1));
        secciones.add(new Seccion("BRA - Brasil 🇧🇷", "BRA", 20, 1));
        secciones.add(new Seccion("CAN - Canadá 🇨🇦", "CAN", 20, 1));
        secciones.add(new Seccion("CPV - Cabo Verde 🇨🇻", "CPV", 20, 1));
        secciones.add(new Seccion("COL - Colombia 🇨🇴", "COL", 20, 1));
        secciones.add(new Seccion("COD - Congo RD 🇨🇩", "COD", 20, 1));
        secciones.add(new Seccion("CRO - Croacia 🇭🇷", "CRO", 20, 1));
        secciones.add(new Seccion("CUW - Curazao 🇨🇼", "CUW", 20, 1));
        secciones.add(new Seccion("CZE - República Checa 🇨🇿", "CZE", 20, 1));
        secciones.add(new Seccion("ECU - Ecuador 🇪🇨", "ECU", 20, 1));
        secciones.add(new Seccion("EGY - Egipto 🇪🇬", "EGY", 20, 1));
        secciones.add(new Seccion("ENG - Inglaterra 🏴", "ENG", 20, 1));
        secciones.add(new Seccion("FRA - Francia 🇫🇷", "FRA", 20, 1));
        secciones.add(new Seccion("GER - Alemania 🇩🇪", "GER", 20, 1));
        secciones.add(new Seccion("GHA - Ghana 🇬🇭", "GHA", 20, 1));
        secciones.add(new Seccion("HAI - Haití 🇭🇹", "HAI", 20, 1));
        secciones.add(new Seccion("IRN - Irán 🇮🇷", "IRN", 20, 1));
        secciones.add(new Seccion("IRQ - Irak 🇮🇶", "IRQ", 20, 1));
        secciones.add(new Seccion("CIV - Costa de Marfil 🇨🇮", "CIV", 20, 1));
        secciones.add(new Seccion("JPN - Japón 🇯🇵", "JPN", 20, 1));
        secciones.add(new Seccion("JOR - Jordania 🇯🇴", "JOR", 20, 1));
        secciones.add(new Seccion("MEX - México 🇲🇽", "MEX", 20, 1));
        secciones.add(new Seccion("MAR - Marruecos 🇲🇦", "MAR", 20, 1));
        secciones.add(new Seccion("NED - Países Bajos 🇳🇱", "NED", 20, 1));
        secciones.add(new Seccion("NZL - Nueva Zelanda 🇳🇿", "NZL", 20, 1));
        secciones.add(new Seccion("NOR - Noruega 🇳🇴", "NOR", 20, 1));
        secciones.add(new Seccion("PAN - Panamá 🇵🇦", "PAN", 20, 1));
        secciones.add(new Seccion("PAR - Paraguay 🇵🇾", "PAR", 20, 1));
        secciones.add(new Seccion("POR - Portugal 🇵🇹", "POR", 20, 1));
        secciones.add(new Seccion("QAT - Qatar 🇶🇦", "QAT", 20, 1));
        secciones.add(new Seccion("KSA - Arabia Saudita 🇸🇦", "KSA", 20, 1));
        secciones.add(new Seccion("SCO - Escocia 🏴", "SCO", 20, 1));
        secciones.add(new Seccion("SEN - Senegal 🇸🇳", "SEN", 20, 1));
        secciones.add(new Seccion("RSA - Sudáfrica 🇿🇦", "RSA", 20, 1));
        secciones.add(new Seccion("KOR - República de Corea 🇰🇷", "KOR", 20, 1));
        secciones.add(new Seccion("ESP - España 🇪🇸", "ESP", 20, 1));
        secciones.add(new Seccion("SWE - Suecia 🇸🇪", "SWE", 20, 1));
        secciones.add(new Seccion("SUI - Suiza 🇨🇭", "SUI", 20, 1));
        secciones.add(new Seccion("TUN - Túnez 🇹🇳", "TUN", 20, 1));
        secciones.add(new Seccion("TUR - Turquía 🇹🇷", "TUR", 20, 1));
        secciones.add(new Seccion("URU - Uruguay 🇺🇾", "URU", 20, 1));
        secciones.add(new Seccion("USA - Estados Unidos 🇺🇸", "USA", 20, 1));
        secciones.add(new Seccion("UZB - Uzbekistán 🇺🇿", "UZB", 20, 1));
    }

    // Crea el diseño general de la pantalla
    private void crearPantalla() {
        LinearLayout pantalla = new LinearLayout(this);
        pantalla.setOrientation(LinearLayout.VERTICAL);
        pantalla.setBackgroundResource(R.drawable.fondo_album);

        TextView titulo = new TextView(this);
        titulo.setText("World Cup 2026");
        titulo.setTextSize(30);
        titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        titulo.setTextColor(Color.WHITE);
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(20, 120, 20, 20);

        // Menú superior horizontal con scroll
        HorizontalScrollView scrollMenu = new HorizontalScrollView(this);
        scrollMenu.setHorizontalScrollBarEnabled(false);

        LinearLayout menuSuperior = new LinearLayout(this);
        menuSuperior.setOrientation(LinearLayout.HORIZONTAL);
        menuSuperior.setPadding(18, 8, 18, 14);

        Button btnTodas = crearBotonMenu("Todas");
        Button btnFaltantes = crearBotonMenu("Me faltan");
        Button btnRepetidas = crearBotonMenu("Repetidas");
        Button btnEstadisticas = crearBotonMenu("Estadísticas");

        menuSuperior.addView(btnTodas);
        menuSuperior.addView(btnFaltantes);
        menuSuperior.addView(btnRepetidas);
        menuSuperior.addView(btnEstadisticas);

        scrollMenu.addView(menuSuperior);

        // Scroll principal para las estampas
        ScrollView scroll = new ScrollView(this);
        contenedor = new LinearLayout(this);
        contenedor.setOrientation(LinearLayout.VERTICAL);
        contenedor.setPadding(24, 8, 24, 25);
        scroll.addView(contenedor);

        // Botón inferior para regresar al menú principal
        Button btnRegresar = new Button(this);

        btnRegresar.setText("Regresar al menú");
        btnRegresar.setAllCaps(false);
        btnRegresar.setTextSize(17);
        btnRegresar.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        btnRegresar.setTextColor(Color.WHITE);
        btnRegresar.setBackground(
                crearFondoRedondeado(Color.rgb(33, 99, 220), 38)
        );
        btnRegresar.setOnClickListener(v -> finish());
        LinearLayout.LayoutParams paramsRegresar =
                new LinearLayout.LayoutParams(
                        500, // Width
                        150  // Height
                );
        paramsRegresar.gravity = Gravity.RIGHT;
        paramsRegresar.setMargins(24, 30, 50, 25);
        btnRegresar.setLayoutParams(paramsRegresar);
        pantalla.addView(titulo);
        pantalla.addView(scrollMenu);
        pantalla.addView(scroll, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1
        ));
        pantalla.addView(btnRegresar);

        setContentView(pantalla);

        // Eventos de los botones superiores
        btnTodas.setOnClickListener(v -> {
            vistaActual = "todas";
            mostrarTodas();
        });

        btnFaltantes.setOnClickListener(v -> {
            vistaActual = "faltantes";
            mostrarFaltantes();
        });

        btnRepetidas.setOnClickListener(v -> {
            vistaActual = "repetidas";
            mostrarRepetidas();
        });

        btnEstadisticas.setOnClickListener(v -> {
            vistaActual = "estadisticas";
            mostrarEstadisticas();
        });

        mostrarTodas();
    }

    // Crea los botones superiores
    private Button crearBotonMenu(String texto) {
        Button boton = new Button(this);
        boton.setText(texto);
        boton.setAllCaps(false);
        boton.setTextSize(15);
        boton.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        boton.setTextColor(Color.rgb(16, 31, 70));
        boton.setBackground(crearFondoConBorde(Color.WHITE, Color.rgb(220, 226, 238), 32, 2));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(270, 120);
        params.setMargins(8, 5, 8, 10);
        boton.setLayoutParams(params);

        return boton;
    }

    // Muestra todas las estampas
    private void mostrarTodas() {
        contenedor.removeAllViews();

        for (Seccion seccion : secciones) {
            agregarTituloSeccion(seccion.titulo);
            agregarEstampas(seccion, "todas");
        }
    }

    // Muestra solo las estampas faltantes
    private void mostrarFaltantes() {
        contenedor.removeAllViews();

        for (Seccion seccion : secciones) {
            boolean hayFaltantes = false;

            for (int i = 1; i <= seccion.cantidad; i++) {
                if (obtenerCantidad(obtenerClave(seccion, i)) == 0) {
                    hayFaltantes = true;
                    break;
                }
            }

            if (hayFaltantes) {
                agregarTituloSeccion(seccion.titulo);
                agregarEstampasFiltradas(seccion, "faltantes");
            }
        }
    }

    // Muestra solo las estampas repetidas
    private void mostrarRepetidas() {
        contenedor.removeAllViews();

        for (Seccion seccion : secciones) {
            boolean hayRepetidas = false;

            for (int i = 1; i <= seccion.cantidad; i++) {
                if (obtenerCantidad(obtenerClave(seccion, i)) > 1) {
                    hayRepetidas = true;
                    break;
                }
            }

            if (hayRepetidas) {
                agregarTituloSeccion(seccion.titulo);
                agregarEstampasFiltradas(seccion, "repetidas");
            }
        }
    }

    // Muestra estadísticas
    private void mostrarEstadisticas() {
        contenedor.removeAllViews();

        int tengo = 0;              // Estampas únicas obtenidas
        int repetidas = 0;          // Copias extras
        int especialesTengo = 0;    // Especiales únicas obtenidas
        int totalEspeciales = 68;

        for (Seccion seccion : secciones) {
            for (int i = 1; i <= seccion.cantidad; i++) {
                String clave = obtenerClave(seccion, i);
                int cantidad = obtenerCantidad(clave);

                // Solo cuenta una vez, aunque tengas repetidas
                if (cantidad > 0) {
                    tengo++;

                    // Solo cuenta una vez si es especial
                    if (esEspecial(seccion, i)) {
                        especialesTengo++;
                    }
                }

                // Aquí sí cuenta las copias extras
                if (cantidad > 1) {
                    repetidas += cantidad - 1;
                }
            }
        }

        int faltan = TOTAL_ESTAMPAS - tengo;
        int porcentaje = (tengo * 100) / TOTAL_ESTAMPAS;

        agregarTituloSeccion("Estadísticas del álbum");

        agregarFilaEstadistica("Completado", porcentaje + "%", "Total", String.valueOf(TOTAL_ESTAMPAS));
        agregarFilaEstadistica("Tengo", String.valueOf(tengo), "Me faltan", String.valueOf(faltan));
        agregarFilaEstadistica("Repetidas", String.valueOf(repetidas), "Especiales", especialesTengo + "/" + totalEspeciales);
    }

    // Agrega el título de cada sección
    private void agregarTituloSeccion(String texto) {
        TextView titulo = new TextView(this);
        titulo.setText(texto);
        titulo.setTextSize(25);
        titulo.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        titulo.setTextColor(Color.WHITE);
        titulo.setPadding(5, 34, 5, 14);
        contenedor.addView(titulo);

        View linea = new View(this);
        linea.setBackgroundColor(Color.argb(180, 255, 255, 255));
        contenedor.addView(linea, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2
        ));
    }

    // Agrega todas las estampas de una sección
    private void agregarEstampas(Seccion seccion, String tipoVista) {
        LinearLayout fila = null;

        for (int i = 1; i <= seccion.cantidad; i++) {
            if ((i - 1) % 4 == 0) {
                fila = crearFila();
            }

            fila.addView(crearEstampa(seccion, i, tipoVista));
        }
    }

    // Agrega solo faltantes o repetidas, pero conservando el orden
    private void agregarEstampasFiltradas(Seccion seccion, String tipoVista) {
        LinearLayout fila = null;
        int contador = 0;

        for (int i = 1; i <= seccion.cantidad; i++) {
            String clave = obtenerClave(seccion, i);
            int cantidad = obtenerCantidad(clave);

            boolean mostrar = false;

            if (tipoVista.equals("faltantes") && cantidad == 0) {
                mostrar = true;
            }

            if (tipoVista.equals("repetidas") && cantidad > 1) {
                mostrar = true;
            }

            if (mostrar) {
                if (contador % 4 == 0) {
                    fila = crearFila();
                }

                fila.addView(crearEstampa(seccion, i, tipoVista));
                contador++;
            }
        }
    }

    // Crea una fila horizontal de estampas
    private LinearLayout crearFila() {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setGravity(Gravity.CENTER);
        fila.setPadding(0, 12, 0, 12);
        contenedor.addView(fila);
        return fila;
    }

    // Crea visualmente cada estampa
    private FrameLayout crearEstampa(Seccion seccion, int numero, String tipoVista) {
        String codigoVisible = obtenerCodigoVisible(seccion, numero);
        String clave = obtenerClave(seccion, numero);
        int cantidad = obtenerCantidad(clave);
        boolean especial = esEspecial(seccion, numero);

        FrameLayout caja = new FrameLayout(this);

        LinearLayout.LayoutParams paramsCaja = new LinearLayout.LayoutParams(155, 155);
        paramsCaja.setMargins(10, 10, 10, 10);
        caja.setLayoutParams(paramsCaja);

        TextView circulo = new TextView(this);
        circulo.setText(codigoVisible);
        circulo.setGravity(Gravity.CENTER);
        circulo.setTextSize(18);
        circulo.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));

        FrameLayout.LayoutParams paramsCirculo = new FrameLayout.LayoutParams(142, 142);
        paramsCirculo.gravity = Gravity.CENTER;
        circulo.setLayoutParams(paramsCirculo);

        // Si ya la tengo, se pinta gris oscuro y se tacha el número
        if (cantidad > 0) {
            circulo.setTextColor(Color.WHITE);
            circulo.setPaintFlags(circulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            circulo.setBackground(crearFondoOval(Color.rgb(91, 103, 120)));
        } else {
            circulo.setPaintFlags(circulo.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

            // Si es especial, se pinta dorada
            if (especial) {
                circulo.setTextColor(Color.rgb(175, 123, 0));
                circulo.setBackground(crearFondoOval(Color.rgb(255, 239, 195)));
            } else {
                circulo.setTextColor(Color.rgb(87, 99, 118));
                circulo.setBackground(crearFondoOval(Color.rgb(232, 237, 246)));
            }
        }

        caja.addView(circulo);

        // Si tiene repetidas, muestra una burbuja azul con el número
        if (cantidad > 1) {
            TextView burbuja = new TextView(this);
            burbuja.setText(String.valueOf(cantidad - 1));
            burbuja.setTextSize(13);
            burbuja.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
            burbuja.setTextColor(Color.WHITE);
            burbuja.setGravity(Gravity.CENTER);
            burbuja.setBackground(crearFondoOval(Color.rgb(33, 99, 220)));

            FrameLayout.LayoutParams paramsBurbuja = new FrameLayout.LayoutParams(46, 46);
            paramsBurbuja.gravity = Gravity.TOP | Gravity.RIGHT;
            burbuja.setLayoutParams(paramsBurbuja);

            caja.addView(burbuja);
        }

        // CLIC NORMAL:
        // Siempre suma.
        // Si estaba en 0, pasa a 1.
        // Si estaba en 1, pasa a 2 y aparece repetida 1.
        // Si estaba en 2, pasa a 3 y aparece repetida 2.
        caja.setOnClickListener(v -> {
            int actual = obtenerCantidad(clave);
            estampas.put(clave, actual + 1);
            guardarDatos();
            refrescarVista();
        });

        // CLIC LARGO:
        // Sirve para restar.
        // Si tiene 1, se desmarca completamente.
        // Si tiene repetidas, resta una repetida.
        // Si tiene muchas repetidas, abre el cuadro para elegir cuántas quitar.
        caja.setOnLongClickListener(v -> {
            int actual = obtenerCantidad(clave);

            if (actual == 0) {
                Toast.makeText(this, "Esta estampa aún no está marcada", Toast.LENGTH_SHORT).show();
            } else if (actual == 1) {
                estampas.put(clave, 0);
                guardarDatos();
                refrescarVista();
            } else if (actual > 6) {
                mostrarDialogoEliminar(clave, codigoVisible);
            } else {
                estampas.put(clave, actual - 1);
                guardarDatos();
                refrescarVista();
            }

            return true;
        });

        return caja;
    }

    // Cuadro para eliminar varias repetidas
    private void mostrarDialogoEliminar(String clave, String codigoVisible) {
        int cantidadActual = obtenerCantidad(clave);
        int repetidasDisponibles = cantidadActual - 1;
        final int[] eliminar = {1};

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);

        Button menos = new Button(this);
        menos.setText("-");
        menos.setTextSize(22);

        TextView cantidad = new TextView(this);
        cantidad.setText(String.valueOf(eliminar[0]));
        cantidad.setTextSize(26);
        cantidad.setTypeface(null, Typeface.BOLD);
        cantidad.setPadding(45, 0, 45, 0);

        Button mas = new Button(this);
        mas.setText("+");
        mas.setTextSize(22);

        layout.addView(menos);
        layout.addView(cantidad);
        layout.addView(mas);

        menos.setOnClickListener(v -> {
            if (eliminar[0] > 1) {
                eliminar[0]--;
                cantidad.setText(String.valueOf(eliminar[0]));
            }
        });

        mas.setOnClickListener(v -> {
            if (eliminar[0] < repetidasDisponibles) {
                eliminar[0]++;
                cantidad.setText(String.valueOf(eliminar[0]));
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("Eliminar repetidas")
                .setMessage("Estampa: " + codigoVisible)
                .setView(layout)
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    int nuevaCantidad = cantidadActual - eliminar[0];

                    if (nuevaCantidad < 1) {
                        nuevaCantidad = 1;
                    }

                    estampas.put(clave, nuevaCantidad);
                    guardarDatos();
                    refrescarVista();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    // Crea una fila de estadísticas con dos cuadros
    private void agregarFilaEstadistica(String titulo1, String valor1, String titulo2, String valor2) {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams paramsFila = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paramsFila.setMargins(0, 14, 0, 14);
        fila.setLayoutParams(paramsFila);

        fila.addView(crearTarjetaEstadistica(titulo1, valor1));
        fila.addView(crearTarjetaEstadistica(titulo2, valor2));

        contenedor.addView(fila);
    }

    // Crea cada cuadro grande de estadísticas
    private LinearLayout crearTarjetaEstadistica(String titulo, String valor) {
        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.VERTICAL);
        tarjeta.setGravity(Gravity.CENTER);
        tarjeta.setPadding(16, 28, 16, 28);
        tarjeta.setBackground(crearFondoConBorde(Color.WHITE, Color.rgb(222, 228, 240), 34, 2));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 550, 1);
        params.setMargins(8, 30, 8, 8);
        tarjeta.setLayoutParams(params);

        TextView txtTitulo = new TextView(this);
        txtTitulo.setText(titulo);
        txtTitulo.setTextSize(18);
        txtTitulo.setTextColor(Color.rgb(83, 96, 120));
        txtTitulo.setGravity(Gravity.CENTER);

        TextView txtValor = new TextView(this);
        txtValor.setText(valor);
        txtValor.setTextSize(38);
        txtValor.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        txtValor.setTextColor(Color.rgb(16, 31, 70));
        txtValor.setGravity(Gravity.CENTER);

        tarjeta.addView(txtTitulo);
        tarjeta.addView(txtValor);

        return tarjeta;
    }

    // Código que se muestra dentro de la estampa
    private String obtenerCodigoVisible(Seccion seccion, int numero) {
        if (seccion.prefijo.equals("FWC")) {
            if (numero == 1) {
                return "00";
            } else {
                return String.valueOf(numero - 1);
            }
        }

        if (seccion.prefijo.equals("HIS")) {
            return String.valueOf(numero + 8);
        }

        return String.valueOf(numero);
    }

    // Clave interna única.
    // Evita que ALG 1, KOR 1 y PAN 1 se marquen juntas.
    private String obtenerClave(Seccion seccion, int numero) {
        return seccion.prefijo + "_" + numero;
    }

    // Define cuáles estampas son especiales
    private boolean esEspecial(Seccion seccion, int numero) {
        return numero <= seccion.especialesHasta;
    }

    // Obtiene la cantidad guardada de una estampa
    private int obtenerCantidad(String clave) {
        if (estampas.containsKey(clave)) {
            return estampas.get(clave);
        }

        return 0;
    }

    // Guarda los datos en el celular
    private void guardarDatos() {
        SharedPreferences.Editor editor = preferencias.edit();

        for (Seccion seccion : secciones) {
            for (int i = 1; i <= seccion.cantidad; i++) {
                String clave = obtenerClave(seccion, i);
                editor.putInt(clave, obtenerCantidad(clave));
            }
        }

        editor.apply();
    }

    // Carga los datos guardados al abrir la app
    private void cargarDatos() {
        for (Seccion seccion : secciones) {
            for (int i = 1; i <= seccion.cantidad; i++) {
                String clave = obtenerClave(seccion, i);
                int cantidad = preferencias.getInt(clave, 0);

                if (cantidad > 0) {
                    estampas.put(clave, cantidad);
                }
            }
        }
    }

    // Refresca el apartado actual
    private void refrescarVista() {
        if (vistaActual.equals("todas")) {
            mostrarTodas();
        } else if (vistaActual.equals("faltantes")) {
            mostrarFaltantes();
        } else if (vistaActual.equals("repetidas")) {
            mostrarRepetidas();
        } else {
            mostrarEstadisticas();
        }
    }

    // Crea fondos circulares
    private GradientDrawable crearFondoOval(int color) {
        GradientDrawable fondo = new GradientDrawable();
        fondo.setShape(GradientDrawable.OVAL);
        fondo.setColor(color);
        return fondo;
    }

    // Crea fondos redondeados
    private GradientDrawable crearFondoRedondeado(int color, int radio) {
        GradientDrawable fondo = new GradientDrawable();
        fondo.setShape(GradientDrawable.RECTANGLE);
        fondo.setColor(color);
        fondo.setCornerRadius(radio);
        return fondo;
    }

    // Crea fondos redondeados con borde
    private GradientDrawable crearFondoConBorde(int colorFondo, int colorBorde, int radio, int grosorBorde) {
        GradientDrawable fondo = new GradientDrawable();
        fondo.setShape(GradientDrawable.RECTANGLE);
        fondo.setColor(colorFondo);
        fondo.setCornerRadius(radio);
        fondo.setStroke(grosorBorde, colorBorde);
        return fondo;
    }
}