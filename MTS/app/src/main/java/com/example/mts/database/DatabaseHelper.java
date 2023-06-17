package com.example.mts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Класс, настраивающий базу данных при установке или обновлении приложения.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /**
     * Имя базы данных.
     */
    private static final String DATABASE_NAME = "mts.db";

    /**
     * Версия базы данных.
     */
    private static final int DATABASE_VERSION = 1;


    /**
     * Конструктор класса DatabaseHelper.
     * @param context текущий контекст.
     */
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

            insertConnectedEquipment(db);
        } catch (Exception e) {
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

                    insertConnectedEquipment(db);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Добавляет начальные записи в базу.
     * @param db база данных.
     */
    private void insertConnectedEquipment(SQLiteDatabase db) {
        db.execSQL("INSERT INTO building (address) VALUES ('Вологда, Молодёжная 25а')");
        db.execSQL("INSERT INTO building (address) VALUES ('Вологда, Пошехонское ш. 22')");
        db.execSQL("INSERT INTO building (address) VALUES ('Вологда, Горького 122')");
        db.execSQL("INSERT INTO building (address) VALUES ('Вологда, Ильюшина 8')");

        db.execSQL("INSERT INTO switchboard_model (name, max_port_amount) VALUES ('EKF mb11-48', 48)");
        db.execSQL("INSERT INTO switchboard_model (name, max_port_amount) VALUES ('EKF mb11-36', 36)");
        db.execSQL("INSERT INTO switchboard_model (name, max_port_amount) VALUES ('ЩРв-36з-0 36 ', 36)");

        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (1, 1, 'dfh4h5u')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (1, 1, 'hrve4t5')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (1, 1, 'nfg5g43')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (2, 1, '9545cvd')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (3, 3, 'a667gsd')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (3, 3, 'vddd55v')");
        db.execSQL("INSERT INTO switchboard (building_id, model_id, inventory_number) VALUES (4, 2, 'bb67gsa')");

        db.execSQL("INSERT INTO cable (name) VALUES ('МКЭШ 3х0.35 мм2')");
        db.execSQL("INSERT INTO cable (name) VALUES ('МГТФ 1.0 кв.мм')");
        db.execSQL("INSERT INTO cable (name) VALUES ('НВ 0.2мм. кв.')");

        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (1, 1, 1, 25, '112.24.47.8', '8A:08:E5:CF:D0:FC')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (1, 2, 1, 24, '112.24.47.9', '99:B8:CC:86:95:DF')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (2, 3, 1, 25, '112.24.47.10', '99:B8:81:B7:A3:21')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (3, 4, 1, 27, '112.24.47.11', '99:81:B7:A3:2C:B3')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (4, 5, 1, 37, '112.24.47.14', '91:E2:0B:24:3B:15')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (5, 6, 1, 17, '112.24.47.15', '21:D6:EF:52:A4:FD')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (1, 7, 1, 12, '112.24.47.17', '2F:17:3A:C3:01:CC')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (6, 8, 1, 12, '112.24.47.20', 'DD:6D:59:B5:04:0E')");
        db.execSQL("INSERT INTO connected_equipment (switchboard_id, port_number, cable_id, cable_length, ip, mac) VALUES (7, 9, 1, 22, '112.24.47.21', 'D5:AA:34:0B:FF:82')");
    }
}