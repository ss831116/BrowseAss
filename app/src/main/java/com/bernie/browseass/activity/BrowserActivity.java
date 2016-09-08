package com.bernie.browseass.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
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
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bernie.browseass.R;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.utils.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bernie.greendao.dao.BrowseAssBookMarks;
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
    DaoSession daoSession;
    RelativeLayout bottomBar;
    ImageView camera, gallery;
    WebChromeClient.FileChooserParams fileChooserParams;
    ValueCallback<Uri[]> mFilePathCallback;
    private static final int FILE_CHOOSER_RESULT_CODE = 1;
    private static final int CASE_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        daoSession = BrowserAssApplication.instance.getDaoSession(getApplicationContext());
        sharedPreferences = getSharedPreferences("webPage", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initAllView();
    }

    @Override
    public void initView() {

    }


    public void initAllView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        webView = (WebView) findViewById(R.id.webView);
        editText = (EditText) findViewById(R.id.editText);
        advancePage = (ImageView) findViewById(R.id.advancePage);
        retreatPage = (ImageView) findViewById(R.id.retreatPage);
        homePage = (ImageView) findViewById(R.id.homePage);
        bookMark = (ImageView) findViewById(R.id.bookMark);
        refreshPage = (ImageView) findViewById(R.id.refreshPage);
        collectPage = (TextView) findViewById(R.id.collectPage);
        editText.setCursorVisible(false);
        bottomBar = (RelativeLayout) findViewById(R.id.bottomBar);
        camera = (ImageView) findViewById(R.id.camera);
        gallery = (ImageView) findViewById(R.id.gallery);
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
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        bottomBar.setOnClickListener(this);
    }

    public void setEditTextOnClickListener() {
        editText.setOnKeyListener(this);
        editText.setOnTouchListener(this);
    }

    public void webViewLoadHtml(String webSite) {
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         return false;
                                     }

                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                         return false;
                                     }

                                     public void onPageCommitVisible(WebView view, String url) {
                                         editor.putString("webSite", url);
                                         editor.commit();
                                         editText.setText(url);
                                     }
                                 }
        );
        webView.setWebChromeClient(new WebChromeClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePathCallback;
                if (bottomBar.getVisibility() != View.VISIBLE) {
                    bottomBar.setVisibility(View.VISIBLE);
                }
                BrowserActivity.this.fileChooserParams = fileChooserParams;
                return true;
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                Uri uri = Uri.parse(s);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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
                if (!webView.getUrl().toString().equals(DefaultWebSite))
                    openWebPage(DefaultWebSite);
                break;
            case R.id.bookMark:
                startActivityForResult(new Intent(this, BookMarksActivity.class), BOOKMARK_REQUEST);
                break;
            case R.id.refreshPage:
                webView.reload();
                break;
            case R.id.collectPage:
                BrowseAssBookMarks bookMarks = new BrowseAssBookMarks();
                bookMarks.setWebSite(webView.getUrl());
                bookMarks.setSaveDate(getTime());
                bookMarks.setWebSiteIcon("https://www.baidu.com/favicon.ico");
                addToPhotoTable(bookMarks);
                break;
            case R.id.camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CASE_IMAGE);
                break;
            case R.id.gallery:
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_CHOOSER_RESULT_CODE);
                break;
            case R.id.bottomBar:
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                    mFilePathCallback = null;
                }
                if (bottomBar.getVisibility() == View.VISIBLE) {
                    bottomBar.setVisibility(View.GONE);
                }
                break;
        }
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "resultCode = " + resultCode);
        if (bottomBar.getVisibility() == View.VISIBLE) {
            bottomBar.setVisibility(View.GONE);
        }
        if (resultCode == 2) {
            if (data != null && data.getStringExtra("webSite") != null && !"".equals(data.getStringExtra("webSite"))) {
                openWebPage(data.getStringExtra("webSite"));
            }
        } else if (resultCode == -1) {
            if (null == mFilePathCallback) {
                return;
            } else {
                Uri result = data == null ? null : data.getData();
                String path;
                if (result != null) {
                    path = FileUtils.getPath(this, result);
                    if (TextUtils.isEmpty(path)) {
                        mFilePathCallback.onReceiveValue(null);
                    } else {
                        Uri uri = Uri.fromFile(new File(path));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mFilePathCallback.onReceiveValue(new Uri[]{uri});
                        }
                    }
                } else {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = null;
            }
        } else if (resultCode == 0) {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
                mFilePathCallback = null;
            }
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
                openWebPage("https://" + editText.getText().toString() + "/");
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

    public void addToPhotoTable(BrowseAssBookMarks browseAssBookMarks) {
        daoSession.getBrowseAssBookMarksDao().insert(browseAssBookMarks);
    }
}
