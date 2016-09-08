package com.bernie.browseass.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bernie.browseass.R;
import com.bernie.browseass.application.BrowserAssApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bernie.greendao.dao.BrowseAssBookMarks;
import bernie.greendao.dao.DaoMaster;
import bernie.greendao.dao.DaoSession;

public class BrowserActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener, OnKeyListener, OnTouchListener {
    NavigationView navigationView;
    WebView webView;
    String DefaultWebSite = "https://github.com/";
    EditText editText;
    ImageView advancePage, retreatPage, homePage, bookMark, refreshPage;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String webSite;
    private final int BOOKMARK_REQUEST = 1;
    TextView collectPage;
    DaoMaster daoMaster;
    DaoSession daoSession;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        daoMaster = BrowserAssApplication.instance.getDaoMaster(getApplicationContext());
        daoSession = BrowserAssApplication.instance.getDaoSession(getApplicationContext());
        sqLiteDatabase = BrowserAssApplication.instance.getSqLiteDatabase(getApplicationContext());
        sharedPreferences = getSharedPreferences("webPage", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void initView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        webView = (WebView) findViewById(R.id.webView);
        editText = (EditText) findViewById(R.id.editText);
        advancePage = (ImageView) findViewById(R.id.advancePage);
        retreatPage = (ImageView) findViewById(R.id.retreatPage);
        homePage = (ImageView) findViewById(R.id.homePage);
        bookMark = (ImageView) findViewById(R.id.bookMark);
        refreshPage = (ImageView) findViewById(R.id.refreshPage);
        collectPage = (TextView)findViewById(R.id.collectPage);
        editText.setCursorVisible(false);
        initOnClickListener();
        setEditTextOnClickListener();
        webSite = sharedPreferences.getString("webSite", "").equals("") ? DefaultWebSite : sharedPreferences.getString("webSite", "");
        webViewLoadHtml(webSite);
    }

    public void initOnClickListener() {
        advancePage.setOnClickListener(this);
        retreatPage.setOnClickListener(this);
        homePage.setOnClickListener(this);
        bookMark.setOnClickListener(this);
        refreshPage.setOnClickListener(this);
        collectPage.setOnClickListener(this);
    }

    public void setEditTextOnClickListener() {
        editText.setOnKeyListener(this);
        editText.setOnTouchListener(this);
    }

    public void webViewLoadHtml(String webSite) {
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         view.loadUrl(url);
                                         return false;
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         Log.d("onPageFinished", "method 1 =  " + url);

                                     }

                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                         Log.d("onPageFinished", "method 2");
                                         return false;
                                     }

                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         Log.d("onPageFinished", "method 3 = " + url);
                                     }

                                     public void onLoadResource(WebView view, String url) {
                                        // Log.d("onPageFinished", "method 4 = " + url);
                                     }

                                     public void onPageCommitVisible(WebView view, String url) {
                                         editor.putString("webSite", url);
                                         editor.commit();
                                         editText.setText(url);
                                     }

                                     public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                                         Log.d("onPageFinished", "method 6 = " + error);
                                     }

                                     public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                                         Log.d("onPageFinished", "method 7 = " + errorResponse);
                                     }

                                     public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                                         Log.d("onPageFinished", "method 8 = " + dontResend.toString() + ";" + resend.toString());
                                     }

                                     public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                                         Log.d("onPageFinished", "method 9 = " + url);
                                     }

                                     public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                         Log.d("onPageFinished", "method 10 = " + error);
                                     }

                                     public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                                         Log.d("onPageFinished", "method 11 = " + request.toString());
                                     }

                                     public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                                         Log.d("onPageFinished", "method 12 = " + host + ";" + realm);
                                     }

                                     public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                                         Log.d("onPageFinished", "method 13 = " + event);
                                         return true;
                                     }

                                     public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                                         Log.d("onPageFinished", "method 14 = " + event);
                                     }

                                     public void onScaleChanged(WebView view, float oldScale, float newScale) {
                                         Log.d("onPageFinished", "method 15 = " + oldScale + ";" + newScale);
                                     }

                                     public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                                         Log.d("onPageFinished", "method 16 = " + realm + ";" + account + ";" + args);
                                     }
                                 }
        );
        openWebPage(webSite);
    }

    public void openWebPage(String webSite) {
        webView.loadUrl(webSite);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.advancePage:
                if (webView.canGoForward()) {
                    webView.goForward();
                } else {
                    Toast.makeText(this, "no can go forward", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.retreatPage:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    Toast.makeText(this, "no can go back", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.homePage:
                if(!webView.getUrl().toString().equals(DefaultWebSite))
                openWebPage(DefaultWebSite);
                break;
            case R.id.bookMark:
                startActivityForResult(new Intent(this,BookMarksActivity.class),BOOKMARK_REQUEST);
                break;
            case R.id.refreshPage:
                webView.reload();
                break;
            case R.id.collectPage:
                BrowseAssBookMarks bookMarks = new BrowseAssBookMarks();
                bookMarks.setId("1");
                bookMarks.setWebSite(webView.getUrl());
                bookMarks.setSaveDate(getTime());
                bookMarks.setWebSiteIcon("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png");
                addToPhotoTable(bookMarks);
                break;
        }
    }
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult( requestCode, resultCode,data);
        if(data != null && data.getStringExtra("") != null &&!"".equals(data.getStringExtra("")) ){
            openWebPage(data.getStringExtra("webSite"));
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openInputWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    public void closeInputWindow() {
        editText.setCursorVisible(false);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                openWebPage(editText.getText().toString());
                closeInputWindow();
                break;
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.editText:
                if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                    editText.setCursorVisible(true);
                }
                break;
        }
        return false;
    }
    public void addToPhotoTable(BrowseAssBookMarks browseAssBookMarks)
    {
        daoSession.getBrowseAssBookMarksDao().insert(browseAssBookMarks);
    }
}
