package com.nicootech.nytnewsmvvm.viewmodels;

import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.repositories.ArticleRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ArticleListViewModel extends ViewModel {

    private ArticleRepository mArticleRepository;

    public ArticleListViewModel() {
        mArticleRepository = ArticleRepository.getInstance();
    }

    public LiveData<List<Docs>> getDocs() {
        return mArticleRepository.getDocs();
    }

    public void searchArticlesApi(String query, int pageNumber){

        mArticleRepository.searchArticlesApi(query,pageNumber);
    }
}
