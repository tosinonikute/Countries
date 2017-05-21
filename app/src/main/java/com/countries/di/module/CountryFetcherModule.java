package com.countries.di.module;

import android.app.Application;

import dagger.Module;

/**
 * @author Tosin Onikute.
 */

@Module
public class CountryFetcherModule {

    private Application application;

    public CountryFetcherModule(Application application){
        this.application = application;
    }

}
