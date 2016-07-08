package com.example.spider007.trsvisapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OurWebView extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        super.shouldOverrideUrlLoading(view, url);
        view.loadUrl(url);
        return true;
    }
}
