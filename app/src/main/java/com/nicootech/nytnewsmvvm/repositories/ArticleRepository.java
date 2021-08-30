package com.nicootech.nytnewsmvvm.repositories;

import com.nicootech.nytnewsmvvm.model.Docs;
import com.nicootech.nytnewsmvvm.request.ArticleApiClient;

import java.util.List;
import androidx.lifecycle.LiveData;



public class ArticleRepository {
    private static ArticleRepository instance;
    private ArticleApiClient mArticleApiClient;

    public static ArticleRepository getInstance() {
        if(instance == null){
            instance = new ArticleRepository();
        }
        return instance;
    }

    private ArticleRepository() {
        mArticleApiClient = ArticleApiClient.getInstance();
    }

    public LiveData<List<Docs>> getDocs() {
        return mArticleApiClient.getDocs();
    }

    public void searchArticlesApi(String query, int pageNumber){
        if(pageNumber == 0){
            pageNumber = 1;
        }
        mArticleApiClient.searchArticlesApi(query,pageNumber);
    }
}
