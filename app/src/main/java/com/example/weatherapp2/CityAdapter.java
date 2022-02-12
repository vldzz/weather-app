package com.example.weatherapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.weatherapp2.api.WeatherEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import lombok.SneakyThrows;

public class CityAdapter extends ArrayAdapter<CityFragment> {
    private Context context;
    private ArrayList<CityFragment> cityList;
    private LayoutInflater layoutInflater;
    private int resource;

    public CityAdapter(@NonNull Context context, int resource, @NonNull List<CityFragment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.city_layout, viewGroup, false);
        }

        TextView cityView = view.findViewById(R.id.city_name);
        TextView tempView = view.findViewById(R.id.temp_fragment);

        CityFragment cityFragment = getCity(i);

        if (cityFragment == null) {
            cityView.setText("Unknown");
        } else {
            cityView.setText(cityFragment.getCityName());

            WeatherEntity weatherEntity = cityFragment.getWeatherEntity();
            if (weatherEntity != null) {
                tempView.setText(weatherEntity.getTempStr());
            } else {
                tempView.setText("-");
            }
        }


        return view;
    }

    private CityFragment getCity(int pos) {
        return (CityFragment) getItem(pos);
    }
}
