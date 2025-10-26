package com.lucas.previsaodotempo.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.previsaodotempo.R;
import com.lucas.previsaodotempo.adapters.WeatherAdapter;
import com.lucas.previsaodotempo.model.WeatherResponse;
import com.lucas.previsaodotempo.network.ApiClient;
import com.lucas.previsaodotempo.utils.Constants;
import com.lucas.previsaodotempo.utils.PrefHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progress;
    private WeatherAdapter adapter;

    private final BroadcastReceiver cityChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadData();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerViewWeather);
        progress = v.findViewById(R.id.progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(cityChangedReceiver, new IntentFilter(Constants.ACTION_CITY_CHANGED));
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(requireContext())
                .unregisterReceiver(cityChangedReceiver);
    }

    private void loadData() {
        String city = PrefHelper.getCity(requireContext(), getString(R.string.cidade_padrao));
        progress.setVisibility(View.VISIBLE);

        ApiClient.get().getWeather("json", city)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherResponse> call,
                                           @NonNull Response<WeatherResponse> response) {
                        progress.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null
                                && response.body().results != null) {
                            if (response.body().results.forecast != null) {
                                adapter.setItems(response.body().results.forecast);
                            } else {
                                adapter.setItems(null);
                            }
                        } else {
                            Toast.makeText(getContext(), R.string.erro_api, Toast.LENGTH_SHORT).show();
                            adapter.setItems(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(getContext(), R.string.erro_api, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
