package com.nicootech.nytnewsmvvm;

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
        testRetrofitRequest();
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
        searchArticlesApi("afghanistan",25);

    }

    @Override
    public void onArticleClick(int position) {

    }
}