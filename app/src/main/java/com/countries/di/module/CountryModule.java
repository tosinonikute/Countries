package com.countries.di.module;

import android.app.Application;

import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInteractorImpl;
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
    public CountryPresenter getCountryPresenter(CountryInteractor countryInteractor){
        return new CountryPresenter(countryInteractor);
    }

    @Provides
    CountryInteractor provideCountryFetcher() {
        return new CountryInteractorImpl();
    }



}
