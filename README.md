# Open-Weather-API-Wrapper

[ ![Download](https://api.bintray.com/packages/kevalpatel2106/maven/Open-Weather-API-Wrapper/images/download.svg) ](https://bintray.com/kevalpatel2106/maven/Open-Weather-API-Wrapper/_latestVersion) [![Twitter](https://img.shields.io/badge/Twitter-@Kevalonly77-blue.svg?style=flat)](https://twitter.com/Kevalonly77) [![Build Status](https://travis-ci.org/kevalpatel2106/Open-Weather-API-Wrapper.svg?branch=master)](https://travis-ci.org/kevalpatel2106/Open-Weather-API-Wrapper) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Open--Weather--API--Wrapper-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5251)

![sun.png](/images/sun.png)

Open Weather API Wrapper is an Android wrapper for the APIs of https://openweathermap.org. This library handles all the network operations and parameter validations on behalf of the developer.

## Dependency:
- Add below lines to `app/build.gradle` file of your project.
```
dependencies {
    compile 'com.kevalpatel2106:open-weather-wrapper:1.0'
}
```

## How to use this library?:
- **Initialization:**

Initialize the library in your launch activity by providing the open weather api key and the unit system you want to use throughout the application.
If you don't have the open weather api key, you can generate it from [here](http://openweathermap.org/appid).
```java
   OpenWeatherApi.initialize("YOUR API KEY", Unit.STANDARD);
```

- **Accessing the API:**

Open weather api provides you functions to access below information:
- _Get current weather for provided city, geo point or postal code._
- _Get three hourly forecast for city or, geo point._
- _Get the daily forecast for provided city or geo point._

You can get the required information by passing the required parameters. The information will be received in specific listeners.

Here is the example of getting the three hourly forecast of the weather by city name.
```java
OpenWeatherApi.getThreeHoursForecast("Landon,uk", new ForecastListener() {
    @Override
    public void onResponse(WeatherForecast weatherForecasts) {
        //Forecast received.
        //Do someting
    }

    @Override
    public void onError(String message) {
        //Something went wrong.
        //Display the error message to the user.
    }
});
```

Open Weather API Wrapper uses [RxJava](https://github.com/ReactiveX/RxJava) and [Retrofit](https://square.github.io/retrofit/) to handle the network operations.

## Demo:
- You can download the sample application from [here](https://github.com/kevalpatel2106/Open-Weather-API-Wrapper/releases/download/1.0/sample.apk).

## Contribute:
- Still there are many open weather apis to implement. Any pull request are most welcome.
**Simple 3 step to contribute into this repo:**
1. Fork the project.
2. Make required changes and commit.
3. Generate pull request. Mention all the required description regarding changes you made.

## Questions:
If you have any questions, hit me on twitter [@kevalonly77](https://twitter.com/Kevalonly77) .

## License
Copyright 2017 Keval Patel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
