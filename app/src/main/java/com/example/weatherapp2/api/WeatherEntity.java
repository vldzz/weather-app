package com.example.weatherapp2.api;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherEntity {
    @Getter
    private Coords coords;

    @SerializedName("weather")
    private ArrayList<Weather> weatherList;

    //    "base": "stations"
    private String base;

    private MainObject main;

    //"visibility": 10000
    private int visibility;

    private Wind wind;

    private Clouds clouds;

    //"dt": 1642660471
    private long dt;

    private Sys sys;

    //"timezone": 7200
    private double timezone;

    //"id": 617638
    private int id;

    //"name": "Orhei"
    private String name;

    //"cod": 200
    private float cod;

    public String getTempStr() {
        return String.format("%s°C", Math.round(this.main.getTemp()));
    }

    public String getTempMinStr() {
        return String.format("%s°", Math.round(this.main.getTempMin()));
    }

    public String getTempMaxStr() {
        return String.format("%s°", Math.round(this.main.getTempMax()));
    }

    public String getFeelsLikeStr() {
        return String.format("%s°C", Math.round(this.main.getFeelsLikeTemp()));
    }

    public String getHumidityStr() {
        return String.format("%d%%", Math.round(this.main.getHumidity()));
    }

    public String getVisibilityStr() {
        return String.format("%d km", Math.round(this.visibility / 1000));
    }

    public String getPressureStr() {
        return String.format("%d", this.main.getPressure());
    }


    public String getStateStr() {
        if (weatherList.size() > 0) {
            if (weatherList.get(0).getMain().equals("Clouds")) {
                return weatherList.get(0).getDescription().substring(0, 1).toUpperCase() +
                        weatherList.get(0).getDescription().substring(1);
            }
        }
        return "Unknown";
    }

    public String getFormattedDateStr() {
        return new SimpleDateFormat("E, MMMM dd").format(new Date(this.dt * 1000));
    }

    @Override
    public String
    toString() {
        return "WeatherEntity{" +
                "coords=" + coords +
                ", weatherList=" + weatherList +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }

    @Getter
    @Setter
    public static class Coords {
        //"lon": 28.8231,
        private double lon;
        //"lat": 47.3831
        private double lat;

        @Override
        public String toString() {
            return "Coords{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class Weather {
        //"id": 803
        private int id;

        //"main": "Clouds"
        private String main;

        //"description": "broken clouds"
        private String description;

        //"icon": "04d"
        private String icon;

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class MainObject {
        //"temp": -4.22,
        private double temp;

        @SerializedName("feels_like")
        //"feels_like": -9.09,
        private double feelsLikeTemp;

        @SerializedName("temp_min")
        //"temp_min": -4.56,
        private double tempMin;

        @SerializedName("temp_max")
        //"temp_max": -3.45,
        private double tempMax;

        //"pressure": 1015,
        private int pressure;
        //"humidity": 69,
        private int humidity;

        @SerializedName("sea_level")
        //"sea_level": 1015,
        private int seaLevel;

        @SerializedName("grnd_level")
        //"grnd_level": 1006
        private int grindLevel;

        @Override
        public String toString() {
            return "MainObject{" +
                    "temp=" + temp +
                    ", feelsLikeTemp=" + feelsLikeTemp +
                    ", tempMin=" + tempMin +
                    ", tempMax=" + tempMax +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", seaLevel=" + seaLevel +
                    ", grindLevel=" + grindLevel +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class Wind {
        //"speed": 3.55,
        private double speed;
        //"deg": 156,
        private double deg;
        //"gust": 9.9
        private double gust;

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    ", gust=" + gust +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class Clouds {
        //"all": 64
        private int all;

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }

    @Getter
    @Setter
    public static class Sys {
        //"type": 2,
        private int type;
        //"id": 2002036,
        private int id;
        //"country": "MD",
        private String country;
        //"sunrise": 1642657428,
        private int sunrise;
        //"sunset": 1642690028
        private int sunset;

        @Override
        public String toString() {
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }
}
