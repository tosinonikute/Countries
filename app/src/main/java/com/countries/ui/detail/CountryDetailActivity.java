package com.countries.ui.detail;

import android.os.Bundle;

import com.countries.R;
import com.countries.data.model.Country;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.util.Logger;

import java.util.ArrayList;

public class CountryDetailActivity extends BaseActivity {

    private ArrayList<Country> mCountryItem;
    private int position;
    private Logger logger = Logger.getLogger(getClass());

    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCountryItem = (ArrayList<Country>) getIntent().getSerializableExtra("mCountry");
            position = extras.getInt("position");
            logger.debug(mCountryItem.get(position).getFlag());

        }


    }
}
