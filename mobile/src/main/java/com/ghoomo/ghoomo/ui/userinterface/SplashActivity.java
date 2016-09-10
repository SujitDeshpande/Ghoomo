package com.ghoomo.ghoomo.ui.userinterface;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import com.ghoomo.ghoomo.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;

/**
 * splash screen fro loading configurations
 */
public class SplashActivity extends BaseFragmentActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "CKEihbEh3LgzDuaQov81oIKP7";
    private static final String TWITTER_SECRET = "emKomVQZTmzTRMBaG0axVoK7JRYhIMB5wpz4HxhT9Li4xAlM48";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        //Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveNextToAndFinish(LoginActivity.class);
            }
        }, 4000);
    }

    @Override
    public void onDialogButtonClick(DialogInterface dialog, int which, int alertCode) {

    }
}
