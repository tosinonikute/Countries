package com.countries.di.component;

import com.countries.di.module.CountryFetcherModule;
import com.countries.di.module.CountryModule;
import com.countries.ui.countrylist.MainActivity;
import com.hellomvp.di.module.HelloFetcherModule;
import com.hellomvp.di.module.HelloModule;
import com.hellomvp.di.scope.UserScope;
import com.hellomvp.ui.hello.HelloActivity;
import com.hellomvp.ui.hello.HelloPresenter;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(modules = {CountryModule.class, CountryFetcherModule.class})
public interface CountryComponent {

    void inject(MainActivity mainActivity);
    void inject(CountryPresenter countryPresenter);

}
