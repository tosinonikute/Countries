package com.countries.di.module;


import android.support.annotation.NonNull;

import com.countries.data.remote.CountryInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * @author Tosin Onikute.
 */

@Module
public class RetrofitModule {

    @Provides
    public CountryInterface providesCountryInterface(@NonNull Retrofit retrofit) {
        return retrofit.create(CountryInterface.class);
    }
}