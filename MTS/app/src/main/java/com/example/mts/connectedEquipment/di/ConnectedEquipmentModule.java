package com.example.mts.connectedEquipment.di;

import com.example.mts.connectedEquipment.data.dao.BuildingDAO;
import com.example.mts.connectedEquipment.data.dao.CableDAO;
import com.example.mts.connectedEquipment.data.dao.ConnectedEquipmentDAO;
import com.example.mts.connectedEquipment.data.dao.SwitchboardDAO;
import com.example.mts.connectedEquipment.data.dao.SwitchboardModelsDAO;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSource;
import com.example.mts.connectedEquipment.data.database.ConnectedEquipmentDataSourceImpl;
import com.example.mts.connectedEquipment.domain.interactor.CreateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.DeleteConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetBuildingsUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetCablesUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.UpdateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepositoryImpl;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentItemPresenter;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentListPresenter;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentItemActivity;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentItemView;
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
     * Экземпляр модуля ConnectedEquipmentModule.
     */
    private static ConnectedEquipmentModule instance;

    /**
     * Создаёт и возвращает экземпляр модуля ConnectedEquipmentModule.
     * @param connectedEquipmentListActivity ведущая активность модуля.
     * @return экземпляр модуля ConnectedEquipmentModule.
     */
    public static ConnectedEquipmentModule getInstance(ConnectedEquipmentListActivity connectedEquipmentListActivity) {
        instance = new ConnectedEquipmentModule(connectedEquipmentListActivity);
        return instance;
    }

    /**
     * Возвращает экземпляр модуля ConnectedEquipmentModule, если он есть.
     * @return экземпляр модуля ConnectedEquipmentModule.
     */
    public static ConnectedEquipmentModule getInstance() {
        if (instance == null) {
            throw new RuntimeException("Не удалось создать модуль ConnectedEquipmentModule");
        }
        return instance;
    }

    /**
     * Активность списка подключённого оборудования.
     */
    private ConnectedEquipmentListActivity connectedEquipmentListActivity;

    /**
     * Активность работы с записью о подключённым оборудованием.
     */
    private ConnectedEquipmentItemActivity connectedEquipmentItemActivity;

    /**
     * Устанавливает активность работы с записью ConnectedEquipmentItemActivity.
     * @param connectedEquipmentItemActivity активность работы с записью ConnectedEquipmentItemActivity.
     */
    public static void setConnectedEquipmentItemActivity(ConnectedEquipmentItemActivity connectedEquipmentItemActivity) {
        getInstance().connectedEquipmentItemActivity = connectedEquipmentItemActivity;
    }

    /**
     * Конструктор класса ConnectedEquipmentModule.
     * @param connectedEquipmentListActivity списка подключённого оборудования.
     */
    private ConnectedEquipmentModule(ConnectedEquipmentListActivity connectedEquipmentListActivity) {
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
     * Возвращает представителя для работы с записью о подключённом оборудовании.
     * @param createConnectedEquipmentUseCase UseСase создания записи подключённого оборудования.
     * @param updateConnectedEquipmentUseCase UseСase обновления записи подключённого оборудования.
     * @param getCablesUseCase UseСase получения моделей кабелей.
     * @param getBuildingsUseCase UseСase получения зданий.
     * @return представитель для работы с записью о подключённом оборудовании.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentItemPresenter provideConnectedEquipmentItemPresenter(CreateConnectedEquipmentUseCase createConnectedEquipmentUseCase,
                                                                                  UpdateConnectedEquipmentUseCase updateConnectedEquipmentUseCase,
                                                                                  GetCablesUseCase getCablesUseCase,
                                                                                  GetBuildingsUseCase getBuildingsUseCase) {
        return new ConnectedEquipmentItemPresenter(connectedEquipmentItemActivity,
                createConnectedEquipmentUseCase,
                updateConnectedEquipmentUseCase,
                getCablesUseCase,
                getBuildingsUseCase);
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
     * Возвращает реализацию представления списка подключённого оборудования.
     * @return представление списка подключённого оборудования.
     */
    @Provides
    public ConnectedEquipmentItemView provideConnectedEquipmentItemView() {
        if (connectedEquipmentItemActivity == null) {
            throw new RuntimeException("Активность ConnectedEquipmentItemView не должна быть null.");
        }
        return connectedEquipmentItemActivity;
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
     * Возвращает UseCase получения списка зданий.
     * @param repository репозиторий, возвращающий список зданий.
     * @return UseCase получения списка зданий.
     */
    @Provides
    @ConnectedEquipmentScope
    public GetBuildingsUseCase provideGetBuildingsUseCase(ConnectedEquipmentRepository repository) {
        return new GetBuildingsUseCase(repository, Schedulers.io());
    }

    /**
     * Возвращает UseCase получения моделей кабелей.
     * @param repository репозиторий, возвращающий список моделей кабелей.
     * @return UseCase получения списка моделей кабелей.
     */
    @Provides
    @ConnectedEquipmentScope
    public GetCablesUseCase provideGetCablesUseCase(ConnectedEquipmentRepository repository) {
        return new GetCablesUseCase(repository, Schedulers.io());
    }

    /**
     * Возвращает UseCase создания записи о подключённом оборудовании.
     * @param repository репозиторий для работы с подключённым оборудованием.
     * @return UseCase создания записи о подключённом оборудовании.
     */
    @Provides
    @ConnectedEquipmentScope
    public CreateConnectedEquipmentUseCase provideCreateConnectedEquipmentUseCase(ConnectedEquipmentRepository repository) {
        return new CreateConnectedEquipmentUseCase(repository, Schedulers.io());
    }

    /**
     * Возвращает UseCase обновления записи о подключённом оборудовании.
     * @param repository репозиторий для работы с подключённым оборудованием.
     * @return UseCase обновления записи о подключённом оборудовании.
     */
    @Provides
    @ConnectedEquipmentScope
    public UpdateConnectedEquipmentUseCase provideUpdateConnectedEquipmentUseCase(ConnectedEquipmentRepository repository) {
        return new UpdateConnectedEquipmentUseCase(repository, Schedulers.io());
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
     * @param cableDAO DAO моделей кабелей.
     * @param buildingDAO DAO зданий.
     * @param switchboardModelsDAO DAO моделей электрических щитков.
     * @param switchboardDAO DAO электрических щитков.
     * @return источник данных подключённого оборудования.
     */
    @Provides
    @ConnectedEquipmentScope
    public ConnectedEquipmentDataSource provideConnectedEquipmentDataSource(ConnectedEquipmentDAO connectedEquipmentDAO,
                                                                            CableDAO cableDAO,
                                                                            BuildingDAO buildingDAO,
                                                                            SwitchboardModelsDAO switchboardModelsDAO,
                                                                            SwitchboardDAO switchboardDAO) {
        return new ConnectedEquipmentDataSourceImpl(connectedEquipmentDAO, cableDAO, buildingDAO, switchboardModelsDAO, switchboardDAO);
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
            throw new RuntimeException("Произошла ошибка при создании ConnectedEquipmentDAO");
        }
    }

    /**
     * Возвращает DAO моделей кабелей.
     * @param connectionSource источник соединения.
     * @return DAO моделей кабелей.
     */
    @Provides
    @ConnectedEquipmentScope
    public CableDAO provideCableDAO(ConnectionSource connectionSource) {
        try {
            return new CableDAO(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при создании CableDAO");
        }
    }

    /**
     * Возвращает DAO зданий.
     * @param connectionSource источник соединения.
     * @return DAO зданий.
     */
    @Provides
    @ConnectedEquipmentScope
    public BuildingDAO provideBuildingDAO(ConnectionSource connectionSource) {
        try {
            return new BuildingDAO(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при создании BuildingDAO");
        }
    }

    /**
     * Возвращает DAO моделей электрических щитков.
     * @param connectionSource источник соединения.
     * @return DAO моделей электрических щитков
     */
    @Provides
    @ConnectedEquipmentScope
    public SwitchboardModelsDAO provideSwitchboardModelsDAO(ConnectionSource connectionSource) {
        try {
            return new SwitchboardModelsDAO(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при создании SwitchboardModelsDAO");
        }
    }
    
    /**
     * Возвращает DAO  электрических щитков.
     * @param connectionSource источник соединения.
     * @return DAO электрических щитков
     */
    @Provides
    @ConnectedEquipmentScope
    public SwitchboardDAO provideSwitchboardDAO(ConnectionSource connectionSource) {
        try {
            return new SwitchboardDAO(connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException("Произошла ошибка при создании SwitchboardDAO");
        }
    }
}
