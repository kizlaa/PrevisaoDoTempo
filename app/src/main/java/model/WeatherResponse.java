package com.lucas.previsaodotempo.model;

import java.util.List;

public class WeatherResponse {
    public Results results;

    public static class Results {
        public String city;
        public String date;
        public String time;
        public Integer temp;
        public String description;
        public List<Forecast> forecast;
    }
}
