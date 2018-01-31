package net.devbyzero.app.pascatrisakti;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by anta40 on 29-May-17.
 */

public class NewsWebActivity extends Activity {

    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("NEWS_URL");
        setContentView(R.layout.activity_news);

        webView = (WebView) findViewById(R.id.news_wv);
        webView.loadUrl(url);
    }
}
