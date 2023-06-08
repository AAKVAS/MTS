package com.example.mts.connectedEquipment.data.dao;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO-класс для работы с подключённым оборудованием.
 */
public class DAOConnectedEquipment extends BaseDaoImpl<ConnectedEquipment, Integer> {
    public DAOConnectedEquipment(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, ConnectedEquipment.class);
    }
}
