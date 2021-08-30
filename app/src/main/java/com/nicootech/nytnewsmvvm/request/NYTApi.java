package com.nicootech.nytnewsmvvm.request;

import com.nicootech.nytnewsmvvm.request.responses.ArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTApi {
    //Search
    @GET("svc/search/v2/articlesearch.json")
    Call<ArticleSearchResponse> searchArticle(

            @Query("api-key")String key,
            @Query("q")String query,
            @Query("page")String page
    );
}
