package com.nicootech.nytnewsmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nicootech.nytnewsmvvm.model.Docs;
import com.nicootech.nytnewsmvvm.model.viewmodels.ArticleListViewModel;
import com.nicootech.nytnewsmvvm.request.NYTApi;
import com.nicootech.nytnewsmvvm.request.ServiceGenerator;
import com.nicootech.nytnewsmvvm.request.responses.ArticleSearchResponse;
import com.nicootech.nytnewsmvvm.utils.Constants;
import com.nicootech.nytnewsmvvm.utils.Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends BaseActivity {
    private static final String TAG = "ArticleListActivity";

    private ArticleListViewModel mArticleListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        mArticleListViewModel = new ViewModelProvider(this).get(ArticleListViewModel.class);

        subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });

    }

    private void subscribeObservers(){
        mArticleListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
                if(docs != null){
                    Testing.print(docs,"articles test");
//                    for(Docs doc : docs){
//                        Log.d(TAG, "onChanged: "+doc.getHeadline().getMain());
//                    }
                }

            }
        });
    }
    private void searchArticlesApi(String query, int pageNumber){

        mArticleListViewModel.searchArticlesApi(query,pageNumber);
    }

    private void testRetrofitRequest(){
//        NYTApi nytApi = ServiceGenerator.getNytApi();
//
//        Call<ArticleSearchResponse> responseCall = nytApi
//                .searchArticle(
//                        Constants.API_KEY,
//                        "iran",
//                        "1"
//                );
//
//        responseCall.enqueue(new Callback<ArticleSearchResponse>() {
//            @Override
//            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
//                Log.d(TAG, "onResponse: server response"+response.toString());
//                if(response.code() == 200){
//                    Log.d(TAG, "onResponse: "+response.body().toString());
//                    List<Docs> docs = new ArrayList<>(response.body().getResponse().getDocs());
//                    for(Docs doc : docs){
//                        Log.d(TAG, "onResponse: "+doc.getHeadline().getMain());
//                    }
//                }
//                else{
//                    try {
//                        Log.d(TAG, "onResponse: "+response.errorBody().string());
//                    }catch (IOException e){
//                        e.printStackTrace();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {
//
//            }
//        });
        searchArticlesApi("iran",1);

    }
}