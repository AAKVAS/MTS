package com.example.mts.modules.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MODULES")
public class Module {
    public static final String UID = "UID";
    public static final String NAME = "NAME";

    @DatabaseField(generatedId = true, columnName = UID)
    private int id;

    @DatabaseField(columnName = NAME)
    private String name;

    public Module() {}

    public Module(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
