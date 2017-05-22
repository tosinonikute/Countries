package com.countries.ui.countrylist;

import android.app.Application;

import com.countries.data.model.Country;
import com.countries.data.remote.CountryInteractor;
import com.countries.data.remote.CountryInterface;
import com.countries.ui.base.BasePresenter;
import com.countries.util.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Tosin Onikute.
 */

public class CountryPresenter extends BasePresenter<CountryView> {

    private final Application application;
    private CountryInteractor countryInteractor;
    private CountryView countryView;
    private Logger logger = Logger.getLogger(getClass());



    public CountryPresenter(Application application, CountryInteractor countryInteractor) {
        this.application = application;
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

    public void getCountryList(CountryInterface countryInterface, CompositeSubscription mCompositeSubscription){

        getMvpView().showLoading();

        mCompositeSubscription.add(countryInteractor.fetchCountries(countryInterface)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Country>>() {
                    @Override
                    public void call(List<Country> posts) {

                        getMvpView().hideLoading();
                        List<Country> arr = posts;
                        logger.debug(posts.get(0).getName());

                        ArrayList<Country> countryItemList = new ArrayList<Country>(arr);
                        getMvpView().setAdapter(countryItemList);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        logger.debug(throwable.getLocalizedMessage());
                    }
                }));

    }





}
