package com.countries;

import android.app.Application;

import com.countries.di.component.CountryComponent;
import com.countries.di.component.DaggerCountryComponent;
import com.countries.di.component.DaggerNetComponent;
import com.countries.di.component.NetComponent;
import com.countries.di.module.AppModule;
import com.countries.di.module.CountryDetailModule;
import com.countries.di.module.CountryModule;
import com.countries.di.module.NetModule;
import com.countries.di.module.RetrofitModule;


/**
 * @author Tosin Onikute.
 */

public class BaseApplication extends Application {

    private NetComponent mNetComponent;
    private CountryComponent mCountryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        mCountryComponent = DaggerCountryComponent.builder()
                .netComponent(mNetComponent)
                .retrofitModule(new RetrofitModule())
                .countryModule(new CountryModule(this))
                .countryDetailModule(new CountryDetailModule(this))
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public CountryComponent getCountryComponent() {
        return mCountryComponent;
    }

}
