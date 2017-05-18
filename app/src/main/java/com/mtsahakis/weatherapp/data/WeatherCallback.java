package com.mtsahakis.weatherapp.data;


import java.util.List;

public interface WeatherCallback {

    void onDataLoaded(List<WeatherItem> weatherItems);

    void onDataFailed();

}
