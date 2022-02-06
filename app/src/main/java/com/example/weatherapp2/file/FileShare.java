package com.example.weatherapp2.file;

import com.example.weatherapp2.CityFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FileShare {

    public static List<CityFragment> readSavedCitiesFromDisk() {
        List<CityFragment> cities = new ArrayList<>();

        cities.add(new CityFragment("Chisinau"));
        cities.add(new CityFragment("Orhei"));

        return cities;
    }

    public static void saveCitiesToDisk(ArrayList<CityFragment> cities) {

    }
}
