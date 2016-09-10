package com.ghoomo.ghoomo.ui.userinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.core.TwitterAuthConfig;

import com.ghoomo.ghoomo.R;

public class Login extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "CKEihbEh3LgzDuaQov81oIKP7";
    private static final String TWITTER_SECRET = "emKomVQZTmzTRMBaG0axVoK7JRYhIMB5wpz4HxhT9Li4xAlM48";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        //Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_login);
    }
}
