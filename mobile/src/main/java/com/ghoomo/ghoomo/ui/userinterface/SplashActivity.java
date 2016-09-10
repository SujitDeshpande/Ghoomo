package com.ghoomo.ghoomo.ui.userinterface;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import com.ghoomo.ghoomo.R;

/**
 * splash screen fro loading configurations
 */
public class SplashActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
