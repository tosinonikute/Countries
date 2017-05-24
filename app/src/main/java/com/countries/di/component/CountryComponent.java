package com.countries.di.component;

import com.countries.di.module.CountryDetailModule;
import com.countries.di.module.CountryModule;
import com.countries.di.module.RetrofitModule;
import com.countries.di.scope.UserScope;
import com.countries.ui.countrylist.CountryPresenter;
import com.countries.ui.countrylist.MainActivity;
import com.countries.ui.detail.CountryDetailActivity;
import com.countries.ui.detail.CountryDetailPresenter;
import com.countries.ui.search.SearchActivity;

import dagger.Component;

/**
 * @author Tosin Onikute.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = {RetrofitModule.class, CountryModule.class, CountryDetailModule.class})
public interface CountryComponent {

    void inject(MainActivity mainActivity);
    void inject(CountryDetailActivity countryDetailActivity);
    void inject(SearchActivity searchActivity);
    void inject(CountryPresenter countryPresenter);
    void inject(CountryDetailPresenter countryDetailPresenter);

}
