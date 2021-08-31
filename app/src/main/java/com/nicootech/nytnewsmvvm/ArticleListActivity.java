package com.nicootech.nytnewsmvvm;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.nicootech.nytnewsmvvm.adapters.ArticleRecyclerAdapter;
import com.nicootech.nytnewsmvvm.adapters.OnArticleListener;
import com.nicootech.nytnewsmvvm.models.Docs;
import com.nicootech.nytnewsmvvm.viewmodels.ArticleListViewModel;
import com.nicootech.nytnewsmvvm.utils.Testing;

import java.util.List;

public class ArticleListActivity extends BaseActivity  implements OnArticleListener {
    private static final String TAG = "ArticleListActivity";

    private ArticleListViewModel mArticleListViewModel;
    private RecyclerView mRecyclerView;
    private ArticleRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        mRecyclerView = findViewById(R.id.article_list);

        mArticleListViewModel = new ViewModelProvider(this).get(ArticleListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
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
                    mAdapter.setDocs(docs);
                }

            }
        });
    }

    private void initRecyclerView(){
        mAdapter = new ArticleRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void searchArticlesApi(String query, int pageNumber){

    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mArticleListViewModel.searchArticlesApi(s,0);
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
}