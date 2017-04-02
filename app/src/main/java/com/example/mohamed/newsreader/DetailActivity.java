package com.example.mohamed.newsreader;


import android.app.Activity;
import android.webkit.WebView;

/**
 * Created by Mohamed on 30/03/2017.
 */

public class DetailActivity extends Activity {


    @Override
    protected void onCreate(@android.support.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        
        String title = getIntent().getStringExtra("title");
        String news = getIntent().getStringExtra("news");
        
        setTitle(title);


        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadData(news,"text/html; charset=utf-8", "utf-8");
    }
}
