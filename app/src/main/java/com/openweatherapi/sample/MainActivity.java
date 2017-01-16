package com.openweatherapi.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openweatherweapper.OpenWeatherApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the api
        OpenWeatherApi.initilize(getString(R.string.your_open_weather_api_key));
    }
}
