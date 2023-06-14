package com.example.mts.connectedEquipment.data.dao;

import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO-класс для доступа к моделям кабелей.
 */
public class CableDAO extends BaseDaoImpl<Cable, Integer> {
    public CableDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Cable.class);
    }
}
