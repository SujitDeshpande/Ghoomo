package com.ghoomo.ghoomo.ui.userinterface;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ghoomo.ghoomo.R;


/**
 * Login activity
 */
public class LoginActivity extends BaseFragmentActivity implements View.OnClickListener {

    // edit text for phone number
    private EditText mPhoneNumber_EditTExt;
    // edittext for password
    private EditText mPassword_EditText;
    // editor for phone number
    private String mPhoneNumberEditor;
    // editor for password
    private String mPasswordEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    if (mPhoneNumberEditor.length() == 0 && mPasswordEditor.length() == 0) {
                        // show toast or snakbar
//                        Snackbar snackbar = Snackbar
//                                .make(mPhoneNumber_EditTExt, "Welcome to AndroidHive", Snackbar.LENGTH_SHORT);
//
//                        snackbar.show();
                    }
                }
            }
        });
        mPassword_EditText = (EditText) findViewById(R.id.input_password);
        if (mPassword_EditText != null) {
            mPassword_EditText.setTypeface(Typeface.DEFAULT);
        }
        mPassword_EditText.setTransformationMethod(new PasswordTransformationMethod());
        mPassword_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mPasswordEditor = s.toString();
                    if (mPhoneNumberEditor.length() == 0 && mPasswordEditor.length() == 0) {
                        // show taiost or snakbar

//                        Snackbar snackbar = Snackbar
//                                .make(mPassword_EditText, "Welcome to AndroidHive", Snackbar.LENGTH_SHORT);
//
//                        snackbar.show();
                    }
                }
            }
        });
        /*
      variable for button on click submit
     */
        Button mSubmitButton = (Button) findViewById(R.id.submit_login_btn);
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onDialogButtonClick(DialogInterface dialog, int which, int alertCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_login_btn:
                if (checkLocal()) {
                    Snackbar snackbar = Snackbar
                            .make(mPhoneNumber_EditTExt, "Welcome to Lets Gang", Snackbar.LENGTH_SHORT);

                    snackbar.show();

//                    moveNextToAndFinish(LandingActivity.class);
                } else {
                    Snackbar snackbar = Snackbar
                            .make(mPhoneNumber_EditTExt, "Please fill username/password.", Snackbar.LENGTH_SHORT);

                    snackbar.show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * check local fields
     *
     * @return
     */
    private boolean checkLocal() {

        if (TextUtils.isEmpty(mPhoneNumber_EditTExt.getText().toString()) || (TextUtils.isEmpty(mPassword_EditText.getText().toString()))) {
            return false;
        } else {

            return true;
        }
    }
}
