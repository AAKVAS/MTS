package com.example.mts.connectedEquipment.di;

import com.example.mts.connectedEquipment.data.dao.ConnectedEquipmentDAO;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSourceImpl;
import com.example.mts.connectedEquipment.domain.interactor.DeleteConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepositoryImpl;
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
     * @param getUseCase UseCase получения списка подключённого оборудования.
     * @param delUseCase UseCase удаления подключённого оборудования.
     * @return представитель списка подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentListPresenter provideConnectedEquipmentListPresenter(GetConnectedEquipmentUseCase getUseCase, DeleteConnectedEquipmentUseCase delUseCase) {
        return new ConnectedEquipmentListPresenter(connectedEquipmentListActivity, getUseCase, delUseCase);
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
     * Возвращает UseCase удаления записи о подключённом оборудовании.
     * @param repository репозиторий для работы с подключённым оборудованием.
     * @return UseCase удаления записи о подключённом оборудовании.
     */
    @Provides
    @ConnectedEquipmentScope
    public DeleteConnectedEquipmentUseCase provideDeleteConnectedEquipmentUseCase(ConnectedEquipmentRepository repository) {
        return new DeleteConnectedEquipmentUseCase(repository, Schedulers.io());
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

    /**
     * Возвращает источник данных подключённого оборудования.
     * @param connectedEquipmentDAO DAO подключённого оборудования.
     * @return источник данных подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentDataSource provideConnectedEquipmentDataSource(ConnectedEquipmentDAO connectedEquipmentDAO) {
        return new ConnectedEquipmentDataSourceImpl(connectedEquipmentDAO);
    }

    /**
     * Возвращает DAO подключённого оборудования.
     * @param connectionSource источник соединения.
     * @return DAO подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentDAO provideDAOConnectedEquipment(ConnectionSource connectionSource) {
        try {
            return new ConnectedEquipmentDAO(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
