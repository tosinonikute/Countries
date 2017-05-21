package com.countries.di.module;

import android.app.Application;

import com.hackernewsapp.story.data.StoryInteractor;
import com.hackernewsapp.story.data.StoryInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Tosin Onikute.
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    StoryInteractor provideDataManager(StoryInteractorImpl appDataManager) {
        return appDataManager;
    }
}