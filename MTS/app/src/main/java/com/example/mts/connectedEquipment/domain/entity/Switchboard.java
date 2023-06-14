package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "SWITCHBOARD")
public class Switchboard implements Serializable {
    public static final String UID = "UID";
    public static final String INVENTORY_NUMBER = "INVENTORY_NUMBER";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(columnName = INVENTORY_NUMBER)
    private String inventoryNumber;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Building building;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SwitchboardModel model;

    @ForeignCollectionField()
    private Collection<ConnectedEquipment> connectedEquipmentList;

    public Switchboard() {}

    public Switchboard(int id, Building building, SwitchboardModel model, List<ConnectedEquipment> connectedEquipmentList) {
        this.id = id;
        this.building = building;
        this.model = model;
        this.connectedEquipmentList = connectedEquipmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Switchboard that = (Switchboard) o;
        return id == that.id;
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

    public Collection<ConnectedEquipment> getConnectedEquipmentList() {
        return connectedEquipmentList;
    }

    public void setConnectedEquipmentList(List<ConnectedEquipment> connectedEquipmentList) {
        this.connectedEquipmentList = connectedEquipmentList;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
}
