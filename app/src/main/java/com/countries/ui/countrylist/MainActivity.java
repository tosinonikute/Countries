package com.countries.ui.countrylist;

import android.os.Bundle;

import com.countries.BaseApplication;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.util.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity implements CountryView {

    @Inject
    CountryPresenter presenter;

    @Inject
    CountryInterface countryInterface;

    private final Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ((BaseApplication) getApplication()).getCountryComponent().inject(this);
        presenter.attachView(this);


//        countryInterface.getCountry2(new Callback<List<Country>>() {
//            @Override
//            public void success(List<Country> country, Response response) {
//
//                logger.debug(country.get(0).getName().toString());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                logger.debug(error.getLocalizedMessage());
//            }
//        });

        mCompositeSubscription = new CompositeSubscription();
        getNewsList(countryInterface, mCompositeSubscription);

    }


    public Observable<List<Country>> fetchNews(CountryInterface countryInterface){

        return countryInterface.getCountry()
                .flatMap(new Func1<List<Country>, Observable<List<Country>>>() {
                    @Override
                    public Observable<List<Country>> call(List<Country> newsEntity) {
                        return Observable.just(newsEntity);
                    }
                })
                .onErrorReturn(new Func1<Throwable, List<Country>>() {
                    @Override
                    public List<Country> call(Throwable thr) {
                        return null;
                    }
                });
    }


    public void getNewsList(CountryInterface countryInterface, CompositeSubscription mCompositeSubscription){

        mCompositeSubscription.add(fetchNews(countryInterface)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Country>>() {
                    @Override
                    public void call(List<Country> posts) {

                        List<Country> arr = posts;
                        logger.debug(posts.get(0).getName());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        logger.debug(throwable.getLocalizedMessage());
                    }
                }));
    }








    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

}
