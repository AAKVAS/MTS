package com.example.mts.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DatabaseHelperFactory {
    private static DatabaseHelper helper;

    public static DatabaseHelper getHelper(){
        return helper;
    }
    public static void setHelper(Context context){
        helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        helper = null;
    }
}