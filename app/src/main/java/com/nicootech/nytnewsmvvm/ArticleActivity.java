package com.nicootech.nytnewsmvvm;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.nicootech.nytnewsmvvm.models.Docs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;


public class ArticleActivity extends BaseActivity {
    private WebView mWebView;
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    private static final String TAG = "ArticleActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mWebView = findViewById(R.id.web_view_article);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //mActionBar = getSupportActionBar();
        //mActionBar.setDisplayHomeAsUpEnabled(true);


        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("article")){
            Docs doc = getIntent().getParcelableExtra("article");
            Log.d(TAG, "getIncomingIntent: "+doc.getWeb_url());
            mWebView.loadUrl(doc.getWeb_url());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_share_article, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
