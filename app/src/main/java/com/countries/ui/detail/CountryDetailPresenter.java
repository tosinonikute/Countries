package com.countries.ui.detail;

import android.app.Application;
import android.support.annotation.NonNull;

import com.countries.data.model.Country;
import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInterface;
import com.countries.ui.base.BasePresenter;
import com.countries.util.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Tosin Onikute.
 */

public class CountryDetailPresenter extends BasePresenter<CountryDetailView> {

    private CountryInteractor countryInteractor;
    private CountryDetailView countryDetailView;
    private Logger logger = Logger.getLogger(getClass());

    public CountryDetailPresenter(CountryInteractor countryInteractor) {
        this.countryInteractor = countryInteractor;
    }

    @Override
    public void attachView(CountryDetailView countryDetailView){
        super.attachView(countryDetailView);
    }

    @Override
    public void detachView(){
        super.detachView();
    }

    public void getCountryList(CountryInterface countryInterface, CompositeDisposable compositeDisposable, String alpha3code){
        if(isViewAttached()) {
            getMvpView().showLoading();
            countryInteractor.fetchCountryByAlpha(countryInterface, alpha3code)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse,this::handleError);
        }
    }

    private void handleResponse(Country country) {
        getMvpView().setCountryDetails(country);
    }

    private void handleError(@NonNull Throwable error) {
        logger.debug(error.getLocalizedMessage());
    }

}
