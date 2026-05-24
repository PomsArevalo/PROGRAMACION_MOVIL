package com.example.listaconfragmentos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ListView listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAnimales = findViewById(R.id.listaAnimales);

        String[] elementos = {
                getString(R.string.item_perro),
                getString(R.string.item_gato),
                getString(R.string.item_vaca)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                elementos
        );
        listaAnimales.setAdapter(adapter);

        if (savedInstanceState == null) {
            reemplazarFragmento(new FragmentoPerro());
        }

        listaAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        reemplazarFragmento(new FragmentoPerro());
                        break;
                    case 1:
                        reemplazarFragmento(new FragmentoGato());
                        break;
                    case 2:
                        reemplazarFragmento(new FragmentoVaca());
                        break;
                }
            }
        });
    }


    private void reemplazarFragmento(Fragment fragmento) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.contenedorFragmento, fragmento);
        transaction.commit();
    }
}