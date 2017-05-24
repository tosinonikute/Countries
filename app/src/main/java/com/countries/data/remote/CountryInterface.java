package com.countries.data.remote;


import com.countries.data.model.Country;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
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

    @GET("/alpha/{alpha3code}")
    Observable<Country> getCountryByAlpha(@Path("alpha3code") String alpha3code);

    @GET("/alpha/{alpha3code}")
    void getCountryByAlpha2(@Path("alpha3code") String alpha3code, Callback<Country> response);


}
