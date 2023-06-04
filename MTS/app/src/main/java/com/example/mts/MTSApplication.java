package com.example.mts;

import android.app.Application;

import com.example.mts.database.DatabaseHelperFactory;
import com.example.mts.di.AppComponent;
import com.example.mts.di.AppModule;
import com.example.mts.di.DaggerAppComponent;


public class MTSApplication extends Application {

    protected static MTSApplication instance;

    public static MTSApplication getInstance() {
        return instance;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        DatabaseHelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        DatabaseHelperFactory.releaseHelper();
        super.onTerminate();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}