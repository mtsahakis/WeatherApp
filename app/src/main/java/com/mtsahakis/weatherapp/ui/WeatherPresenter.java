package com.mtsahakis.weatherapp.ui;


import android.support.annotation.NonNull;

import com.mtsahakis.weatherapp.data.WeatherCallback;
import com.mtsahakis.weatherapp.data.WeatherItem;
import com.mtsahakis.weatherapp.data.WeatherRepository;

import java.util.List;

class WeatherPresenter implements WeatherContract.Presenter, WeatherCallback {

    private WeatherContract.View mView;
    private WeatherRepository mWeatherRepository;

    WeatherPresenter(@NonNull WeatherContract.View view,
                     @NonNull WeatherRepository weatherRepository) {
        mView = view;
        mWeatherRepository = weatherRepository;
    }

    @Override
    public void init() {
        mView.initView();
        mWeatherRepository.setWeatherCallback(this);
        mWeatherRepository.makeRequest();
    }

    @Override
    public void setData(List<WeatherItem> weatherItems) {
        mView.displayData(weatherItems);
    }

    @Override
    public void setError() {
        mView.displayError();
    }

    @Override
    public void onDataLoaded(List<WeatherItem> weatherItems) {
        setData(weatherItems);
    }

    @Override
    public void onDataFailed() {
        setError();
    }
}
