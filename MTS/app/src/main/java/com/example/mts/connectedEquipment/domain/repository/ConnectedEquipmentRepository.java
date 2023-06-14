package com.example.mts.connectedEquipment.domain.repository;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерфейс-репозиторий работы с подключённым оборудованием.
 */
public interface ConnectedEquipmentRepository {
    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    Single<List<ConnectedEquipment>> getConnectedEquipment();

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

    /**
     * Возвращает список моделей кабелей.
     * @return список моделей кабелей.
     */
     Single<List<Cable>> getCables();

    /**
     * Возвращает список зданий.
     * @return список зданий.
     */
    Single<List<Building>> getBuildings();
}