package com.countries.di.module;

import android.app.Application;

import com.countries.data.remote.CountryInteractor;
import com.countries.ui.detail.CountryDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class CountryDetailModule {

    private Application application;

    public CountryDetailModule(Application application){
        this.application = application;
    }


    @Provides
    public CountryDetailPresenter getCountryDetailPresenter(CountryInteractor countryInteractor){
        return new CountryDetailPresenter(countryInteractor);
    }




}
