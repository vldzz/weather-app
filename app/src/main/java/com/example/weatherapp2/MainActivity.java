package com.example.weatherapp2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp2.api.IWeatherAPI;
import com.example.weatherapp2.api.WeatherAPI;
import com.example.weatherapp2.api.WeatherEntity;
import com.example.weatherapp2.debug.Debug;
import com.example.weatherapp2.file.FileShare;
import com.jkb.slidemenu.SlideMenuLayout;

import java.text.ParseException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private IWeatherAPI iWeatherAPI;

    public static MainActivity mainActivity;

    private ImageView imageView;
    private TextView tempView;
    private TextView minTempView;
    private TextView maxTempView;
    private TextView dateView;
    private TextView cityView;
    private TextView stateView;
    private TextView feelLikeView;
    private TextView humidityView;
    private TextView uvIndexView;
    private TextView visibilityView;
    private TextView dewPointView;
    private TextView pressureView;

    private SlideMenuLayout slideMenuLayout;

    private ListView cities;
    private ArrayList<CityFragment> cityList = new ArrayList<>();
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        initElements();

        iWeatherAPI = WeatherAPI.getClient().create(IWeatherAPI.class);
        fetchWeather("Orhei");

        readSavedCitiesFromDisk();
        setupCityAdapter();

    }

    @Override
    public void onBackPressed() {
        if (slideMenuLayout.isRightSlideOpen()) {
            toggleSlider();
        } else {
            super.onBackPressed();
        }
    }


    private void setupCityAdapter() {
        cityAdapter = new CityAdapter(this, cityList);
        cities.setAdapter(cityAdapter);

        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long duration) {

                CityFragment clickedCity = (CityFragment) adapterView.getItemAtPosition(pos);
//                Debug.error(this, clickedCity.getCityName());
                fetchWeather(clickedCity.getCityName());
            }
        });
    }


    private void readSavedCitiesFromDisk() {
        cityList.addAll(FileShare.readSavedCitiesFromDisk());
    }

    private void initElements() {
        imageView = findViewById(R.id.image);
        tempView = findViewById(R.id.temp);
        minTempView = findViewById(R.id.minTemp);
        maxTempView = findViewById(R.id.maxTemp);
        dateView = findViewById(R.id.date);
        cityView = findViewById(R.id.city);
        stateView = findViewById(R.id.state);
        feelLikeView = findViewById(R.id.feelsLike);
        humidityView = findViewById(R.id.humidity);
        uvIndexView = findViewById(R.id.uvIndex);
        visibilityView = findViewById(R.id.visibility);
        dewPointView = findViewById(R.id.dewPoint);
        pressureView = findViewById(R.id.pressure);

        cities = findViewById(R.id.cities);

        slideMenuLayout = findViewById(R.id.citySlideMenu);
    }

    private void fetchWeather(String city) {
        Call<WeatherEntity> call = iWeatherAPI.getWeather(city, "metric", Configs.API_TOKEN, "en");

        AppCompatActivity appCompatActivity = this;
        call.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                WeatherEntity weather = response.body();
                try {
                    setWeather(weather);
                } catch (ParseException e) {
                    Debug.error(appCompatActivity, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {
                Debug.error(appCompatActivity, "Failed to fetch :(");
            }
        });

    }


    private void setWeather(WeatherEntity weather) throws ParseException {
//        Debug.error(this, weather.toString());
        tempView.setText(weather.getTempStr());
        minTempView.setText(weather.getTempMinStr());
        maxTempView.setText(weather.getTempMaxStr());

        dateView.setText(weather.getFormattedDateStr());
        cityView.setText(weather.getName());
        stateView.setText(weather.getStateStr());

        //DETAILS
        feelLikeView.setText(weather.getFeelsLikeStr());
        humidityView.setText(weather.getHumidityStr());
        uvIndexView.setText("-");
        visibilityView.setText(weather.getVisibilityStr());
        dewPointView.setText("-");
        pressureView.setText(weather.getPressureStr());
    }

    private void toggleSlider() {
        if (slideMenuLayout.isRightSlideOpen()) {
            findViewById(R.id.city_select_layout).setAlpha(1);
            slideMenuLayout.closeRightSlide();
        } else {
            findViewById(R.id.city_select_layout).setAlpha(0);
            slideMenuLayout.toggleRightSlide();

        }
    }

    public void search(View view) {
        cityList.add(new CityFragment("Balti"));
        cityList.add(new CityFragment( "Ungheni"));
        cityList.add(new CityFragment( "Drochia"));
        cityList.add(new CityFragment( "Soroca"));

        cityAdapter.notifyDataSetChanged();
        FileShare.saveCitiesToDisk(cityList);
    }

    public void onCityItemClicked(View view) {
        TextView city = (TextView) view.findViewById(R.id.city_name);
        fetchWeather(String.valueOf(city.getText()));
        toggleSlider();
    }

}