package com.ghoomo.ghoomo.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.ghoomo.ghoomo.ui.utility.GhoomoConstant;
import com.ghoomo.ghoomo.ui.utility.GhoomoPreference;

/**
 * Created by Agoel on 10-09-2016.
 */
public class GhoomoApplication extends Application {

    private static GhoomoApplication instance;

    // bold roboto font
    private static Typeface boldFont;
    // light roboto font
    private static Typeface lightFont;
    // medium roboto font
    private static Typeface mediumFont;
    // regular roboto font
    private static Typeface regularFont;

//    private BajajSQLiteHelper mBajajSQLiteHelper;
//
//    HashMap<String, ArrayList<ShortageTypes>> stringArrayListHashMap = new HashMap<>();

//    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;

    public static final String TAG = GhoomoApplication.class
            .getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        GhoomoPreference.init(this);

        instance = this;

        getBoldFont();
        getLightFont();
        getMediumFont();
        getRegularFont();

    }

    /**
     * Gets the app context.
     *
     * @return the main context of the Application
     */
    public static Context getAppContext() {
        return instance;
    }

    /**
     * Gets the app resources.
     *
     * @return the main resources from the Application
     */
//    public static Resources getAppResources() {
//
//        return instance.getResources();
//    }
    public static GhoomoApplication getInstance() {
        return instance;
    }

    public static void setInstance(GhoomoApplication instance) {
        GhoomoApplication.instance = instance;
    }

    // -- Fonts -- //
    public void setTextTypeface(TextView textView, int fontType) {
        if (textView != null) {
            switch (fontType) {
                case GhoomoConstant.BOLD_FONT:
                    textView.setTypeface(getBoldFont());
                    break;
                case GhoomoConstant.LIGHT_FONT:
                    textView.setTypeface(getLightFont());
                    break;
                case GhoomoConstant.MEDIUM_FONT:
                    textView.setTypeface(getMediumFont());
                    break;
                case GhoomoConstant.REGULAR_FONT:
                    textView.setTypeface(getRegularFont());
                    break;
                default:
                    textView.setTypeface(getRegularFont());
            }
        }
    }

    /**
     * bold roboto font
     *
     * @return
     */
    private Typeface getBoldFont() {
        if (boldFont == null) {
            boldFont = Typeface.createFromAsset(getAssets(), "fonts/roboto_bold.ttf");
        }
        return this.boldFont;
    }

    /**
     * light roboto font
     *
     * @return
     */
    private Typeface getLightFont() {
        if (lightFont == null) {
            lightFont = Typeface.createFromAsset(getAppContext().getAssets(), "fonts/roboto_light.ttf");
        }
        return this.lightFont;
    }

    /**
     * medium roboto font
     *
     * @return
     */
    private Typeface getMediumFont() {
        if (mediumFont == null) {
            mediumFont = Typeface.createFromAsset(getAppContext().getAssets(), "fonts/roboto_medium.ttf");
        }
        return this.mediumFont;
    }

    /**
     * regular roboto font
     *
     * @return
     */
    private Typeface getRegularFont() {
        if (regularFont == null) {
            regularFont = Typeface.createFromAsset(getAppContext().getAssets(), "fonts/roboto_regular.ttf");
        }
        return this.regularFont;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        unregisterReceiver(new NetworkReciever());
    }
}
