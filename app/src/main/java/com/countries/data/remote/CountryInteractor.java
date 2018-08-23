package com.countries.data.remote;

import com.countries.data.model.Country;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Tosin Onikute.
 */

public interface CountryInteractor {

    Observable<List<Country>> fetchCountries(CountryInterface countryInterface);

    Observable<Country> fetchCountryByAlpha(CountryInterface countryInterface, String alpha3code);

}
