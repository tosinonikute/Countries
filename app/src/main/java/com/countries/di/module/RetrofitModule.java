package com.countries.di.module;


import com.countries.data.remote.CountryInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;


/**
 * @author Tosin Onikute.
 */

@Module
public class RetrofitModule {

    @Provides
    public CountryInterface providesCountryInterface(RestAdapter restAdapter) {
        return restAdapter.create(CountryInterface.class);
    }
}