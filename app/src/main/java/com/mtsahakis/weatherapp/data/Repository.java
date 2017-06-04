package com.mtsahakis.weatherapp.data;


import android.net.Uri;
import android.util.Log;

import com.mtsahakis.weatherapp.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class Repository {

    private static final String LOG_TAG = "WeatherRepository";

    public void makeRequest(RepositoryCallback repositoryCallback) {
        final WeakReference<RepositoryCallback> callbackReference
                = new WeakReference<>(repositoryCallback);
        OkHttpClient client = new OkHttpClient();
        String url = constructURL();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                RepositoryCallback repositoryCallback = callbackReference.get();
                if (repositoryCallback != null) {
                    repositoryCallback.onDataFailed();
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response)
                    throws IOException {
                String result = response.body().string();
                RepositoryCallback repositoryCallback = callbackReference.get();
                if (repositoryCallback != null) {
                    try {
                        List<WeatherItem> weatherItems = getWeatherDataFromJson(result);
                        repositoryCallback.onDataLoaded(weatherItems);
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, e.getMessage(), e);
                        repositoryCallback.onDataFailed();
                    }
                }
            }
        });
    }

    private String constructURL() {
        final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
        final String ID_PARAM = "id";
        final String UNITS_PARAM = "units";
        final String CNT_PARAM = "cnt";
        final String APPID_PARAM = "APPID";

        final String units = "metric";
        final int numDays = 5;
        final int cityId = 2643741; // London

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(ID_PARAM, Integer.toString(cityId))
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(CNT_PARAM, Integer.toString(numDays))
                .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                .build();
        return uri.toString();
    }

    private List<WeatherItem> getWeatherDataFromJson(String response) throws JSONException {
        final List<WeatherItem> weatherItems = new ArrayList<>();

        final String JSON_LIST = "list";
        final String JSON_DT = "dt";
        final String JSON_TEMPERATURE = "temp";
        final String JSON_MAX = "max";
        final String JSON_MIN = "min";
        final String JSON_WEATHER = "weather";
        final String JSON_DESCRIPTION = "main";


        JSONObject forecastJson = new JSONObject(response);
        JSONArray weatherArray = forecastJson.getJSONArray(JSON_LIST);

        for (int i = 0; i < weatherArray.length(); i++) {
            JSONObject dayForecast = weatherArray.getJSONObject(i);
            long timeStamp = dayForecast.getLong(JSON_DT);
            JSONObject weather = dayForecast.getJSONArray(JSON_WEATHER).getJSONObject(0);
            String description = weather.getString(JSON_DESCRIPTION);
            JSONObject temperature = dayForecast.getJSONObject(JSON_TEMPERATURE);
            double high = temperature.getDouble(JSON_MAX);
            double low = temperature.getDouble(JSON_MIN);

            WeatherItem weatherItem = new WeatherItem(timeStamp, description, high, low);
            weatherItems.add(weatherItem);
        }

        return weatherItems;
    }

}
