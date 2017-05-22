package com.countries.data.remote;

import android.app.Application;
import android.content.Context;

import com.countries.data.model.Country;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Tosin Onikute.
 */

public class CountryInteractorImpl implements CountryInteractor {

    private final Application application;

    public CountryInteractorImpl(Application application) {
        this.application = application;
    }

    public String sayHelloFetcher(Context context, String str){
        String newString = str + " + Hello Android ! ";
        return newString;
    }

    public Observable<List<Country>> fetchCountries(CountryInterface countryInterface){

        return countryInterface.getCountry()
                .flatMap(new Func1<List<Country>, Observable<List<Country>>>() {
                    @Override
                    public Observable<List<Country>> call(List<Country> countries) {
                        return Observable.just(countries);
                    }
                })
                .onErrorReturn(new Func1<Throwable, List<Country>>() {
                    @Override
                    public List<Country> call(Throwable thr) {
                        return null;
                    }
                });
    }






}
