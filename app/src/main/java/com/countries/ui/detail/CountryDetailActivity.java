package com.countries.ui.detail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.countries.BaseApplication;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.ui.countrylist.CountryListAdapter;
import com.countries.util.Logger;
import com.countries.util.NetworkUtil;
import com.countries.util.ui.MaterialProgressBar;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class CountryDetailActivity extends BaseActivity implements CountryDetailView {

    @Inject
    CountryDetailPresenter countryDetailPresenter;

    @Inject
    CountryInterface countryInterface;

    private ArrayList<Country> mCountryItem;
    private int position;
    private String alpha3code;
    private String toolbarTitle;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<Country> countryItemList;
    private LinearLayout detailLayout;
    private CountryListAdapter adapter;
    private MaterialProgressBar progressBar;
    private Snackbar snackbarOffline;

    private ImageView detailFlag;
    private int imageResource;
    private TextView toolbarText, countryNameText, regionText, capitalText,
            languageText, subRegionText, areaText, populationText, currencyText;

    private LinearLayout detailsContainer;



    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_country_detail);
        ((BaseApplication) getApplication()).getCountryComponent().inject(this);
        countryDetailPresenter.attachView(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCompositeSubscription = new CompositeSubscription();
        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCountryItem = (ArrayList<Country>) getIntent().getSerializableExtra("mCountry");
            position = extras.getInt("position");

            alpha3code = mCountryItem.get(position).getAlpha3Code();
            toolbarTitle = mCountryItem.get(position).getName();

            if(toolbarTitle != null) {
                toolbarText.setText(toolbarTitle);
                countryNameText.setText(toolbarTitle);
                setImageReseource(mCountryItem.get(position).getAlpha2Code());
            }

            logger.debug(alpha3code);
        }

        loadView();

    }

    // Initialize the view
    public void init() {
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        detailsContainer = (LinearLayout) findViewById(R.id.details_container);
        detailLayout = (LinearLayout) findViewById(R.id.detail_layout);

        detailFlag  = (ImageView) findViewById(R.id.detail_flag );
        toolbarText = (TextView) findViewById(R.id.toolbar_title);
        countryNameText = (TextView) findViewById(R.id.country_name);
        regionText = (TextView) findViewById(R.id.region);
        capitalText = (TextView) findViewById(R.id.capital);
        languageText = (TextView) findViewById(R.id.language);
        subRegionText = (TextView) findViewById(R.id.sub_region);
        areaText = (TextView) findViewById(R.id.area);
        populationText = (TextView) findViewById(R.id.population);
        currencyText = (TextView) findViewById(R.id.currency);

    }

    public void loadView(){
        if(NetworkUtil.isConnected(getApplicationContext())) {
            countryDetailList();
            hideOfflineSnackBar();
        } else {
            displayOfflineSnackbar();
        }
    }

    public void countryDetailList(){
        countryDetailPresenter.getCountryList(countryInterface, mCompositeSubscription, alpha3code);
    }


    public void setCountryDetails(Country details){

        detailsContainer.setVisibility(View.VISIBLE);

        String region = "";
        String capital = "";
        String language = "";
        String subRegion = "";
        double area = 0;
        int population = 0;
        String currency = "";

        if(details.getRegion() != null) region = details.getRegion();
        if(details.getCapital() != null) capital = details.getCapital();
        if(details.getLanguages().get(0).getName() != null) language = details.getLanguages().get(0).getName() ;
        if(details.getSubregion() != null) subRegion = details.getSubregion();
        if(details.getArea() != 0) area = details.getArea();
        if(details.getPopulation() != null) population = details.getPopulation();
        if(details.getCurrencies().get(0).getName() != null) currency = details.getCurrencies().get(0).getName();


        regionText.setText(region);
        capitalText.setText(capital);
        languageText.setText(language);
        subRegionText.setText(subRegion);
        areaText.setText(String.valueOf(NumberFormat.getInstance().format(area)));
        populationText.setText(String.valueOf(NumberFormat.getInstance().format(population)));
        currencyText.setText(currency);



    }


    public void setImageReseource(String alpha2code){

        String uri = "";
        if (alpha2code != null && !alpha2code.equals("")) {
            uri = "drawable/flag_" + alpha2code.toLowerCase();
        } else {
            uri = "drawable/place_holder";
        }

        int imageResource = getApplicationContext().getResources().getIdentifier(uri, null, getApplicationContext().getPackageName());
        if(imageResource != 0){
            Glide.with(getApplicationContext())
                    .load(imageResource)
                    .placeholder(R.drawable.place_holder)
                    .into(detailFlag);
        } else {
            Glide.with(getApplicationContext())
                    .load(R.drawable.place_holder)
                    .into(detailFlag);
        }
    }


    public void displayOfflineSnackbar() {
        snackbarOffline = Snackbar.make(detailLayout, R.string.no_connection_snackbar, Snackbar.LENGTH_INDEFINITE);
        TextView snackbarText = (TextView) snackbarOffline.getView().findViewById(android.support.design.R.id.snackbar_text);
        snackbarText.setTextColor(getApplicationContext().getResources().getColor(android.R.color.white));
        snackbarOffline.setAction(R.string.snackbar_action_retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadView();
            }
        });
        snackbarOffline.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbarOffline.show();
    }

    public void hideOfflineSnackBar() {
        if (snackbarOffline != null && snackbarOffline.isShown()) {
            snackbarOffline.dismiss();
        }
    }

    @Override
    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        progressBar.setVisibility(View.GONE);
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
