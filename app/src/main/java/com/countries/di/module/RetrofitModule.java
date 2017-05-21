package com.countries.di.module;


import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.hackernewsapp.StoryInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;


/**
 * @author Tosin Onikute.
 */

@Module
public class RetrofitModule {

    @Provides
    public CountryInterface providesStoryInterface(RestAdapter restAdapter) {
        return restAdapter.create(CountryInterface.class);
    }
}