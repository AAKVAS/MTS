package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "SWITCHBOARD")
public class Switchboard {
    public static final String UID = "UID";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Building building;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SwitchboardModel model;

    @ForeignCollectionField()
    private List<ConnectedEquipment> connectedEquipmentList;

    public Switchboard(int id, Building building, SwitchboardModel model, List<ConnectedEquipment> connectedEquipmentList) {
        this.id = id;
        this.building = building;
        this.model = model;
        this.connectedEquipmentList = connectedEquipmentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public SwitchboardModel getModel() {
        return model;
    }

    public void setModel(SwitchboardModel model) {
        this.model = model;
    }

    public List<ConnectedEquipment> getConnectedEquipmentList() {
        return connectedEquipmentList;
    }

    public void setConnectedEquipmentList(List<ConnectedEquipment> connectedEquipmentList) {
        this.connectedEquipmentList = connectedEquipmentList;
    }
}
