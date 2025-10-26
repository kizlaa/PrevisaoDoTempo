package com.lucas.previsaodotempo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {
    public static void setCity(Context ctx, String city) {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        sp.edit().putString(Constants.KEY_CITY, city).apply();
    }

    public static String getCity(Context ctx, String def) {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        return sp.getString(Constants.KEY_CITY, def);
    }
}
