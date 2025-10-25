package com.lucas.previsaodotempo.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherModels {

    @SerializedName("results")
    private Results results;
    public Results getResults() { return results; }

    public static class Results {
        private String city;
        private List<Forecast> forecast;
        public String getCity() { return city; }
        public List<Forecast> getForecast() { return forecast; }
    }

    public static class Forecast {
        private String date;
        private String description;
        private int max;
        private int min;

        public String getDate() { return date; }
        public String getDescription() { return description; }
        public int getMax() { return max; }
        public int getMin() { return min; }
    }
}
