package com.example.listaconfragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class FragmentoPerro extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Aquí le decimos al controlador qué diseño XML debe inflar
        return inflater.inflate(R.layout.fragment_perro, container, false);
    }
}