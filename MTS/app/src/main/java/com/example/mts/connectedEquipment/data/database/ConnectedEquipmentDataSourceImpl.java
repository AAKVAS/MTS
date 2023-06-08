package com.example.mts.connectedEquipment.data.database;

import com.example.mts.connectedEquipment.data.dao.DAOConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Реализация источника данных подключённого оборудования.
 */
public class ConnectedEquipmentDataSourceImpl implements ConnectedEquipmentDataSource {

    /**
     * DAO для работы с подключённым оборудованиемю
     */
    private DAOConnectedEquipment daoConnectedEquipment;

    /**
     * Конструктор класса ConnectedEquipmentDataSourceImpl.
     * @param daoConnectedEquipment DAO для доступа к подключённому оборудованию.
     */
    @Inject
    public ConnectedEquipmentDataSourceImpl(DAOConnectedEquipment daoConnectedEquipment) {
        this.daoConnectedEquipment = daoConnectedEquipment;
    }

    @Override
    public List<ConnectedEquipment> getConnectedEquipment() throws SQLException {
        return daoConnectedEquipment.queryForAll();
    }
}
