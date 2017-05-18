package com.mtsahakis.weatherapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.mtsahakis.weatherapp.R;
import com.mtsahakis.weatherapp.data.Repository;
import com.mtsahakis.weatherapp.data.WeatherItem;
import com.mtsahakis.weatherapp.databinding.ActivityWeatherBinding;

import java.util.List;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    private ActivityWeatherBinding mBinding;
    private Handler mHandler;
    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getSupportActionBar().setElevation(0f);

        Repository repository = new Repository();
        mWeatherPresenter = new WeatherPresenter(this, repository);
        mWeatherPresenter.init();
    }

    @Override
    public void initView() {
        mBinding.errorMessage.setVisibility(View.GONE);
    }

    @Override
    public void displayData(final List<WeatherItem> weatherItems) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // remove progress bar and error message
                mBinding.errorMessage.setVisibility(View.GONE);
                mBinding.progressBar.setVisibility(View.GONE);

                // display data
                WeatherAdapter weatherAdapter = new WeatherAdapter(weatherItems);
                mBinding.recyclerView.setAdapter(weatherAdapter);
            }
        });
    }

    @Override
    public void displayError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.errorMessage.setVisibility(View.VISIBLE);
            }
        });
    }
}
