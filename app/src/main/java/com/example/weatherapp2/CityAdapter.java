package com.example.weatherapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

import lombok.SneakyThrows;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CityFragment> cityList;
    private LayoutInflater layoutInflater;


    public CityAdapter(Context context, ArrayList<CityFragment> cityList) {
        this.cityList = cityList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @SneakyThrows
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.city_layout, viewGroup, false);
        }

        TextView cityView = view.findViewById(R.id.city_name);

        CityFragment cityFragment = getCity(i);

        if (cityFragment == null) {
            cityView.setText("Unknown");
        } else {
            cityView.setText(cityFragment.getCityName());
        }

        return view;
    }

    private CityFragment getCity(int pos) {
        return (CityFragment) getItem(pos);
    }
}
