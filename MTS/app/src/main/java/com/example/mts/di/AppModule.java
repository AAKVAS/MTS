package com.example.mts.di;

import android.content.Context;

import androidx.annotation.NonNull;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.support.ConnectionSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль приложения, используется для внедрения зависимостей.
 */
@Module
public class AppModule {

    /**
     * Контекст приложения.
     */
    private final Context context;

    /**
     * Конструктор класса AppModule.
     * @param context Контекст приложения.
     */
    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    /**
     * Возвращает контекст приложения.
     * @return контекст приложения.
     */
    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    /**
     * Возвращает источник соединения с БД.
     * @param context контекст приложения.
     * @return источник соединения с БД.
     */
    @Provides
    @Singleton
    public ConnectionSource connectionSource(Context context) {
        return OpenHelperManager.getHelper(context).getConnectionSource();
    }
}
