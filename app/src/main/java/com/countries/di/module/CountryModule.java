package com.countries.di.module;

import android.app.Application;

import com.hellomvp.HelloFetcher;
import com.hellomvp.HelloFetcherImpl;
import com.hellomvp.ui.hello.HelloPresenter;
import com.hellomvp.ui.hello.HelloPresenterImpl;

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
    public CountryPresenter getHelloPresenter(CountryFetcher helloFetcher){
        return new CountryPresenterImpl(application, helloFetcher);
    }


    @Provides
    CountryFetcher provideHelloFetcher() {
        return new CountryFetcherImpl( application );
    }



}
