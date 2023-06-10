package com.example.mts.connectedEquipment.domain.repository;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Интерфейс-репозиторий работы с подключённым оборудованием.
 */
public interface ConnectedEquipmentRepository {
    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    Maybe<List<ConnectedEquipment>> getConnectedEquipment();

    /**
     * Удаляет указанное подключённое оборудование.
     * @param id идентификатор записи подключённого оборудования.
     * @return результат удаления записи.
     */
     Completable deleteConnectedEquipment(int id);

    /**
     * Добавляет запись о подключённом оборудовании в БД.
     * @param connectedEquipment запись подключённого оборудования, которую нужно добавить.
     * @return результат добавления записи.
     */
     Completable createConnectedEquipment(ConnectedEquipment connectedEquipment);

    /**
     * Обновляет запись о подключённом оборудовании в БД.
     * @param connectedEquipment запись подключённого оборудования, которую нужно обновить.
     * @return результат обновления записи.
     */
     Completable updateConnectedEquipment(ConnectedEquipment connectedEquipment);
}