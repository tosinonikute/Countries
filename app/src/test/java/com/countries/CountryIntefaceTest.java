package com.countries;

import android.app.Application;

import com.countries.data.model.Country;
import com.countries.data.model.Currency;
import com.countries.data.model.Language;
import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInterface;
import com.countries.ui.countrylist.CountryPresenter;
import com.countries.ui.countrylist.CountryView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * @author Tosin Onikute.
 */

public class CountryIntefaceTest {

    @Mock
    CountryInterface countryInterface;

    @Mock
    Application application;

    @Mock
    CountryInteractor countryInteractor;

    @Mock
    CountryView countryView;

    private CountryPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CountryPresenter(application, countryInteractor);
        presenter.attachView(countryView);
    }

    @Test
    public void testAPIResponse() throws Exception {
        // for this test, we use Colombia https://restcountries.eu/rest/v2/alpha/col
        String name = "Colombia";
        String alpha3Code = "COL";
        String capital = "Bogotá";
        String subRegion = "South America";

        // when
        when(countryInterface.getCountryByFilter()).thenReturn(Observable.just(countryList()));

        // given
        given(countryInterface.getCountryByFilter()).willReturn(Observable.just(countryList()));

        // When
        TestSubscriber<List<Country>> subscriber = new TestSubscriber<>();
        countryInterface.getCountryByFilter().subscribe(subscriber);

        // Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<List<Country>> onNextEvents = subscriber.getOnNextEvents();
        List<Country> countryInfo = onNextEvents.get(0);

        // assert for name
        Assert.assertEquals(name, countryInfo.get(0).getName());

        // assert for alpha3Code
        Assert.assertEquals(alpha3Code, countryInfo.get(0).getAlpha3Code());

        // assert for capital
        Assert.assertEquals(capital, countryInfo.get(0).getCapital());

        // assert for subRegion
        Assert.assertEquals(subRegion, countryInfo.get(0).getSubregion());


    }

    private List<Country> countryList() {

        // Setting up the values for test
        List<Country> listCountry = new ArrayList<Country>();

        Country country = new Country();
        country.setName("Colombia");

        List<String> topLevelDomain = new ArrayList<>();
        topLevelDomain.add(".co");

        country.setTopLevelDomain(topLevelDomain);
        country.setAlpha2Code("CO");
        country.setAlpha3Code("COL");

        List<String> callingCodes = new ArrayList<>();
        callingCodes.add("57");

        country.setCallingCodes(callingCodes);
        country.setCapital("Bogotá");

        List<String> altSpellings = new ArrayList<>();
        altSpellings.add("CO");
        altSpellings.add("Republic of Colombia");
        altSpellings.add("República de Colombia");

        country.setAltSpellings(altSpellings);
        country.setRegion("Americas");
        country.setSubregion("South America");
        country.setPopulation(48759958);

        List<Double> latlng = new ArrayList<>();
        latlng.add(4.0);
        latlng.add(-72.0);

        country.setLatlng(latlng);
        country.setDemonym("Colombian");
        country.setArea(1141748);
        country.setGini(55.9);

        List<String> timeZones = new ArrayList<>();
        timeZones.add("UTC-05:00");

        country.setTimezones(timeZones);

        List<String> borders = new ArrayList<>();
        borders.add("BRA");
        borders.add("ECU");
        borders.add("PAN");
        borders.add("PER");
        borders.add("VEN");

        country.setBorders(borders);
        country.setNativeName("Colombia");
        country.setNumericCode("170");

        // set currency
        Currency currency = new Currency();
        currency.setCode("COP");
        currency.setName("Colombian peso");
        currency.setSymbol("$");

        List<Currency> currentArrayList = new ArrayList<Currency>();
        currentArrayList.add(currency);

        // set language
        Language languages = new Language();
        languages.setIso6391("es");
        languages.setIso6392("spa");
        languages.setName("Spanish");
        languages.setNativeName("Español");

        List<Language> languageArrayList = new ArrayList<Language>();
        languageArrayList.add(languages);

        country.setCurrencies(currentArrayList);
        country.setLanguages(languageArrayList);

        // add all the country data into country List structure
        listCountry.add(country);

        return listCountry;
    }


}
