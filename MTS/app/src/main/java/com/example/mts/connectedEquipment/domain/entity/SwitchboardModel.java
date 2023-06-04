package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "SWITCHBOARD_MODEL")
public class SwitchboardModel {
    public static final String UID = "UID";
    public static final String NAME = "NAME";
    public static final String MAX_PORT_AMOUNT = "MAX_PORT_AMOUNT";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = MAX_PORT_AMOUNT)
    private int maxPortAmount;

    @ForeignCollectionField()
    private List<Switchboard> switchboardList;

    public SwitchboardModel() {}

    public SwitchboardModel(int id, String name, int maxPortAmount, List<Switchboard> switchboardList) {
        this.id = id;
        this.name = name;
        this.maxPortAmount = maxPortAmount;
        this.switchboardList = switchboardList;
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

    public int getMaxPortAmount() {
        return maxPortAmount;
    }

    public void setMaxPortAmount(int maxPortAmount) {
        this.maxPortAmount = maxPortAmount;
    }

    public List<Switchboard> getSwitchboardList() {
        return switchboardList;
    }

    public void setSwitchboardList(List<Switchboard> switchboardList) {
        this.switchboardList = switchboardList;
    }
}
