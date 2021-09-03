package com.nicootech.nytnewsmvvm;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import com.nicootech.nytnewsmvvm.models.Docs;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;



public class ArticleActivity extends BaseActivity {
    private WebView mWebView;
    private Toolbar mToolbar;

    private static final String TAG = "ArticleActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mWebView = findViewById(R.id.web_view_article);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

//        mActionBar = getSupportActionBar();
//        mActionBar.setDisplayHomeAsUpEnabled(true);




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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_share_article, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.share_item:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String body = mWebView.getUrl();
                String subject = "NewYorkTimes: ";
                sharingIntent.putExtra(Intent.EXTRA_TEXT,body);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
                startActivity(Intent.createChooser(sharingIntent,"Share using"));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
