package com.countries.ui.detail;

import com.countries.data.model.Country;
import com.countries.ui.base.MvpView;

/**
 * @author Tosin Onikute.
 */

public interface CountryDetailView extends MvpView {

    void init();

    void loadView();

    void countryDetailList();

    void setCountryDetails(Country details);

    void displayOfflineSnackbar();

    void hideOfflineSnackBar();

    void showLoading();

    void hideLoading();
}
