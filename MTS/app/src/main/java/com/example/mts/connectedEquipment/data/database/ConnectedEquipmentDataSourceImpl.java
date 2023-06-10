package com.example.mts.connectedEquipment.data.database;

import com.example.mts.connectedEquipment.data.dao.ConnectedEquipmentDAO;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Реализация источника данных подключённого оборудования.
 */
public class ConnectedEquipmentDataSourceImpl implements ConnectedEquipmentDataSource {

    /**
     * DAO для работы с подключённым оборудованием.
     */
    private ConnectedEquipmentDAO connectedEquipmentDAO;

    /**
     * Конструктор класса ConnectedEquipmentDataSourceImpl.
     * @param connectedEquipmentDAO DAO для доступа к подключённому оборудованию.
     */
    @Inject
    public ConnectedEquipmentDataSourceImpl(ConnectedEquipmentDAO connectedEquipmentDAO) {
        this.connectedEquipmentDAO = connectedEquipmentDAO;
    }

    @Override
    public List<ConnectedEquipment> getConnectedEquipment() throws SQLException {
        return connectedEquipmentDAO.queryForAll();
    }

    @Override
    public void deleteConnectedEquipment(int id) throws SQLException {
        connectedEquipmentDAO.deleteById(id);
    }

    @Override
    public void createConnectedEquipment(ConnectedEquipment connectedEquipment) throws SQLException {
        connectedEquipmentDAO.create(connectedEquipment);
    }

    @Override
    public void updateConnectedEquipment(ConnectedEquipment connectedEquipment) throws SQLException {
        connectedEquipmentDAO.update(connectedEquipment);
    }
}
