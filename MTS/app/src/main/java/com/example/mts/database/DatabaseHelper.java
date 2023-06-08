package com.example.mts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;
import com.example.mts.modules.domain.entity.Module;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "mts.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Building.class);
            TableUtils.createTable(connectionSource, SwitchboardModel.class);
            TableUtils.createTable(connectionSource, Switchboard.class);
            TableUtils.createTable(connectionSource, Cable.class);
            TableUtils.createTable(connectionSource, ConnectedEquipment.class);
            TableUtils.createTable(connectionSource, Module.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            switch (oldVersion) {
                case 0:
                    TableUtils.createTable(connectionSource, Building.class);
                    TableUtils.createTable(connectionSource, SwitchboardModel.class);
                    TableUtils.createTable(connectionSource, Switchboard.class);
                    TableUtils.createTable(connectionSource, Cable.class);
                    TableUtils.createTable(connectionSource, ConnectedEquipment.class);
                    TableUtils.createTable(connectionSource, Module.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}