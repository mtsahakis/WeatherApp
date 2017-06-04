package com.mtsahakis.weatherapp;

import com.mtsahakis.weatherapp.data.Repository;
import com.mtsahakis.weatherapp.data.WeatherItem;
import com.mtsahakis.weatherapp.ui.WeatherContract;
import com.mtsahakis.weatherapp.ui.WeatherPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Mock
    WeatherContract.View mView;

    @Mock
    Repository mRepository;

    private WeatherPresenter mWeatherPresenter;

    @Before
    public void setUp() {
        mWeatherPresenter = new WeatherPresenter(mView, mRepository);
    }

    @Test
    public void init() {
        // when
        mWeatherPresenter.init();

        // then
        verify(mView, times(1)).initView();
        verify(mRepository, times(1)).makeRequest(mWeatherPresenter);
    }

    @Test
    public void setData() {
        // given
        List<WeatherItem> weatherItems = new ArrayList<>();

        // when
        mWeatherPresenter.setData(weatherItems);

        // then
        verify(mView, times(1)).displayData(weatherItems);
    }

    @Test
    public void setError() {
        // when
        mWeatherPresenter.setError();

        // then
        verify(mView, times(1)).displayError();
    }
}