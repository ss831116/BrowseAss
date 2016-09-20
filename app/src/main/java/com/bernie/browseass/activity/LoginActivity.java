package com.bernie.browseass.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.application.BrowserAssApplication;
import com.bernie.browseass.listener.BaseUiListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
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
    SimpleDraweeView gitImage;
    TextView qqLogin;
    private Tencent mTencent;
    private final int LOGIN_RESULT = 4;
    private UserInfo mInfo;
    ImageView backImage;
    String gitPath = "http://img3.imgtn.bdimg.com/it/u=519728998,1542515417&fm=21&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTencent = BrowserAssApplication.getTencent();
        initView();
    }
    public void initView(){
        qqLogin = (TextView) findViewById(R.id.qqLogin);
        gitImage = (SimpleDraweeView) findViewById(R.id.gitImage);
        backImage= (ImageView)findViewById(R.id.backImage);
        qqLogin.setOnClickListener(this);
        backImage.setOnClickListener(this);
        Uri uri = Uri.parse(gitPath);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        gitImage.setController(controller);
        Animatable animatable = gitImage.getController().getAnimatable();
        if (animatable != null) {
            animatable.start();
        }
        Log.d("shifuqiang",animatable+"");
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
            case R.id.backImage:
                finish();
                break;
        }
    }
}

