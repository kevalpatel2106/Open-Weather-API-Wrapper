package com.openweatherweapper;

import android.os.Build;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Keval on 16-Jan-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

class RetrofitBuilder {

    static APIService getApiService() {
        OkHttpClient client;

        if (BuildConfig.DEBUG) { //In sandbox environment logs will be displayed.
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                    .build();
        } else {        //In production environment logs wont be available
            client = new OkHttpClient.Builder().build();
        }

        //Building retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIService.BASE_URL)
                .client(client)
                .build();

        return retrofit.create(APIService.class);
    }
}
