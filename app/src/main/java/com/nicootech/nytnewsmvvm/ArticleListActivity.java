package com.nicootech.nytnewsmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.nicootech.nytnewsmvvm.adapters.ArticleRecyclerAdapter;
import com.nicootech.nytnewsmvvm.adapters.OnArticleListener;

import com.nicootech.nytnewsmvvm.utils.VerticalSpacingItemDecorator;
import com.nicootech.nytnewsmvvm.viewmodels.ArticleListViewModel;
import com.nicootech.nytnewsmvvm.utils.Testing;

import org.jetbrains.annotations.NotNull;


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
        mArticleListViewModel.getDocs().observe(this, docs -> {
            if(docs != null){
                if(mArticleListViewModel.isViewingArticles()){
                    Testing.print(docs,"articles test");
                    mArticleListViewModel.setIsPerformingQuery(false);
                    mAdapter.setDocs(docs);
                    //showProgressBar(false);
                }
            }
        });
    }

    private void initRecyclerView(){
        mAdapter = new ArticleRecyclerAdapter(this);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!mRecyclerView.canScrollVertically(1)){
                    //search next page
                    mArticleListViewModel.searchNextPage();
                }
            }
        });


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
        Log.d(TAG, "onArticleClick: clicked. " + position);

        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("article",mAdapter.getSelectedArticle(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

        mAdapter.displayLoading();
        mArticleListViewModel.searchArticlesApi(category,0);
        mSearchView.clearFocus();

    }

    private void displaySearchCategories(){
        Log.d(TAG, "displaySearchCategories: called.");
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