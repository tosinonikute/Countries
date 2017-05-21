package com.countries.ui.countrylist;

import android.app.Application;

import com.countries.data.remote.CountryFetcher;
import com.countries.ui.base.BasePresenter;

/**
 * @author Tosin Onikute.
 */

public class CountryPresenter extends BasePresenter<CountryView> {

    private final Application application;
    CountryFetcher countryFetcher;


    public CountryPresenter(Application application, CountryFetcher countryFetcher) {
        this.application = application;
        this.countryFetcher = countryFetcher;
    }



}
