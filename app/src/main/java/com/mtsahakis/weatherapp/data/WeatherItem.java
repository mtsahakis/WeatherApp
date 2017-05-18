package com.mtsahakis.weatherapp.data;


public class WeatherItem {

    private final long mTimestamp;

    private final String mForecast;

    private final double mHigh;

    private final double mLow;

    WeatherItem(long timestamp, String forecast, double high, double low) {
        mTimestamp = timestamp;
        mForecast = forecast;
        mHigh = high;
        mLow = low;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public String getForecast() {
        return mForecast;
    }

    public double getHigh() {
        return mHigh;
    }

    public double getLow() {
        return mLow;
    }
}
