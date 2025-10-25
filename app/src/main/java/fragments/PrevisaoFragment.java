package com.lucas.previsaodotempo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.previsaodotempo.MainActivity;
import com.lucas.previsaodotempo.R;
import com.lucas.previsaodotempo.adapters.ForecastAdapter;
import com.lucas.previsaodotempo.api.HgService;
import com.lucas.previsaodotempo.api.RetrofitClient;
import com.lucas.previsaodotempo.models.WeatherModels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrevisaoFragment extends Fragment {

    private RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_previsao, container, false);
        recycler = v.findViewById(R.id.recyclerForecast);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        carregar();
        return v;
    }

    private void carregar() {
        HgService api = RetrofitClient.getClient().create(HgService.class);
        String cidade = MainActivity.getSavedCity((MainActivity) requireActivity());

        Call<WeatherModels> call = api.getByCity(cidade);
        call.enqueue(new Callback<WeatherModels>() {
            @Override
            public void onResponse(Call<WeatherModels> call, Response<WeatherModels> resp) {
                if (resp.isSuccessful() && resp.body() != null &&
                        resp.body().getResults() != null &&
                        resp.body().getResults().getForecast() != null) {
                    recycler.setAdapter(new ForecastAdapter(resp.body().getResults().getForecast()));
                } else {
                    Toast.makeText(getContext(), "Sem dados da API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherModels> call, Throwable t) {
                Toast.makeText(getContext(), "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
