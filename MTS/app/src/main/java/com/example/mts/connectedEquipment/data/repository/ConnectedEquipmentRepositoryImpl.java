package com.example.mts.connectedEquipment.data.repository;

import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSourceImpl;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

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
}
