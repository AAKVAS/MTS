package com.example.mts.connectedEquipment.domain.repository;

import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Реализация репозитория работы с подключённым оборудованием.
 */
public class ConnectedEquipmentRepositoryImpl implements ConnectedEquipmentRepository {
    /**
     * Источник данных репозитория.
     */
    private ConnectedEquipmentDataSource connectedEquipmentDataSource;

    /**
     * Конструктор класса ConnectedEquipmentRepositoryImpl по умолчанию.
     */
    public ConnectedEquipmentRepositoryImpl() {
    }

    /**
     * Конструктор класса ConnectedEquipmentRepositoryImpl.
     *
     * @param connectedEquipmentDataSource Источник данных.
     */
    @Inject
    public ConnectedEquipmentRepositoryImpl(ConnectedEquipmentDataSource connectedEquipmentDataSource) {
        this.connectedEquipmentDataSource = connectedEquipmentDataSource;
    }


    @Override
    public Single<List<ConnectedEquipment>> getConnectedEquipment() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(connectedEquipmentDataSource.getConnectedEquipment());
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Completable deleteConnectedEquipment(int id) {
        return Completable.create(emitter -> {
            try {
                connectedEquipmentDataSource.deleteConnectedEquipment(id);
                emitter.onComplete();
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Completable createConnectedEquipment(ConnectedEquipment connectedEquipment) {
        return Completable.create(emitter -> {
            try {
                connectedEquipmentDataSource.createConnectedEquipment(connectedEquipment);
                emitter.onComplete();
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Completable updateConnectedEquipment(ConnectedEquipment connectedEquipment) {
        return Completable.create(emitter -> {
            try {
                connectedEquipmentDataSource.updateConnectedEquipment(connectedEquipment);
                emitter.onComplete();
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<List<Cable>> getCables() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(connectedEquipmentDataSource.getCables());
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<List<Building>> getBuildings() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(connectedEquipmentDataSource.getBuildings());
            } catch (SQLException e) {
                emitter.onError(e);
            }
        });
    }
}
