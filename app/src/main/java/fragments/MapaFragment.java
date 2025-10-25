package com.lucas.previsaodotempo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lucas.previsaodotempo.MainActivity;
import com.lucas.previsaodotempo.R;

public class MapaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        String cidade = "Maringá - PR";
        if (getActivity() instanceof MainActivity) {
            String cidadeSalva = MainActivity.getSavedCity((MainActivity) getActivity());
            if (cidadeSalva != null && !cidadeSalva.isEmpty()) {
                cidade = formatarCidade(cidadeSalva);
            }
        }

        TextView tv = view.findViewById(R.id.textViewMapa);
        tv.setText(cidade);

        return view;
    }

    private String formatarCidade(String codigo) {

        if (codigo.length() > 2) {
            String nome = codigo.substring(0, codigo.length() - 2);
            String uf = codigo.substring(codigo.length() - 2);
            nome = nome.replace("Maringa", "Maringá"); // corrige acento comum
            return nome + " - " + uf.toUpperCase();
        }
        return codigo;
    }
}
