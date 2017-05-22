package com.countries.ui.countrylist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.countries.R;
import com.countries.data.model.Country;
import com.countries.ui.detail.CountryDetailActivity;
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
    private Context mContext;


    private String ctitle;
    private String cCapital;
    private String thumbnailURL;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView countryItemImage;
        public final TextView countryTitle;
        public final TextView countryCapital;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            countryItemImage = (ImageView) view.findViewById(R.id.country_item_image);
            countryTitle = (TextView) view.findViewById(R.id.country_title);
            countryCapital = (TextView) view.findViewById(R.id.country_capital);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf(mCountry.get(position).getName());
    }

    public CountryListAdapter(Context context, ArrayList<Country> story) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mCountry = story;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_countries, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /* Set your values */
        final Country model = (Country) mCountry.get(position);

        ctitle = "";
        cCapital = "";
        thumbnailURL = "";

        if (model.getName() != null) {
            ctitle = model.getName();
        }

        if (model.getCapital() != null) {
            cCapital = model.getCapital();
        }

        if (model.getFlag() != null) {
            thumbnailURL = model.getFlag();
        }

        logger.debug(String.valueOf(holder.getAdapterPosition()));
        holder.countryTitle.setText(ctitle);
        holder.countryCapital.setText(cCapital);

        Glide.with(holder.countryItemImage.getContext())
                .load(thumbnailURL)
                .placeholder(R.drawable.place_holder)
                .into(holder.countryItemImage);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CountryDetailActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("mCountry", mCountry);
                context.startActivity(intent);
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

