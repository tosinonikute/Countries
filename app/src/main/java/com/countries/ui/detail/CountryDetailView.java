package com.countries.ui.detail;

import com.countries.ui.base.MvpView;

/**
 * @author Tosin Onikute.
 */

public interface CountryDetailView extends MvpView {

    void showLoading();

    void hideLoading();
}
