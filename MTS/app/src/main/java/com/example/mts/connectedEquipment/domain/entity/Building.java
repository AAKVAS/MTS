package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "BUILDING")
public class Building implements Serializable {
    public static final String UID = "UID";
    public static final String ADDRESS = "ADDRESS";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(columnName = ADDRESS)
    private String address;

    @ForeignCollectionField(eager = true)
    private Collection<Switchboard> switchboardList;

    public Building() {}

    public Building(int id, String address, List<Switchboard> switchboardList) {
        this.id = id;
        this.address = address;
        this.switchboardList = switchboardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building that = (Building) o;
        return id == that.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Switchboard> getSwitchboardList() {
        return switchboardList;
    }

    public void setSwitchboardList(List<Switchboard> switchboardList) {
        this.switchboardList = switchboardList;
    }
}
