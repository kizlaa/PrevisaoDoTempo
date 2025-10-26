package com.lucas.previsaodotempo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.previsaodotempo.R;
import com.lucas.previsaodotempo.model.Forecast;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.VH> {

    private final List<Forecast> data = new ArrayList<>();

    public void setItems(List<Forecast> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Forecast f = data.get(position);
        h.txtDateWeekday.setText(f.weekday + " - " + f.date);
        h.txtDescription.setText(f.description);
        h.txtMax.setText(h.itemView.getContext().getString(R.string.label_max) + ": " + f.max + "°C");
        h.txtMin.setText(h.itemView.getContext().getString(R.string.label_min) + ": " + f.min + "°C");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtDateWeekday, txtDescription, txtMax, txtMin;
        public VH(@NonNull View itemView) {
            super(itemView);
            txtDateWeekday = itemView.findViewById(R.id.txtDateWeekday);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtMax = itemView.findViewById(R.id.txtMax);
            txtMin = itemView.findViewById(R.id.txtMin);
        }
    }
}
