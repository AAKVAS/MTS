package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "CABLE")
public class Cable {
    public static final String UID = "UID";
    public static final String NAME = "NAME";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(columnName = NAME)
    private String name;

    @ForeignCollectionField()
    private List<ConnectedEquipment> connectedEquipmentList;

    public Cable() {}

    public Cable(int id, String name, List<ConnectedEquipment> connectedEquipmentList) {
        this.id = id;
        this.name = name;
        this.connectedEquipmentList = connectedEquipmentList;
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

    public List<ConnectedEquipment> getConnectedEquipmentList() {
        return connectedEquipmentList;
    }

    public void setConnectedEquipmentList(List<ConnectedEquipment> connectedEquipmentList) {
        this.connectedEquipmentList = connectedEquipmentList;
    }
}
