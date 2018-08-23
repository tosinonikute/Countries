package com.countries.di.component;

import android.content.SharedPreferences;

import com.countries.di.module.AppModule;
import com.countries.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


/**
 * @author Tosin Onikute.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {

    // downstream components need these exposed
    Retrofit restAdapter();
    SharedPreferences sharedPreferences();
}
