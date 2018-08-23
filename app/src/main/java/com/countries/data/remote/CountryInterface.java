package com.countries.data.remote;


import com.countries.data.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * @author Tosin Onikute.
 */

public interface CountryInterface {

    @GET("all")
    void getCountry2(Callback<List<Country>> response);

    @GET("all")
    Observable<List<Country>> getCountry();

    @GET("all?fields=name;alpha2Code;alpha3Code;capital")
    Observable<List<Country>> getCountryByFilter();

    @GET("alpha/{alpha3code}")
    Observable<Country> getCountryByAlpha(@Path("alpha3code") String alpha3code);

    @GET("alpha/{alpha3code}")
    void getCountryByAlpha2(@Path("alpha3code") String alpha3code, Callback<Country> response);

}
