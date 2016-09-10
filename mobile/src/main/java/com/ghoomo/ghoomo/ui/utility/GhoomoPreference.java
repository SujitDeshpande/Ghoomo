

package com.ghoomo.ghoomo.ui.utility;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Agoel on 02-09-2016.
 */
public class GhoomoPreference {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    // GhoomoConstant key for Preference
    private static GhoomoPreference instance;

    private static final String KEY_LETSGANG_PREFS = "letsGang_preference";
    public static final String KEY_IS_FIRST_TIME_LOGIN = "IS_FIRST_TIME_LOGIN";

    private SharedPreferences sharedPrefs;
    private Editor prefsEditor;

    private GhoomoPreference(Context context) {
        sharedPrefs = context.getSharedPreferences(KEY_LETSGANG_PREFS,
                Activity.MODE_PRIVATE);
        prefsEditor = sharedPrefs.edit();
    }

    public static void init(Application application) {
        if (instance == null) {
            instance = new GhoomoPreference(application.getApplicationContext());
        }
    }

    public static GhoomoPreference getInstance() {
        if (instance == null) {
            throw new RuntimeException(
                    "Must run init(Application application) before an instance can be obtained");
        }
        return instance;
    }

    public String getStringValue(String key, String defaultvalue) {
        return sharedPrefs.getString(key, defaultvalue);
    }

    public void setStringValue(String key, String value) {
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public int getIntValue(String key, int defaultvalue) {
        return sharedPrefs.getInt(key, defaultvalue);
    }

    public void setIntValue(String key, int value) {
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }

    public boolean getBooleanValue(String key, Boolean defaultvalue) {
        return sharedPrefs.getBoolean(key, defaultvalue);
    }

    public void setBooleanValue(String key, boolean value) {
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public void clearPrefrences() {
        sharedPrefs.edit().clear().commit();
    }
}
