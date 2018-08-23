package com.countries.data.remote;

import android.app.Application;

import com.countries.data.model.Country;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author Tosin Onikute.
 *
 * This is a Data Manager implementer class which contains methods, exposed for all the countries related data handling operations
 * Main purpose of this is to decouple your class, thus making it cleaner and testable.
 *
 * fetchCountries method is responsible for handling data retrieval for countries by Filter
 *
 * fetchCountryByAlpha method is responsible for handling data retrieval for a particular country with Alpha3code
 *
 */

public class CountryInteractorImpl implements CountryInteractor {

    public Observable<List<Country>> fetchCountries(CountryInterface countryInterface){
        return countryInterface.getCountryByFilter()
                .flatMap(countries -> Observable.just(countries));
    }

    public Observable<Country> fetchCountryByAlpha(CountryInterface countryInterface, String alpha3code){
        return countryInterface.getCountryByAlpha(alpha3code)
                .flatMap(country -> Observable.just(country));
    }
}
