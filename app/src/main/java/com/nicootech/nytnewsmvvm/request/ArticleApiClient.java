package com.nicootech.nytnewsmvvm.request;

import android.util.Log;

import com.nicootech.nytnewsmvvm.AppExecutors;
import com.nicootech.nytnewsmvvm.model.Docs;
import com.nicootech.nytnewsmvvm.request.responses.ArticleSearchResponse;
import com.nicootech.nytnewsmvvm.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

import static com.nicootech.nytnewsmvvm.utils.Constants.NETWORK_TIMEOUT;

public class ArticleApiClient {

    private static final String TAG = "ArticleApiClient";

    private static ArticleApiClient instance;
    private MutableLiveData<List<Docs>> mDocs;
    private RetrieveArticlesRunnable mRetrieveArticlesRunnable;

    public static ArticleApiClient getInstance() {
        if(instance == null){
            instance = new ArticleApiClient();
        }
        return instance;
    }

    private ArticleApiClient() {
        mDocs = new MutableLiveData<>();
    }

    public LiveData<List<Docs>> getDocs() {
        return mDocs;
    }

    public void searchArticlesApi(String query, int pageNumber){
        if(mRetrieveArticlesRunnable != null){
            mRetrieveArticlesRunnable = null;
        }
        mRetrieveArticlesRunnable = new RetrieveArticlesRunnable(query,pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveArticlesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know its timed out.
                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveArticlesRunnable implements Runnable{

        private String query;
        private int pageNumber;
        private boolean cancelRequest;

        public RetrieveArticlesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getArticles(query,pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){

                    List<Docs> list = new ArrayList<>(((ArticleSearchResponse)response.body()).getResponse().getDocs());
                    if(pageNumber == 1){
                        mDocs.postValue(list);
                    }
                    else{
                        List<Docs>currentArticles = mDocs.getValue();
                        currentArticles.addAll(list);
                        mDocs.postValue(currentArticles);
                    }
                }
                else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: "+ error );
                    mDocs.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mDocs.postValue(null);
            }

        }

        private Call<ArticleSearchResponse>getArticles(String query, int pageNumber){
            return ServiceGenerator.getNytApi().searchArticle(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }
        
        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: canceling the searchRequest");
            cancelRequest = true;
        }
    }
}
