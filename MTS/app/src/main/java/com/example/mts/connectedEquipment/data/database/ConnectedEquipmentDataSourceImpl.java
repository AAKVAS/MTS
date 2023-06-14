package com.example.mts.connectedEquipment.data.database;

import com.example.mts.connectedEquipment.data.dao.BuildingDAO;
import com.example.mts.connectedEquipment.data.dao.CableDAO;
import com.example.mts.connectedEquipment.data.dao.ConnectedEquipmentDAO;
import com.example.mts.connectedEquipment.data.dao.SwitchboardDAO;
import com.example.mts.connectedEquipment.data.dao.SwitchboardModelsDAO;
import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;

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

    private CableDAO cableDAO;

    private BuildingDAO buildingDAO;

    private SwitchboardModelsDAO switchboardModelsDAO;

    private SwitchboardDAO switchboardDAO;

    /**
     * Конструктор класса ConnectedEquipmentDataSourceImpl.
     * @param connectedEquipmentDAO DAO для доступа к подключённому оборудованию.
     * @param cableDAO DAO для доступа к моделям кабелей.
     * @param buildingDAO DAO для доступа к зданиям.
     * @param switchboardModelsDAO DAO для доступа к моделям электрических щитков.
     * @param switchboardDAO DAO для доступа к электрическим щиткам.
     */
    @Inject
    public ConnectedEquipmentDataSourceImpl(ConnectedEquipmentDAO connectedEquipmentDAO,
                                            CableDAO cableDAO,
                                            BuildingDAO buildingDAO,
                                            SwitchboardModelsDAO switchboardModelsDAO,
                                            SwitchboardDAO switchboardDAO) {
        this.connectedEquipmentDAO = connectedEquipmentDAO;
        this.cableDAO = cableDAO;
        this.buildingDAO = buildingDAO;
        this.switchboardModelsDAO = switchboardModelsDAO;
        this.switchboardDAO = switchboardDAO;
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
        switchboardDAO.update(connectedEquipment.getSwitchboard());
    }

    @Override
    public List<Cable> getCables() throws SQLException {
        return cableDAO.queryForAll();
    }

    @Override
    public List<Building> getBuildings() throws SQLException {
        List<Building> buildings = buildingDAO.queryForAll();
        for (Building building: buildings) {
            for (Switchboard switchboard: building.getSwitchboardList()) {
                SwitchboardModel model = switchboardModelsDAO.queryForId(switchboard.getModel().getId());
                switchboard.setModel(model);
            }
        }
        return buildings;
    }
}
