package com.bernie.browseass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bernie.browseass.R;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.BaseUiListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements OnClickListener {
    Button qqLogin;
    private Tencent mTencent;
    private final int LOGIN_RESULT = 4;
    private UserInfo mInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTencent = BrowserAssApplication.getTencent();
        qqLogin = (Button) findViewById(R.id.qqLogin);
        qqLogin.setOnClickListener(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            mTencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            mTencent.setOpenId(values.optString("openid"));
            mTencent.setAccessToken(values.optString("access_token"), values.optString(Constants.PARAM_EXPIRES_IN));
            if (mTencent != null && mTencent.isSessionValid()) {
                mInfo = new UserInfo(getApplicationContext(), mTencent.getQQToken());
                mInfo.getUserInfo(listener);
            } else {
            }
        }
    };
    IUiListener listener = new IUiListener() {
        @Override
        public void onError(UiError e) {
        }
        @Override
        public void onComplete(final Object response) {
            JSONObject jsonObject = (JSONObject) response;
            Intent intent = new Intent();
            intent.putExtra("nickname", jsonObject.optString("nickname"));
            intent.putExtra("headIcon", jsonObject.optString("figureurl_qq_2"));
            setResult(LOGIN_RESULT, intent);
            LoginActivity.this.finish();
        }
        @Override
        public void onCancel() {
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qqLogin:
                mTencent.login(this, "all", loginListener);
                break;
        }
    }
}

