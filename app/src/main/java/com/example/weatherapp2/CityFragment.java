package com.example.weatherapp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp2.api.WeatherEntity;

import lombok.Getter;
import lombok.Setter;

public class CityFragment extends AppCompatActivity {

    @Getter
    @Setter
    private String cityName;

    @Getter
    @Setter
    //TODO: Implement
    private boolean isCurrentCity;

    @Getter
    @Setter
    private WeatherEntity weatherEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);
    }

    public CityFragment() {
    }

    public CityFragment(String cityName) {
        this.cityName = cityName;
    }
}