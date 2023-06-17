package com.example.mts.connectedEquipment.data.dao;

import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO-класс для доступа к моделям электрических щитков.
 */
public class SwitchboardModelsDAO extends BaseDaoImpl<SwitchboardModel, Integer> {
    public SwitchboardModelsDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, SwitchboardModel.class);
    }
}
