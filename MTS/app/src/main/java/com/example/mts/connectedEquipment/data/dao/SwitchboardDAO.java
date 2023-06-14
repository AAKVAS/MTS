package com.example.mts.connectedEquipment.data.dao;

import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO-класс для доступа к электрическим щиткам.
 */
public class SwitchboardDAO extends BaseDaoImpl<Switchboard, Integer>  {
    public SwitchboardDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Switchboard.class);
    }
}
