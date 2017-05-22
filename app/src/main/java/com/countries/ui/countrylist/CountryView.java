package com.countries.ui.countrylist;

import com.countries.data.model.Country;
import com.countries.ui.base.MvpView;

import java.util.ArrayList;

/**
 * @author Tosin Onikute.
 */

public interface CountryView extends MvpView {

    void init();

    void loadView();

    void countryList();

    void setAdapter(ArrayList<Country> countryItemList);

    void displayOfflineSnackbar();

    void hideOfflineSnackBar();

    void showLoading();

    void hideLoading();

}
