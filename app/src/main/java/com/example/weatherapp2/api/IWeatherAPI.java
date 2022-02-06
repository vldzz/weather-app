package com.example.weatherapp2.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherAPI {
    @GET("data/2.5/weather")
    public Call<WeatherEntity> getWeather(@Query("q") String city, @Query("units") String units, @Query("appid") String appToken, @Query("lang") String language);
}