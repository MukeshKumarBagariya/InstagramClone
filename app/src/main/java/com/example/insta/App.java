package com.example.insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("cKnIShVEUNBdjLSHb46mzJ8ZbMtnItkB8KZbQZPp")
                // if defined
                .clientKey("7tvpRDzAE7pRUltNtd2hA9sKMSmtBSChBBWZwg4n")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}