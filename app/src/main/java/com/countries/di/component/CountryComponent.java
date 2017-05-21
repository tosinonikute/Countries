package com.countries.di.component;

import com.countries.di.module.CountryModule;
import com.countries.di.module.RetrofitModule;
import com.countries.di.scope.UserScope;
import com.countries.ui.countrylist.CountryPresenter;
import com.countries.ui.countrylist.MainActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class, CountryModule.class})
public interface CountryComponent {

    void inject(MainActivity mainActivity);
    void inject(CountryPresenter countryPresenter);

}
