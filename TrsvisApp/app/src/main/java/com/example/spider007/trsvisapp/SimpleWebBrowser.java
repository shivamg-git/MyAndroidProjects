package com.example.spider007.trsvisapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleWebBrowser extends Activity implements View.OnClickListener {
    Button bGo, bBack, bForward, bRefresh, bClearHistory;
    WebView browser;
    EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_web_browser);
        initialize();
        browser.setWebViewClient(new OurWebView());
        setupBrowser();
    }

    private void setupBrowser() {

        // Xss Vulnerable due to JS enabling
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
    }

    private void initialize() {
        query = (EditText) findViewById(R.id.etSearchQuery);
        bGo = (Button) findViewById(R.id.bgo);
        bBack = (Button) findViewById(R.id.bBack);
        bForward = (Button) findViewById(R.id.bForward);
        bRefresh = (Button) findViewById(R.id.bRefresh);
        bClearHistory = (Button) findViewById(R.id.bClearHistory);
        browser = (WebView) findViewById(R.id.wvBrowser);

        bGo.setOnClickListener(this);
        bBack.setOnClickListener(this);
        bForward.setOnClickListener(this);
        bRefresh.setOnClickListener(this);
        bClearHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bgo:

                // Hiding Keyboard After EditText
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(query.getWindowToken(), 0);

                String q = query.getText().toString();
                String url;
                if (q.toLowerCase().contains("http"))
                    url = q;
                else
                    url = "https://www.google.com";

                try {
                    browser.loadUrl(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bBack:
                if (browser.canGoBack())
                    browser.goBack();
                break;
            case R.id.bForward:
                if (browser.canGoForward())
                    browser.goForward();
                break;
            case R.id.bRefresh:
                browser.reload();
                break;
            case R.id.bClearHistory:
                browser.clearHistory();
                break;
        }
    }
}
