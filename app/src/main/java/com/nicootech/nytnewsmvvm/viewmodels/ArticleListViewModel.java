package com.nicootech.nytnewsmvvm.viewmodels;

import android.util.Log;

import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.repositories.ArticleRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ArticleListViewModel extends ViewModel {

    private static final String TAG = "ArticleListViewModel";
    private ArticleRepository mArticleRepository;
    private boolean mIsViewingArticles;
    private boolean mIsPerformingQuery;

    public ArticleListViewModel() {

        mArticleRepository = ArticleRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Docs>> getDocs() {
        return mArticleRepository.getDocs();
    }

    public void searchArticlesApi(String query, int pageNumber){
        mIsViewingArticles = true;
        mIsPerformingQuery = true;
        mArticleRepository.searchArticlesApi(query,pageNumber);
    }

    public void searchNextPage(){
        Log.d(TAG, "searchNextPage: called.");
        if(!mIsPerformingQuery
                && mIsViewingArticles){
            mArticleRepository.searchNextPage();
        }
    }



    public boolean isViewingArticles() {
        return mIsViewingArticles;
    }

    public void setIsViewingArticles(boolean isViewingArticles) {
        mIsViewingArticles = isViewingArticles;
    }

    public void setIsPerformingQuery(Boolean isPerformingQuery) {
        mIsPerformingQuery = isPerformingQuery;
    }
    public boolean isPerformingQuery(){
        return  mIsPerformingQuery;
    }

    public boolean onBackPressed(){
        if(mIsPerformingQuery){
            Log.d(TAG, "onBackPressed: canceling the request");
            mArticleRepository.cancelRequest();
            //mIsPerformingQuery = false;
        }
        if(mIsViewingArticles){
            mIsViewingArticles = false;
            return false;
        }
        return true;
    }
}
