package com.example.andrearodriguez.tipcalcu;

import android.app.Application;

/**
 * Created by andrearodriguez on 6/3/16.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL = "https://github.com/liyi85";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
