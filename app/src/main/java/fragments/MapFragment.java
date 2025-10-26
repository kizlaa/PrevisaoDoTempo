package com.lucas.previsaodotempo.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lucas.previsaodotempo.R;
import com.lucas.previsaodotempo.utils.PrefHelper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private TextView mapFallback;
    private GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = v.findViewById(R.id.mapView);
        mapFallback = v.findViewById(R.id.mapFallback);

        mapView.onCreate(savedInstanceState);
        try {
            mapView.getMapAsync(this);
        } catch (Exception e) {

            mapFallback.setVisibility(View.VISIBLE);
        }
        return v;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;

        String city = PrefHelper.getCity(requireContext(), getString(R.string.cidade_padrao));
        LatLng pos = geocodeCity(city);
        if (pos == null) {

            pos = new LatLng(-23.5505, -46.6333);
        }

        map.addMarker(new MarkerOptions().position(pos).title(city));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 11f));
    }

    private LatLng geocodeCity(String city) {
        try {
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            List<Address> list = geocoder.getFromLocationName(city, 1);
            if (list != null && !list.isEmpty()) {
                Address a = list.get(0);
                return new LatLng(a.getLatitude(), a.getLongitude());
            }
        } catch (IOException ignored) { }
        return null;
    }

    @Override public void onResume() { super.onResume(); if (mapView != null) mapView.onResume(); }
    @Override public void onPause() { if (mapView != null) mapView.onPause(); super.onPause(); }
    @Override public void onDestroyView() { if (mapView != null) mapView.onDestroy(); super.onDestroyView(); }
    @Override public void onLowMemory() { super.onLowMemory(); if (mapView != null) mapView.onLowMemory(); }
}
