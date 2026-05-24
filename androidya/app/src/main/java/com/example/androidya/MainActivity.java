package com.example.androidya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    LinearLayout menuPrincipal, seccionRegistros, seccionCreditos;
    EditText etCodigo, etDescripcion, etPrecio, etResultado, etNombreArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuPrincipal = findViewById(R.id.menuPrincipal);
        seccionRegistros = findViewById(R.id.seccionRegistros);
        seccionCreditos = findViewById(R.id.seccionCreditos);

        etCodigo = findViewById(R.id.etCodigo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etResultado = findViewById(R.id.etResultado);
        etNombreArchivo = findViewById(R.id.etNombreArchivo);
    }

    public void mostrarRegistros(View v) {
        menuPrincipal.setVisibility(View.GONE);
        seccionRegistros.setVisibility(View.VISIBLE);
        seccionCreditos.setVisibility(View.GONE);
    }

    public void mostrarCreditos(View v) {
        menuPrincipal.setVisibility(View.GONE);
        seccionRegistros.setVisibility(View.GONE);
        seccionCreditos.setVisibility(View.VISIBLE);
    }

    public void volverMenu(View v) {
        menuPrincipal.setVisibility(View.VISIBLE);
        seccionRegistros.setVisibility(View.GONE);
        seccionCreditos.setVisibility(View.GONE);
    }

    public void altaArticulo(View v) {
        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("descripcion", descripcion);
        registro.put("precio", precio);

        long resultado = bd.insert("articulos", null, registro);
        bd.close();

        if (resultado == -1) {
            Toast.makeText(this, "Ya existe un artículo con ese código", Toast.LENGTH_SHORT).show();
        } else {
            guardarBitacora("Artículo agregado: " + codigo + " - " + descripcion + " - $" + precio);
            etResultado.setText("Artículo agregado correctamente.");
            Toast.makeText(this, "Artículo agregado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
    }

    public void consultarPorCodigo(View v) {
        String codigo = etCodigo.getText().toString();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Ingresa el código", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor fila = bd.rawQuery(
                "SELECT descripcion, precio FROM articulos WHERE codigo=?",
                new String[]{codigo});

        if (fila.moveToFirst()) {
            etDescripcion.setText(fila.getString(0));
            etPrecio.setText(fila.getString(1));

            etResultado.setText(
                    "Artículo encontrado:\n\n" +
                            "Código: " + codigo + "\n" +
                            "Descripción: " + fila.getString(0) + "\n" +
                            "Precio: $" + fila.getString(1));
        } else {
            Toast.makeText(this, "No existe artículo con ese código", Toast.LENGTH_SHORT).show();
        }

        fila.close();
        bd.close();
    }

    public void modificarArticulo(View v) {
        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("descripcion", descripcion);
        registro.put("precio", precio);

        int cantidad = bd.update("articulos", registro, "codigo=?",
                new String[]{codigo});

        bd.close();

        if (cantidad == 1) {
            guardarBitacora("Artículo modificado: " + codigo + " - " + descripcion + " - $" + precio);
            etResultado.setText("Artículo modificado correctamente.");
            Toast.makeText(this, "Artículo modificado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "No existe artículo con ese código", Toast.LENGTH_SHORT).show();
        }
    }

    public void bajaArticulo(View v) {
        String codigo = etCodigo.getText().toString();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Ingresa el código", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        int cantidad = bd.delete("articulos", "codigo=?",
                new String[]{codigo});

        bd.close();

        if (cantidad == 1) {
            guardarBitacora("Artículo eliminado con código: " + codigo);
            etResultado.setText("Artículo eliminado correctamente.");
            Toast.makeText(this, "Artículo eliminado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "No existe artículo con ese código", Toast.LENGTH_SHORT).show();
        }
    }

    public void exportarArticulos(View v) {
        String nombreArchivo = etNombreArchivo.getText().toString();

        if (nombreArchivo.isEmpty()) {
            Toast.makeText(this, "Ingresa un nombre de archivo", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT codigo, descripcion, precio FROM articulos", null);

        try {
            File archivo = new File(getExternalFilesDir(null), nombreArchivo);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(archivo));

            while (fila.moveToNext()) {
                osw.write(fila.getString(0) + "," +
                        fila.getString(1) + "," +
                        fila.getString(2) + "\n");
            }

            osw.flush();
            osw.close();

            guardarBitacora("Artículos exportados al archivo externo: " + nombreArchivo);

            etResultado.setText(
                    "Archivo exportado correctamente.\n\n" +
                            "Nombre: " + nombreArchivo + "\n\n" +
                            "Ruta:\n" + archivo.getAbsolutePath());

            Toast.makeText(this, "Artículos exportados", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error al exportar", Toast.LENGTH_SHORT).show();
        }

        fila.close();
        bd.close();
    }

    public void importarArticulos(View v) {
        String nombreArchivo = etNombreArchivo.getText().toString();

        if (nombreArchivo.isEmpty()) {
            Toast.makeText(this, "Ingresa el nombre del archivo", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            File archivo = new File(getExternalFilesDir(null), nombreArchivo);

            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String linea;
            int contador = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 3) {
                    ContentValues registro = new ContentValues();
                    registro.put("codigo", datos[0]);
                    registro.put("descripcion", datos[1]);
                    registro.put("precio", datos[2]);

                    long resultado = bd.insertWithOnConflict(
                            "articulos",
                            null,
                            registro,
                            SQLiteDatabase.CONFLICT_REPLACE);

                    if (resultado != -1) {
                        contador++;
                    }
                }
            }

            br.close();
            bd.close();

            guardarBitacora("Artículos importados desde archivo externo: " + nombreArchivo +
                    ". Total: " + contador);

            etResultado.setText(
                    "Importación finalizada.\n\n" +
                            "Archivo: " + nombreArchivo + "\n" +
                            "Artículos importados: " + contador);

            Toast.makeText(this, "Artículos importados", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "No se pudo importar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void verBitacora(View v) {
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
            BufferedReader br = new BufferedReader(archivo);

            String linea;
            StringBuilder contenido = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            br.close();
            archivo.close();

            etResultado.setText(contenido.toString());

        } catch (Exception e) {
            Toast.makeText(this, "No hay bitácora todavía", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarBitacora(String texto) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("bitacora.txt", MODE_APPEND));

            archivo.write(texto + "\n");
            archivo.close();

        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar bitácora", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        etCodigo.setText("");
        etDescripcion.setText("");
        etPrecio.setText("");
    }
}