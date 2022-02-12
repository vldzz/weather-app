package com.example.weatherapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp2.api.IWeatherAPI;
import com.example.weatherapp2.api.WeatherAPI;
import com.example.weatherapp2.api.WeatherEntity;
import com.example.weatherapp2.debug.Debug;

import java.text.ParseException;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityFragment extends AppCompatActivity {

    @Getter
    @Setter
    private String cityName;

    @Getter
    @Setter
    //TODO: Implement
    private boolean isCurrentCity;
//
//    @Getter
//    @Setter
//    private String temp;

    @Getter
    private WeatherEntity weatherEntity;

    private static IWeatherAPI iWeatherAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);

        fetchWeather();
    }

    public CityFragment() {
    }

    public CityFragment(String cityName) {
        this.cityName = cityName;

        if (iWeatherAPI == null) {
            iWeatherAPI = WeatherAPI.getClient().create(IWeatherAPI.class);
        }
    }

    private void fetchWeather() {
        Call<WeatherEntity> call = iWeatherAPI.getWeather(this.cityName, "metric", Configs.API_TOKEN, "en");

        CityFragment appCompatActivity = this;
        call.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                appCompatActivity.weatherEntity = response.body();
                setWeather();
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {
                Debug.error(appCompatActivity, "Failed to fetch :(");
            }
        });
    }


    private void setWeather() {
        TextView temp = this.findViewById(R.id.temp_fragment);
        temp.setText(weatherEntity.getTempStr());
    }
}