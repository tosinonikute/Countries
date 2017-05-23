package com.countries.ui.detail;

import android.os.Bundle;
import android.view.MenuItem;

import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.util.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

public class CountryDetailActivity extends BaseActivity  implements CountryDetailView {

    @Inject
    CountryDetailPresenter countryDetailPresenter;

    @Inject
    CountryInterface countryInterface;

    private ArrayList<Country> mCountryItem;
    private int position;
    private Logger logger = Logger.getLogger(getClass());

    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_country_detail);
        countryDetailPresenter.attachView(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCountryItem = (ArrayList<Country>) getIntent().getSerializableExtra("mCountry");
            position = extras.getInt("position");
            logger.debug(mCountryItem.get(position).getFlag());

        }
    }

    @Override
    public void showLoading(){
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
