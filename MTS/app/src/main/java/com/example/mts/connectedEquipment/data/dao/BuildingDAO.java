package com.example.mts.connectedEquipment.data.dao;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * DAO-класс для доступа к зданиям
 */
public class BuildingDAO extends BaseDaoImpl<Building, Integer> {
    public BuildingDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Building.class);
    }
}
