package com.countries.ui.countrylist;

import android.support.annotation.NonNull;

import com.countries.data.model.Country;
import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInterface;
import com.countries.ui.base.BasePresenter;
import com.countries.ui.detail.CountryDetailView;
import com.countries.util.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Tosin Onikute.
 */

public class CountryPresenter extends BasePresenter<CountryView> {

    private CountryInteractor countryInteractor;
    private CountryView countryView;
    private CountryDetailView countryDetailView;
    private Logger logger = Logger.getLogger(getClass());

    public CountryPresenter(CountryInteractor countryInteractor) {
        this.countryInteractor = countryInteractor;
    }

    @Override
    public void attachView(CountryView countryView){
        super.attachView(countryView);
    }

    @Override
    public void detachView(){
        super.detachView();
    }

    public void setView(CountryDetailView countryDetailView){
        this.countryDetailView = countryDetailView;
    }

    public void getCountryList(CountryInterface countryInterface, CompositeDisposable compositeDisposable){
        if(isViewAttached()) {
            getMvpView().showLoading();
            compositeDisposable.add(countryInteractor.fetchCountries(countryInterface)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }

    private void handleResponse(List<Country> posts) {
        ArrayList<Country> countryItemList = new ArrayList<Country>(posts);
        getMvpView().setAdapter(countryItemList);
    }

    private void handleError(@NonNull Throwable error) {
        logger.debug(error.getLocalizedMessage());
    }

}
