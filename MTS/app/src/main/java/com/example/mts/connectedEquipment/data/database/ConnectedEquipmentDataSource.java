package com.example.mts.connectedEquipment.data.database;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

/**
 * Источник данных подключённого оборудования.
 */
public interface ConnectedEquipmentDataSource {
    /**
     * Возвращает список подключённого оборудования.
     * @return список подключённого оборудования.
     * @throws SQLException
     */
    List<ConnectedEquipment> getConnectedEquipment() throws SQLException;
}
