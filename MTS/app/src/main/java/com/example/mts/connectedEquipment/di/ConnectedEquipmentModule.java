package com.example.mts.connectedEquipment.di;

import com.example.mts.connectedEquipment.data.dao.DAOConnectedEquipment;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSourceImpl;
import com.example.mts.connectedEquipment.data.repository.ConnectedEquipmentRepository;
import com.example.mts.connectedEquipment.data.repository.ConnectedEquipmentRepositoryImpl;
import com.example.mts.connectedEquipment.domain.interactor.GetConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentListPresenter;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListActivity;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListView;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

/**
 * Модуль подключённого оборудования. Предоставляет реализации для внедрения.
 */
@Module
public class ConnectedEquipmentModule {
    /**
     * Активность списка подключённого оборудования.
     */
    private ConnectedEquipmentListActivity connectedEquipmentListActivity;

    /**
     * Конструктор класса ConnectedEquipmentModule.
     * @param connectedEquipmentListActivity списка подключённого оборудования.
     */
    public ConnectedEquipmentModule(ConnectedEquipmentListActivity connectedEquipmentListActivity) {
        this.connectedEquipmentListActivity = connectedEquipmentListActivity;
    }

    /**
     * Возвращает представителя списка подключённого оборудования.
     * @param useCase UseCase получения списка подключённого оборудования.
     * @return представитель списка подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentListPresenter provideConnectedEquipmentListPresenter(GetConnectedEquipmentUseCase useCase) {
        return new ConnectedEquipmentListPresenter(connectedEquipmentListActivity, useCase);
    }

    /**
     * Возвращает реализацию представления списка подключённого оборудования.
     * @return представление списка подключённого оборудования.
     */
    @Provides
    public ConnectedEquipmentListView provideConnectedEquipmentListView() {
        return connectedEquipmentListActivity;
    }

    /**
     * Возвращает UseCase получения списка подключённого оборудования.
     * @param repository репозиторий, возвращающий список подключённого оборудования.
     * @return UseCase получения списка подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public GetConnectedEquipmentUseCase provideGetConnectedEquipmentUseCase(ConnectedEquipmentRepository repository) {
        return new GetConnectedEquipmentUseCase(repository, Schedulers.io());
    }

    /**
     * Возвращает репозиторий подключённого оборудования.
     * @return репозиторий подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentRepository provideConnectedEquipmentRepository(ConnectedEquipmentDataSource connectedEquipmentDataSource) {
        return new ConnectedEquipmentRepositoryImpl(connectedEquipmentDataSource);
    }

    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentDataSource provideConnectedEquipmentDataSource(DAOConnectedEquipment daoConnectedEquipment) {
        return new ConnectedEquipmentDataSourceImpl(daoConnectedEquipment);
    }

    @Provides
    @ConnectedEquipmentScope
    public DAOConnectedEquipment provideDAOConnectedEquipment(ConnectionSource connectionSource) {
        try {
            return new DAOConnectedEquipment(connectionSource);
        } catch (SQLException e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

}
