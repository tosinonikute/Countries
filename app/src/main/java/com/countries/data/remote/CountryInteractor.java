package com.countries.data.remote;

import com.countries.data.model.Country;

import java.util.List;

import rx.Observable;

/**
 * @author Tosin Onikute.
 */

public interface CountryInteractor {

    Observable<List<Country>> fetchCountries(CountryInterface countryInterface);

    Observable<Country> fetchCountryByAlpha(CountryInterface countryInterface, String alpha3code);

}
