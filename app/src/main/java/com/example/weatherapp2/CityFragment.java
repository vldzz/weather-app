package com.example.weatherapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.weatherapp2.debug.Debug;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);
    }


    public CityFragment(String cityName) {
        this.cityName = cityName;
    }

//    public void setCity(View view) {
//        Debug.error(MainActivity.mainActivity, "clicked");
//    }
}