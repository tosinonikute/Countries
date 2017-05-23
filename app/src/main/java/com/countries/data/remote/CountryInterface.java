package com.countries.data.remote;


import com.countries.data.model.Country;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * @author Tosin Onikute.
 */

public interface CountryInterface {

    @GET("/all")
    void getCountry2(Callback<List<Country>> response);

    @GET("/all")
    Observable<List<Country>> getCountry();

    @GET("/all?fields=name;alpha2Code;alpha3Code;capital")
    Observable<List<Country>> getCountryByFilter();

    @GET("/all")
    Observable<List<Country>> getCountryByAlpha();

}
