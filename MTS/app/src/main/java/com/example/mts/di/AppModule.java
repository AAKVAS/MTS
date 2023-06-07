package com.example.mts.di;

import android.content.Context;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.ConnectionSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public ConnectionSource connectionSource(Context context) {
        return OpenHelperManager.getHelper(context).getConnectionSource();
    }
}
