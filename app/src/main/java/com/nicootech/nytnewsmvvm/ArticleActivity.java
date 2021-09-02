package com.nicootech.nytnewsmvvm;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toolbar;
import androidx.annotation.Nullable;


public class ArticleActivity extends BaseActivity {
    private WebView mWebView;
    private Toolbar mToolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mWebView = findViewById(R.id.web_view_article);


    }



}
