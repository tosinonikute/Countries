package com.countries.ui.countrylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.countries.R;

public class MainActivity extends AppCompatActivity implements CountryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
