package com.countries.di.component;

import android.content.SharedPreferences;

import com.hackernewsapp.di.modules.AppModule;
import com.hackernewsapp.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.RestAdapter;


/**
 * @author Tosin Onikute.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {

    // downstream components need these exposed
    RestAdapter restAdapter();
    SharedPreferences sharedPreferences();

}
