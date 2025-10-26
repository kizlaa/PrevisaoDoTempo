package com.lucas.previsaodotempo.network;

import com.lucas.previsaodotempo.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("format") String format,
            @Query("city_name") String cityName

    );
}
