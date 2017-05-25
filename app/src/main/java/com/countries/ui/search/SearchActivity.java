package com.countries.ui.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.countries.BaseApplication;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.ui.countrylist.CountryPresenter;
import com.countries.ui.countrylist.CountryView;
import com.countries.util.Logger;
import com.countries.util.NetworkUtil;
import com.countries.util.TextValidator;
import com.countries.util.ui.MaterialProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class SearchActivity extends BaseActivity implements CountryView {

    @Inject
    CountryPresenter presenter;

    @Inject
    CountryInterface countryInterface;

    private EditText searchEditText;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<Country> countryList;
    private RelativeLayout newsLayout;
    private SearchListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private Snackbar snackbarOffline;


    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        ((BaseApplication) getApplication()).getCountryComponent().inject(this);
        presenter.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mCompositeSubscription = new CompositeSubscription();
        init();
        loadView();

    }


    // Initialize the view
    public void init() {
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.countries_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void loadView(){

        receiveEditTextInput();

        if(NetworkUtil.isConnected(getApplicationContext())) {
            countryList();
            hideOfflineSnackBar();
        } else {
            displayOfflineSnackbar();
        }
    }

    public void countryList(){
        presenter.getCountryList(countryInterface, mCompositeSubscription);
    }


    public void setAdapter(ArrayList<Country> countryItemList){
        if(countryItemList.size() > 0) {
            countryList = new ArrayList(countryItemList);
            adapter = new SearchListAdapter(getApplicationContext(), countryItemList);
            recyclerView.setAdapter(adapter); // set adapter on recyclerview
            //adapter.notifyDataSetChanged(); // Notify the adapter
        }
    }


    public void displayOfflineSnackbar() {
        snackbarOffline = Snackbar.make(newsLayout, R.string.no_connection_snackbar, Snackbar.LENGTH_INDEFINITE);
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


    /* Methods handling search operation */

    public void receiveEditTextInput(){

        searchEditText.addTextChangedListener(new TextValidator(searchEditText) {
            @Override public void validate(TextView textView, String text) {
                String phone = searchEditText.getText().toString();
                logger.debug(phone);
                onSearchTextChanged(searchEditText.getText().toString());
            }
        });
    }


    public void onSearchTextChanged(String query) {
        if (TextUtils.isEmpty(query)) {
            displayCountries();
        } else {
            fetchSearchResults(query);
        }
    }

    public void fetchSearchResults(String query){

        boolean matchSearchString = false;
        adapter.clear();
        for (Country country : countryList) {
            if(query.length() <= country.getName().length() ){
               if(query.equals(country.getName().substring(0, query.length()))){
                   adapter.add(country);
                   matchSearchString = true;
               }
            }
        }

        if(!matchSearchString){
            adapter.clear();
        }

    }


    public void displayCountries(){
        adapter.clear();
        adapter.addAll(countryList);
    }


    @Override
    protected void onDestroy() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
