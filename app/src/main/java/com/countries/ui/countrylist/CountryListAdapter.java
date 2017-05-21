package com.countries.ui.countrylist;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.countries.R;
import com.countries.data.model.Country;
import com.countries.util.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Tosin Onikute.
 */

public class CountryListAdapter
        extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    private final Logger logger = Logger.getLogger(getClass());
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Country> mCountry;
    Context mContext;


    ArrayList<String> imageUrlList =  new ArrayList<String>();
    RecyclerView recyclerView;
    private String aTitle;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;


        // fonts
        public Typeface typeFace;

        public ViewHolder(View view) {
            super(view);
            mView = view;


        }

        @Override
        public String toString() {
            //return super.toString() + " '" + mCountryTitle.getText();
            return "";
        }
    }

    public String getValueAt(int position) {
        //return String.valueOf(mCountry.get(position).getId());
        return "";
    }

    public CountryListAdapter(Context context, ArrayList<Country> story) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mCountry = story;
        //recyclerView = recyclerV;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_list, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */

      
        logger.debug(String.valueOf(holder.getAdapterPosition()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewHolder.getAdapterPosition()
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mCountry ? mCountry.size() : 0);
    }

    public void addAll(List<Country> data){
        //mCountry.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        mCountry.clear();
    }

}

