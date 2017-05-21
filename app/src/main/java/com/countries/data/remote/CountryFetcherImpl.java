package com.countries.data.remote;

import android.app.Application;
import android.content.Context;

/**
 * @author Tosin Onikute.
 */

public class CountryFetcherImpl implements CountryFetcher {

    private final Application application;

    public CountryFetcherImpl(Application application) {
        this.application = application;
    }

    public String sayHelloFetcher(Context context, String str){
        String newString = str + " + Hello Android ! ";
        return newString;
    }


}
