package com.countries;

import android.app.Application;

import com.hellomvp.di.component.DaggerHelloComponent;
import com.hellomvp.di.component.HelloComponent;
import com.hellomvp.di.module.HelloFetcherModule;
import com.hellomvp.di.module.HelloModule;


/**
 * @author Tosin Onikute.
 */

public class BaseApplication extends Application {

    public HelloComponent component;

    @Override
    public void onCreate(){
        super.onCreate();

        component = DaggerHelloComponent
                .builder()
                .helloModule(new HelloModule(this))
                .helloFetcherModule(new HelloFetcherModule(this))
                .build();

    }

    public HelloComponent getComponent() {
        return component;
    }

}
