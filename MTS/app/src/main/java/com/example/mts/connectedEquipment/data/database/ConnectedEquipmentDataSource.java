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

    /**
     * Удаляет указанное подключённое оборудование.
     * @param id идентификатор записи подключённого оборудования.
     * @throws SQLException
     */
    void deleteConnectedEquipment(int id) throws SQLException;

    /**
     * Добавляет запись о подключённом оборудовании в БД.
     * @param connectedEquipment запись подключённого оборудования, которую нужно добавить.
     * @throws SQLException
     */
    void createConnectedEquipment(ConnectedEquipment connectedEquipment) throws SQLException;

    /**
     * Обновляет запись о подключённом оборудовании в БД.
     * @param connectedEquipment запись подключённого оборудования, которую нужно обновить.
     * @throws SQLException
     */
    void updateConnectedEquipment(ConnectedEquipment connectedEquipment) throws SQLException;
}
