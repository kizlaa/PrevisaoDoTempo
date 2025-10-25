package com.lucas.previsaodotempo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.previsaodotempo.R;
import com.lucas.previsaodotempo.models.WeatherModels;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.VH> {

    private final List<WeatherModels.Forecast> data;

    public ForecastAdapter(List<WeatherModels.Forecast> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        WeatherModels.Forecast f = data.get(position);
        h.tvDate.setText(f.getDate());
        h.tvDescription.setText(f.getDescription());
        h.tvTemp.setText("Máx: " + f.getMax() + "°C / Mín: " + f.getMin() + "°C");
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDate, tvDescription, tvTemp;
        VH(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTemp = itemView.findViewById(R.id.tvTemp);
        }
    }
}
