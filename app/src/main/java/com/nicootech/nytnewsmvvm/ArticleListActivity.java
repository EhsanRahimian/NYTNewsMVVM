package com.nicootech.nytnewsmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.nicootech.nytnewsmvvm.adapters.ArticleRecyclerAdapter;
import com.nicootech.nytnewsmvvm.adapters.OnArticleListener;
import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.utils.VerticalSpacingItemDecorator;
import com.nicootech.nytnewsmvvm.viewmodels.ArticleListViewModel;
import com.nicootech.nytnewsmvvm.utils.Testing;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArticleListActivity extends BaseActivity  implements OnArticleListener {
    private static final String TAG = "ArticleListActivity";

    private ArticleListViewModel mArticleListViewModel;
    private RecyclerView mRecyclerView;
    private ArticleRecyclerAdapter mAdapter;
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        mRecyclerView = findViewById(R.id.article_list);
        mSearchView = findViewById(R.id.search_view);

        mArticleListViewModel = new ViewModelProvider(this).get(ArticleListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mArticleListViewModel.isViewingArticles()){
            //display search categories
            displaySearchCategories();
        }
    }


    private void subscribeObservers(){
        mArticleListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
                if(docs != null){
                    if(mArticleListViewModel.isViewingArticles()){
                        Testing.print(docs,"articles test");
                        mArticleListViewModel.setIsPerformingQuery(false);
                        mAdapter.setDocs(docs);
                        //showProgressBar(false);
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        mAdapter = new ArticleRecyclerAdapter(this);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    private void searchArticlesApi(String query, int pageNumber){

    }

    private void initSearchView(){

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                mAdapter.displayLoading();
                //showProgressBar(true);
                mArticleListViewModel.searchArticlesApi(s,0);
                mSearchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onArticleClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

        mAdapter.displayLoading();
        mArticleListViewModel.searchArticlesApi(category,0);
        mSearchView.clearFocus();

    }

    private void displaySearchCategories(){
        mArticleListViewModel.setIsViewingArticles(false);
        mAdapter.displaySearchCategories();
    }
    @Override
    public void onBackPressed() {
        if(mArticleListViewModel.onBackPressed()){
            super.onBackPressed();
        }
        else{
            displaySearchCategories();
        }
    }
}