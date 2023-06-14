package com.example.mts.connectedEquipment.presentation.presenter;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.interactor.CreateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetBuildingsUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetCablesUseCase;
import com.example.mts.connectedEquipment.domain.interactor.UpdateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentItemView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Представитель для работы с записью о подключённом оборудовании.
 */
public class ConnectedEquipmentItemPresenter {

    /**
     * Представление активности.
     */
    ConnectedEquipmentItemView view;

    /**
     * UseСase создания записи подключённого оборудования.
     */
    CreateConnectedEquipmentUseCase createConnectedEquipmentUseCase;

    /**
     * UseСase обновления записи подключённого оборудования.
     */
    UpdateConnectedEquipmentUseCase updateConnectedEquipmentUseCase;

    /**
     * UseСase получения моделей кабелей.
     */
    GetCablesUseCase getCablesUseCase;

    /**
     * UseСase получения зданий.
     */
    GetBuildingsUseCase getBuildingsUseCase;

    /**
     * Текущая запись.
     */
    private ConnectedEquipment currentConnectedEquipment;

    /**
     * Список моделей кабелей.
     */
    private List<Cable> cables;

    /**
     * Список зданий.
     */
    private List<Building> buildings;

    /**
     * Определяет, открыта запись на создание или обновление.
     */
    private boolean isConnectedEquipmentCreated = false;

    /**
     * Возвращает текущую запись.
     * @return текущая запись.
     */
    public ConnectedEquipment getCurrentConnectedEquipment() {
        return currentConnectedEquipment;
    }

    /**
     * Устанавливает текущую запись.
     * @param currentConnectedEquipment текущая запись.
     */
    public void setCurrentConnectedEquipment(ConnectedEquipment currentConnectedEquipment) {
        this.currentConnectedEquipment = currentConnectedEquipment;
    }

    public List<Cable> getCables() {
        return cables;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public boolean isConnectedEquipmentCreated() {
        return isConnectedEquipmentCreated;
    }

    public void setConnectedEquipmentCreated(boolean connectedEquipmentCreated) {
        isConnectedEquipmentCreated = connectedEquipmentCreated;
    }

    /**
     * Конструктор по умолчанию.
     */
    public ConnectedEquipmentItemPresenter() {}

    /**
     * Конструктор класса ConnectedEquipmentItemPresenter.
     * @param view представление модуля.
     * @param createConnectedEquipmentUseCase UseCase создания подключённого оборудования.
     * @param updateConnectedEquipmentUseCase UseCase обновления подключённого оборудования.
     * @param getCablesUseCase UseCase загрузки списка моделей кабелей.
     * @param getBuildingsUseCase UseCase загрузки списка зданий.
     */
    @Inject
    public ConnectedEquipmentItemPresenter(ConnectedEquipmentItemView view,
                                           CreateConnectedEquipmentUseCase createConnectedEquipmentUseCase,
                                           UpdateConnectedEquipmentUseCase updateConnectedEquipmentUseCase,
                                           GetCablesUseCase getCablesUseCase,
                                           GetBuildingsUseCase getBuildingsUseCase
                                           ) {
        this.view = view;
        this.createConnectedEquipmentUseCase = createConnectedEquipmentUseCase;
        this.updateConnectedEquipmentUseCase = updateConnectedEquipmentUseCase;
        this.getCablesUseCase = getCablesUseCase;
        this.getBuildingsUseCase = getBuildingsUseCase;
    }

    public void onResume() {
        getCablesUseCase.execute().subscribe(new LoadCablesObserver());
    }

    public void onDestroy() {
        createConnectedEquipmentUseCase = null;
        updateConnectedEquipmentUseCase = null;
        getCablesUseCase = null;
        getBuildingsUseCase = null;
    }

    /**
     * Класс-наблюдатель, загружающий список моделей кабелей.
     */
    private class LoadCablesObserver implements SingleObserver<List<Cable>> {

        @Override
        public void onSubscribe(Disposable d) {}

        @Override
        public void onSuccess(List<Cable> cables) {
            ConnectedEquipmentItemPresenter.this.cables = cables;
            getBuildingsUseCase.execute().subscribe(new LoadBuildingsObserver());
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при получении списка кабелей.");
        }
    }

    /**
     * Класс-наблюдатель, загружающий список зданий.
     */
    private class LoadBuildingsObserver implements SingleObserver<List<Building>> {

        @Override
        public void onSubscribe(Disposable d) {}

        @Override
        public void onSuccess(List<Building> buildings) {
            ConnectedEquipmentItemPresenter.this.buildings = buildings;
            view.fillConnectedEquipmentItemView();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при получении списка зданий.");
        }
    }

    public void saveConnectedEquipment() {
        if (isConnectedEquipmentCreated) {
            createConnectedEquipmentUseCase.execute(new CreateConnectedEquipmentUseCase.Param(currentConnectedEquipment)).subscribe(new CreateObserver());
        }
        else {
            updateConnectedEquipmentUseCase.execute(new UpdateConnectedEquipmentUseCase.Param(currentConnectedEquipment)).subscribe(new UpdateObserver());
        }
    }

    private class CreateObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {
            view.close();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Произошла ошибка при добавлении записи.");
        }
    }

    private class UpdateObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {
            view.close();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Произошла ошибка при изменении записи.");
        }
    }
}
