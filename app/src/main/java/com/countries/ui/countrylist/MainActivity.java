package com.countries.ui.countrylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.countries.BaseApplication;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.data.remote.CountryInterface;
import com.countries.di.component.CountryComponent;
import com.countries.ui.base.BaseActivity;
import com.countries.ui.search.SearchActivity;
import com.countries.util.Logger;
import com.countries.util.NetworkUtil;
import com.countries.util.ui.MaterialProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

public class MainActivity extends BaseActivity implements CountryView {

    @Inject
    CountryPresenter presenter;

    @Inject
    CountryInterface countryInterface;

    private Logger logger = Logger.getLogger(getClass());
    private CompositeDisposable mCompositeDisposable;
    private ArrayList<Country> countryItemList;
    private RelativeLayout mainLayout;
    private CountryListAdapter adapter;
    private RecyclerView recyclerView;
    private MaterialProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private Snackbar snackbarOffline;
    private VerticalRecyclerViewFastScroller fastScroller;
    private SectionTitleIndicator sectionTitleIndicator;
    private ImageView searchIconButton;


    @Override
    protected void setupActivity(CountryComponent component, Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ((BaseApplication) getApplication()).getCountryComponent().inject(this);
        presenter.attachView(this);

        mCompositeDisposable = new CompositeDisposable();
        init();
        loadView();

    }

    // Initialize the view
    public void init() {

        progressBar = (MaterialProgressBar) findViewById(R.id.material_progress_bar);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        searchIconButton = (ImageView) findViewById(R.id.search_icon_button);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.countries_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // fastscroller
        fastScroller = (VerticalRecyclerViewFastScroller) findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);

        // section indicator
        sectionTitleIndicator = (SectionTitleIndicator) findViewById(R.id.fast_scroller_section_title_indicator);
        recyclerView.setOnScrollListener(fastScroller.getOnScrollListener());
        fastScroller.setSectionIndicator(sectionTitleIndicator);


        searchIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSearchActivity();
            }
        });

    }

    public String getSectionFirstLetter(String letter) {
        if (!TextUtils.isEmpty(letter)) {
            return letter.substring(0, 1).toUpperCase();
        }

        return null;
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
        presenter.getCountryList(countryInterface, mCompositeDisposable);
    }


    public void setAdapter(ArrayList<Country> countryItemList){
        hideLoading();
        if(countryItemList.size() > 0) {
            adapter = new CountryListAdapter(getApplicationContext(), countryItemList);
            recyclerView.setAdapter(adapter); // set adapter on recyclerview
            //adapter.notifyDataSetChanged(); // Notify the adapter

            fastScroller.setVisibility(View.VISIBLE);
            recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    int topPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if(topPosition != -1) {
                        sectionTitleIndicator.setTitleText(getSectionFirstLetter(adapter.getItemPos(topPosition).getName()));
                    }
                }
            });
        }
    }


    public void displayOfflineSnackbar() {
        snackbarOffline = Snackbar.make(mainLayout, R.string.no_connection_snackbar, Snackbar.LENGTH_INDEFINITE);
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


    public void launchSearchActivity(){

        Context context = getApplicationContext();
        Intent intent = new Intent(context, SearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
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
