package com.countries.data.remote;


import com.hackernewsapp.discussion.model.Discussion;
import com.hackernewsapp.story.model.Story;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * @author Tosin Onikute.
 */

public interface CountryInterface {

    @GET("/{story_type}.json")
    Observable<List<Long>> getStories(@Path("story_type") String storyType);

    @GET("/item/{itemId}.json")
    Observable<Story> getStory(@Path("itemId") String itemId);

    @GET("/item/{itemId}.json")
    Observable<Discussion> getComment(@Path("itemId") long itemId);

    @GET("/{story_type}.json")
    public void getStory2(@Path("story_type") String storyType, Callback<List<Long>> response);

}
