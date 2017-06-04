package com.mtsahakis.weatherapp;

import com.mtsahakis.weatherapp.data.Repository;
import com.mtsahakis.weatherapp.data.WeatherItem;
import com.mtsahakis.weatherapp.ui.WeatherContract;
import com.mtsahakis.weatherapp.ui.WeatherPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
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

    @Test
    public void onDataLoaded() {
        // given
        final List<WeatherItem> weatherItems = new ArrayList<>();
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                mWeatherPresenter.onDataLoaded(weatherItems);
                return null;
            }
        }).when(mRepository).makeRequest(mWeatherPresenter);

        // when
        mWeatherPresenter.init();

        // then
        verify(mView, times(1)).displayData(weatherItems);
    }

    @Test
    public void onDataFailed() {
        // given
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) {
                mWeatherPresenter.onDataFailed();
                return null;
            }
        }).when(mRepository).makeRequest(mWeatherPresenter);

        // when
        mWeatherPresenter.init();

        // then
        verify(mView, times(1)).displayError();
    }
}