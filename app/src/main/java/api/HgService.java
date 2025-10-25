package com.lucas.previsaodotempo.api;

import com.lucas.previsaodotempo.models.WeatherModels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HgService {
    @GET("weather")
    Call<WeatherModels> getByCity(@Query("city_name") String cityName);
}
