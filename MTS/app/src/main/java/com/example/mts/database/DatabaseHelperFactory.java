package com.example.mts.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Фабрика для создания одного экземпляра класса DatabaseHelper.
 */
public class DatabaseHelperFactory {
    /**
     * Экземпляр класса DatabaseHelper.
     */
    private static DatabaseHelper helper;

    /**
     * Возвращает экземпляр класса DatabaseHelper.
     * @return экземпляр класса DatabaseHelper.
     */
    public static DatabaseHelper getHelper(){
        return helper;
    }

    /**
     * Задаёт экземлпяр класса DatabaseHelper.
     * @param context текущий контекст, откуда берётся объект DatabaseHelper.
     */
    public static void setHelper(Context context){
        helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    /**
     * Освобождает объект DatabaseHelper.
     */
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        helper = null;
    }
}
