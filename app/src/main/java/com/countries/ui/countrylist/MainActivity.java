package com.countries.ui.countrylist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.countries.BaseApplication;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.util.Logger;
import com.countries.util.NetworkUtil;
import com.countries.util.ui.MaterialProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity implements CountryView {

    @Inject
    CountryPresenter presenter;

    @Inject
    CountryInterface countryInterface;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<Country> countryItemList;
    private RelativeLayout newsLayout;
    private CountryListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private Snackbar snackbarOffline;


    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ((BaseApplication) getApplication()).getCountryComponent().inject(this);
        presenter.attachView(this);

        mCompositeSubscription = new CompositeSubscription();
        init();
        loadView();

    }

    // Initialize the view
    public void init() {
        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.countries_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void loadView(){
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
            adapter = new CountryListAdapter(getApplicationContext(), countryItemList);
            recyclerView.setAdapter(adapter); // set adapter on recyclerview
            adapter.notifyDataSetChanged(); // Notify the adapter
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
