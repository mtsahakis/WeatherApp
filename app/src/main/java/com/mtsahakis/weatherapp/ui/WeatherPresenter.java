package com.mtsahakis.weatherapp.ui;


import android.support.annotation.NonNull;

import com.mtsahakis.weatherapp.data.Repository;
import com.mtsahakis.weatherapp.data.RepositoryCallback;
import com.mtsahakis.weatherapp.data.WeatherItem;

import java.util.List;

class WeatherPresenter implements WeatherContract.Presenter, RepositoryCallback {

    private WeatherContract.View mView;
    private Repository mRepository;

    WeatherPresenter(@NonNull WeatherContract.View view,
                     @NonNull Repository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void init() {
        mView.initView();
        mRepository.setWeatherCallback(this);
        mRepository.makeRequest();
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
