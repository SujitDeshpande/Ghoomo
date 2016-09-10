package com.ghoomo.ghoomo.ui.userinterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.ghoomo.ghoomo.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;


/**
 * Login activity
 */
public class LoginActivity extends BaseFragmentActivity {

    // edit text for phone number
    private EditText mPhoneNumber_EditTExt;
    // editor for phone number
    private String mPhoneNumberEditor;


    private AuthCallback authCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TwitterAuthConfig authConfig = new TwitterAuthConfig("CKEihbEh3LgzDuaQov81oIKP7", "emKomVQZTmzTRMBaG0axVoK7JRYhIMB5wpz4HxhT9Li4xAlM48");
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        authCallback = new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                Toast.makeText(LoginActivity.this,
                        "Authentication Successful for " + phoneNumber, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, LandingGridActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(DigitsException exception) {
                // Do something on failure
            }
        };
        initUiComponents();
    }

    /**
     * initilaize ui components
     */
    private void initUiComponents() {

        mPhoneNumber_EditTExt = (EditText) findViewById(R.id.input_phonenumber);
        if (mPhoneNumber_EditTExt != null) {
            mPhoneNumber_EditTExt.requestFocus();
        }

        mPhoneNumber_EditTExt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    mPhoneNumberEditor = s.toString();
                    if (mPhoneNumberEditor.length() == 0 ) {
                        // show toast or snakbar
//                        Snackbar snackbar = Snackbar
//                                .make(mPhoneNumber_EditTExt, "Welcome to AndroidHive", Snackbar.LENGTH_SHORT);
//
//                        snackbar.show();
                    }
                }
            }
        });

        /*
      variable for button on click submit
     */
        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(authCallback);
        digitsButton.setAuthTheme(android.R.style.Theme_Material);
        digitsButton.setBackgroundColor(Color.parseColor("#ff398622"));
        digitsButton.setText("Login");
    }

    @Override
    public void onDialogButtonClick(DialogInterface dialog, int which, int alertCode) {

    }


    private boolean checkLocal() {

            return true;
    }
}
