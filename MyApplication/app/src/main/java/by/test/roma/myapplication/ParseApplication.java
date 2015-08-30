package by.test.roma.myapplication;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Roma on 29.08.2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(getApplicationContext(), "HX6n9WMdhKg5BPhC7d22IKHV34jyTst5OHQxtxUD", "U9DotgCbByVP4eZ9oFae61w3Hnno0xFUgefYJOq4");
    }
}