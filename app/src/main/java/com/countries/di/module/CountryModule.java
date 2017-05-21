package com.countries.di.module;

import android.app.Application;

import com.countries.data.remote.CountryFetcher;
import com.countries.data.remote.CountryFetcherImpl;
import com.countries.ui.countrylist.CountryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class CountryModule {

    private Application application;

    public CountryModule(Application application){
        this.application = application;
    }


    @Provides
    public CountryPresenter getCountryPresenter(CountryFetcher countryFetcher){
        return new CountryPresenter(application, countryFetcher);
    }


    @Provides
    CountryFetcher provideCountryFetcher() {
        return new CountryFetcherImpl( application );
    }



}
