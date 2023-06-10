package com.example.mts.connectedEquipment.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CONNECTED_EQUIPMENT")
public class ConnectedEquipment {
    public static final String UID = "UID";
    public static final String PORT_NUMBER = "PORT_NUMBER";
    public static final String CABLE_LENGTH = "CABLE_LENGTH";
    public static final String IP = "IP";
    public static final String MAC = "MAC";

    @DatabaseField(columnName = UID, generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Switchboard switchboard;

    @DatabaseField(columnName = PORT_NUMBER)
    private int portNumber;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Cable cable;

    @DatabaseField(columnName = CABLE_LENGTH)
    private int cableLength;

    @DatabaseField(columnName = IP)
    private String ip;

    @DatabaseField(columnName = MAC)
    private String mac;

    public ConnectedEquipment() {}

    public ConnectedEquipment(int id, Switchboard switchboard, int portNumber, Cable cable, int cableLength, String ip, String mac) {
        this.id = id;
        this.switchboard = switchboard;
        this.portNumber = portNumber;
        this.cable = cable;
        this.cableLength = cableLength;
        this.ip = ip;
        this.mac = mac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Switchboard getSwitchboard() {
        return switchboard;
    }

    public void setSwitchboard(Switchboard switchboard) {
        this.switchboard = switchboard;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public Cable getCable() {
        return cable;
    }

    public void setCable(Cable cable) {
        this.cable = cable;
    }

    public int getCableLength() {
        return cableLength;
    }

    public void setCableLength(int cableLength) {
        this.cableLength = cableLength;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
