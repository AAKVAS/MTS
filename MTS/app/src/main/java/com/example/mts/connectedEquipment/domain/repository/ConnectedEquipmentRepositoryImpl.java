package com.example.mts.connectedEquipment.domain.repository;

import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;

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
    public ConnectedEquipmentRepositoryImpl() {}

    /**
     * Конструктор класса ConnectedEquipmentRepositoryImpl.
     * @param connectedEquipmentDataSource Источник данных.
     */
    @Inject
    public ConnectedEquipmentRepositoryImpl(ConnectedEquipmentDataSource connectedEquipmentDataSource) {
        this.connectedEquipmentDataSource = connectedEquipmentDataSource;
    }


    @Override
    public Maybe<List<ConnectedEquipment>> getConnectedEquipment() {
        return Maybe.create(emitter -> {
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
}
