package com.countries.ui.countrylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackernewsapp.R;
import com.hackernewsapp.discussion.DiscussionActivity;
import com.hackernewsapp.story.model.Story;
import com.hackernewsapp.util.Logger;
import com.hackernewsapp.util.Misc;

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
    private ArrayList<Story> mStory;
    Context mContext;
    ArrayList<String> imageUrlList =  new ArrayList<String>();
    RecyclerView recyclerView;
    private String aTitle;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mhotStory;
        public final TextView mStoryComments, mStoryTitle, mStoryPrettyUrl, mStoryPoints, mStoryTime;
        // fonts
        public Typeface typeFace;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mhotStory = (ImageView) view.findViewById(R.id.hot_story);
            mStoryComments = (TextView) view.findViewById(R.id.story_comments);
            mStoryTitle = (TextView) view.findViewById(R.id.story_title);
            mStoryPrettyUrl = (TextView) view.findViewById(R.id.story_pretty_url);
            mStoryPoints = (TextView) view.findViewById(R.id.story_point);
            mStoryTime = (TextView) view.findViewById(R.id.story_time);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mStoryTitle.getText();
        }
    }

    public String getValueAt(int position) {
        return String.valueOf(mStory.get(position).getId());
    }

    public CountryListAdapter(Context context, ArrayList<Story> story) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mContext = context;
        mBackground = mTypedValue.resourceId;
        mStory = story;
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

        final Story model = (Story) mStory.get(position);

        String title = "";
        String url = "";
        Integer commentsNo = 0;
        Integer points = 0;
        Integer time = 0;

            if (model.getTitle() != null) title = model.getTitle();
            if (model.getUrl() != null) url = model.getUrl();
            if (model.getDescendants() != null) commentsNo = model.getDescendants();
            if (model.getScore() != null) points = model.getScore();
            if (model.getTime() != null) time = model.getTime();

        aTitle = title;

        int tLength = title.length();
        if(tLength >= 80){
            title = title.substring(0, 80).toLowerCase() + "...";
            title = title.substring(0, 1).toUpperCase() + title.substring(1);
        } else {
            if(tLength > 3) {
                title = title.substring(0, tLength).toLowerCase();
                title = title.substring(0, 1).toUpperCase() + title.substring(1);
            }
        }



        holder.mStoryTitle.setText(title);

        holder.mStoryPrettyUrl.setText(url);
        holder.mStoryComments.setText(String.valueOf(commentsNo));
        holder.mStoryPoints.setText(String.valueOf(points) + mContext.getString(R.string.story_point_p));
        holder.mStoryTime.setText(String.valueOf(Misc.formatTime(time)));

        if(points > 50){
            holder.mhotStory.setImageResource(R.drawable.ic_fire);
        } else {
            holder.mhotStory.setImageResource(R.drawable.ic_fire_grey);
        }

        logger.debug(String.valueOf(holder.getAdapterPosition()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DiscussionActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("mStory", mStory);
                intent.putExtra("title", aTitle);
                Activity activity = (Activity) v.getContext();
                activity.startActivityForResult(intent, 500);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mStory ? mStory.size() : 0);
    }

    public void addAll(List<Story> data){
        //mStory.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        mStory.clear();
    }

}

