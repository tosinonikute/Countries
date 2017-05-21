package com.countries.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.countries.Api;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * @author Tosin Onikute.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return restAdapter;
    }


}
