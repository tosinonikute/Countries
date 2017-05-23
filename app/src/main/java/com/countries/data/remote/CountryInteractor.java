package com.countries.data.remote;

import android.content.Context;

import com.countries.data.model.Country;

import java.util.List;

import rx.Observable;

/**
 * @author Tosin Onikute.
 */

public interface CountryInteractor {

    String sayHelloFetcher(Context context, String str);

    Observable<List<Country>> fetchCountries(CountryInterface countryInterface);

    Observable<List<Country>> fetchCountryByAlpha(CountryInterface countryInterface, String alpha3code);

}
