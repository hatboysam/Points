package com.habosa.points;

import android.app.Application;

import com.parse.Parse;

public class PointsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Parse Data Storage
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parse_appid), getString(R.string.parse_appkey));
    }

}
