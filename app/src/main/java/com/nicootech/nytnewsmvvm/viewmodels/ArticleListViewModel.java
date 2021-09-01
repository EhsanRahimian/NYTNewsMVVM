package com.nicootech.nytnewsmvvm.viewmodels;

import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.repositories.ArticleRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ArticleListViewModel extends ViewModel {

    private ArticleRepository mArticleRepository;
    private boolean mIsViewingArticles;
    private boolean mIsPerformingQuery;

    public ArticleListViewModel() {
        mArticleRepository = ArticleRepository.getInstance();
    }

    public LiveData<List<Docs>> getDocs() {
        return mArticleRepository.getDocs();
    }

    public void searchArticlesApi(String query, int pageNumber){
        mIsViewingArticles = true;
        mIsPerformingQuery = true;
        mArticleRepository.searchArticlesApi(query,pageNumber);
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
            //cancel that query
            mArticleRepository.cancelRequest();
            mIsPerformingQuery = false;
        }
        if(mIsViewingArticles){
            mIsViewingArticles = false;
            return false;
        }
        return true;
    }
}
