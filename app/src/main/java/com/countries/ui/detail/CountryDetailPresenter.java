package com.countries.ui.detail;

import android.app.Application;

import com.countries.data.model.Country;
import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInterface;
import com.countries.ui.base.BasePresenter;
import com.countries.util.Logger;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Tosin Onikute.
 */

public class CountryDetailPresenter extends BasePresenter<CountryDetailView> {

    private final Application application;
    private CountryInteractor countryInteractor;
    private CountryDetailView countryDetailView;
    private Logger logger = Logger.getLogger(getClass());


    public CountryDetailPresenter(Application application, CountryInteractor countryInteractor) {
        this.application = application;
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


    public void getCountryList(CountryInterface countryInterface, CompositeSubscription mCompositeSubscription, String alpha3code){

        getMvpView().showLoading();

        mCompositeSubscription.add(countryInteractor.fetchCountryByAlpha(countryInterface, alpha3code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Country>() {
                    @Override
                    public void call(Country posts) {

                        getMvpView().hideLoading();
                        Country country = posts;
                        logger.debug(posts.getSubregion());

                        getMvpView().setCountryDetails(country);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        logger.debug(throwable.getLocalizedMessage());
                    }
                }));

    }


}
