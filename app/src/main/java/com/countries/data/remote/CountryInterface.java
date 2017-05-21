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

    @GET("/all?fields=name;alpha3Code;flag;capital")
    Observable<List<Country>> getCountryByFilter();

}
