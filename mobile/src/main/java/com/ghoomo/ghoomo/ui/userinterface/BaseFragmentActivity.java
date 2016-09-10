
package com.ghoomo.ghoomo.ui.userinterface;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.ghoomo.ghoomo.R;
import com.ghoomo.ghoomo.ui.GhoomoApplication;
import com.ghoomo.ghoomo.ui.utility.GhoomoConstant;


/**
 * base fragment activity for all activity having common methods like progress bar alerts etc
 */
public abstract class BaseFragmentActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private ProgressDialog progressDialog = null;
    private String progressMessage = null;
    private String alertMessage = null;
    private int alertCode = -1;
    private Handler handler;
    private AlertDialog alertDialog = null;
    protected Resources res;
    private String[] strBtnArray = null;

    private boolean isprogress = false;
    protected Toolbar toolbar;
    boolean isRunning = false;
    private TextView timeTextView;

    /**
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        supportInvalidateOptionsMenu();
        // ScreenController.setScreen(this);
        // this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        res = getResources();
        handler = new Handler();

    }

    @Override
    protected void onResume() {
        super.onResume();

//        System.out.println("ON RESUME BASE ********************");

        if (isprogress) {
            showProgressDialog(this, progressMessage);
        }
    }

    public void setContentView(int layoutResID) {

        super.setContentView(layoutResID);
        if (alertDialog != null) {
            alertDialog.show();
        } else if (progressDialog != null) {
            progressDialog.show();
        }

    }

    public Object onRetainCustomNonConfigurationInstance() {

        cancelAlert();
        return alertDialog;
    }

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (progressDialog != null && progressDialog.isShowing()) {
            outState.putString("progressDialogMessage", progressMessage);
            outState.putBoolean("IsProgressDailog", true);
            isprogress = true;
            progressDialog.dismiss();
        }
        if (alertDialog != null && alertDialog.isShowing()) {

            outState.putString("alertMessage", alertMessage);
            outState.putInt("alertCode", alertCode);
            outState.putBoolean("IsAlertDailog", true);
            outState.putStringArray("HasTwoButtons", strBtnArray);
            strBtnArray = null;
            cancelAlert();
        }

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            if (savedInstanceState.getBoolean("IsProgressDailog")) {

                String proMsg = savedInstanceState.getString("progressDialogMessage");
                showProgressDialog(this, proMsg);
            }

            if (savedInstanceState.getBoolean("IsAlertDailog")) {

                String alertMsg = savedInstanceState.getString("alertMessage");
                int alertCode = savedInstanceState.getInt("alertCode");
                String[] btnArray = savedInstanceState.getStringArray("HasTwoButtons");
                if (null != btnArray) {
                    showAlert(alertMsg, alertCode, btnArray);

                } else {
                    showAlert(alertMsg, "", alertCode);
                }

            }

        }
    }

    public void changeProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
        BaseFragmentActivity.this.runOnUiThread(changeMessage);

    }

    private Runnable changeMessage = new Runnable() {
        @Override
        public void run() {
            // Log.v(TAG, strCharacters);
            // progressDialog.setMessage(progressMessage);
            progressDialog.setTitle(progressMessage);
        }
    };

    public void showProgressDialog(Context context, String message) {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.cancel();

        progressDialog = null;
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(message);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        /*
         * if (progressDialog != null && progressDialog.isShowing()) progressDialog.cancel();
		 *
		 * progressMessage = message;
		 *
		 * progressDialog = null; progressDialog = ProgressDialog.show(BaseFragmentActivity.this, null, null);
		 * progressDialog.setContentView(R.layout.progressbar_activity); ((TextView) progressDialog.findViewById(R.id.textmsg)).setText(message);
		 * progressDialog.setMessage("Please wait..."); progressDialog.setCancelable(false);
		 *
		 * progressDialog.show();
		 */
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            isprogress = false;
            progressDialog.cancel();
            progressDialog = null;
        }
    }

    protected void moveBackTo(Class<?> prevActivity) {
        // cancelAlert();
        // isRunning = true;
        Intent it = new Intent(this, prevActivity);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);

    }

    public void moveNextTo(Class<?> nextActivity) {
        // cancelAlert();
        // isRunning = true;// for login Dialog

        Intent it = new Intent(this, nextActivity);
        it.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(it);

    }

    public void moveNextTo(Intent it) {
        startActivity(it);

    }

    public void moveNextToAndFinish(Intent it) {
        moveNextTo(it);
        finish();
    }

    public void moveNextToAndFinish(Class<?> nextActivity) {
        moveNextTo(nextActivity);
        finish();
    }

    public void showAlert(String message, final String errorCode, final int alertCode) {

        alertMessage = message;
        this.alertCode = alertCode;

        handler.post(new Runnable() {

            public void run() {
                final Dialog dialog = new Dialog(BaseFragmentActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert);

                dialog.setCancelable(false);
                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.title);
                text.setText("");

                TextView msgTxt = (TextView) dialog.findViewById(R.id.msg);
                msgTxt.setText(alertMessage);
                GhoomoApplication.getInstance().setTextTypeface(msgTxt, GhoomoConstant.LIGHT_FONT);

                Button dialogButton = (Button) dialog.findViewById(R.id.ok_btn);

                dialogButton.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub

                        dialog.dismiss();
                    }
                });
                // if button is clicked, close the custom dialog

                if (!(BaseFragmentActivity.this).isFinishing()) {
                    dialog.show();
                }
                /*
                 * alertDialog = new AlertDialog.Builder(BaseFragmentActivity.this).create(); alertDialog.setContentView(R.layout.custom_alert_dailog);
				 * alertDialog.setTitle("Alert"); alertDialog.setMessage(alertMessage); alertDialog.setCancelable(false); alertDialog.setButton("OK",
				 * BaseFragmentActivity.this); alertDialog.show();
				 */

            }
        });

    }

    public void setProgressMsg(String msg) {

        progressDialog.setTitle(msg);

    }

    public void showAlert(String message, final int alertCode, final String[] button) {
        alertMessage = message;
        this.alertCode = alertCode;
        strBtnArray = button;
        handler.post(new Runnable() {

            public void run() {

                final Dialog dialog = new Dialog(BaseFragmentActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_two_button_dialog);
                dialog.setCancelable(false);
                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.title);
                text.setText("");
                GhoomoApplication.getInstance().setTextTypeface(text, GhoomoConstant.LIGHT_FONT);

                TextView msgTxt = (TextView) dialog.findViewById(R.id.msg);
                msgTxt.setText(alertMessage);
                GhoomoApplication.getInstance().setTextTypeface(msgTxt, GhoomoConstant.LIGHT_FONT);


                Button okdialogButton = (Button) dialog.findViewById(R.id.ok_btn);
                okdialogButton.setText(strBtnArray[0]);
                okdialogButton.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        onDialogButtonClick(dialog, AlertDialog.BUTTON_POSITIVE, alertCode);
                        dialog.dismiss();
                    }
                });
                Button canceldialogButton = (Button) dialog.findViewById(R.id.cancel_btn);
                canceldialogButton.setText(strBtnArray[1]);
                canceldialogButton.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        onDialogButtonClick(dialog, AlertDialog.BUTTON_NEGATIVE, alertCode);
                        dialog.dismiss();
                    }
                });
                // if button is clicked, close the custom dialog

                dialog.show();
                /*
                 * alertDialog = new AlertDialog.Builder(BaseFragmentActivity.this) .create(); alertDialog.setTitle("Alert");
				 * alertDialog.setMessage(alertMessage); alertDialog.setCancelable(false); alertDialog.setButton(button[0], BaseFragmentActivity.this);
				 * alertDialog.setButton2(button[1], BaseFragmentActivity.this); alertDialog.show();
				 */
            }
        });
    }

    private void cancelAlert() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.cancel();
            alertDialog = null;
            System.out.println("BaseActivity.cancelAlert() " + alertDialog);
        }

    }

    public final void onClick(final DialogInterface dialog, final int which) {

        System.out.println("BaseActivity.onClick(final DialogInterface dialog, final int which)");
        // eventTime = System.currentTimeMillis();
        onDialogButtonClick(dialog, which, alertCode);
        // alertDialog.dismiss();
        // alertDialog = null;
        alertCode = -1;

    }

    public abstract void onDialogButtonClick(DialogInterface dialog, int which, int alertCode);

    public void closeKeyborad() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onUserInteraction() {

    }

    protected void setActionBar(int titleResourceId, int description, String nextDone, boolean displayShowHomeEnabled, boolean isBackEnabled) {
        System.out.println("setActionBar  .                     1 1 1 1 1");
        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(displayShowHomeEnabled);
            getSupportActionBar().setTitle("");
            if (isBackEnabled) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        toolbarTitle.setText(getString(titleResourceId));
        toolbarTitle.setVisibility(View.VISIBLE);

        GhoomoApplication.getInstance().setTextTypeface(toolbarTitle, GhoomoConstant.LIGHT_FONT);

        TextView toolbarDescription = (TextView) findViewById(R.id.toolbar_description);
        if (description != 0) {

            toolbarDescription.setVisibility(View.VISIBLE);
//            toolbarDescription.setText(getString(titleResourceId));
            toolbarDescription.setText("" + description);
        } else {
            toolbarDescription.setVisibility(View.GONE);
        }
        GhoomoApplication.getInstance().setTextTypeface(toolbarDescription, GhoomoConstant.LIGHT_FONT);

        Button nextDoneButton = (Button) findViewById(R.id.next_done_btn);
        GhoomoApplication.getInstance().setTextTypeface(nextDoneButton, GhoomoConstant.REGULAR_FONT);
        if (!nextDone.equals("")) {
            nextDoneButton.setText(nextDone);
            nextDoneButton.setVisibility(View.VISIBLE);
        } else {
            nextDoneButton.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    protected SpannableStringBuilder appendRedAsterik(String simple) {
        String colored = "*";
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

}


