package com.example.mts.connectedEquipment.presentation.presenter;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.interactor.GetConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

/**
 * Класс-представитель списка модуля подключённого оборудования.
 */
public class ConnectedEquipmentListPresenter {
    /**
     * Представление активности.
     */
    ConnectedEquipmentListView view;

    /**
     * UseСase загрузки списка оборудования из БД.
     */
    GetConnectedEquipmentUseCase getConnectedEquipmentUseCase;

    /**
     * Список модулей приложения.
     */
    private List<ConnectedEquipment> connectedEquipments = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     */
    public ConnectedEquipmentListPresenter() {}

    /**
     * Конструктор класса ConnectedEquipmentListPresenter.
     * @param view представление модуля.
     * @param getConnectedEquipmentUseCase UseCase загрузки списка подключённого оборудования.
     */
    @Inject
    public ConnectedEquipmentListPresenter(ConnectedEquipmentListView view, GetConnectedEquipmentUseCase getConnectedEquipmentUseCase) {
        this.view = view;
        this.getConnectedEquipmentUseCase = getConnectedEquipmentUseCase;
    }

    /**
     * Заполняет список подключённого оборудования, если он пуст.
     */
    public void fillConnectedEquipment() {
        if (connectedEquipments.isEmpty()) {
            loadConnectedEquipment();
        }
    }

    /**
     * Загружает список подключённого оборудования.
     */
    private void loadConnectedEquipment() {
        getConnectedEquipmentUseCase.execute().subscribe(new ConnectedEquipmentListPresenter.LoadConnectedEquipmentObserver());
    }

    /**
     * Класс-наблюдатель, обновляющий список модулей в представлении после успешной выгрузки.
     */
    private class LoadConnectedEquipmentObserver implements MaybeObserver<List<ConnectedEquipment>> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(List<ConnectedEquipment> connectedEquipments) {
            ConnectedEquipmentListPresenter.this.connectedEquipments.clear();
            ConnectedEquipmentListPresenter.this.connectedEquipments.addAll(connectedEquipments);
            updateView();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    /**
     * Обновляет список подключённого оборудования в представлении.
     */
    private void updateView() {
        view.fillConnectedEquipmentList();
    }

    /**
     * Возвращает список подключённого оборудования.
     * @return список подключённого оборудования.
     */
    public List<ConnectedEquipment> getConnectedEquipment() {
        return connectedEquipments;
    }

    /**
     * Устанавливает список подключённого оборудования.
     * @param connectedEquipments новый список подключённого оборудования.
     */
    public void setConnectedEquipments(List<ConnectedEquipment> connectedEquipments) {
        this.connectedEquipments = connectedEquipments;
    }

    /**
     * Возникает при нажатии на элемент списка подключённого оборудования.
     * @param connectedEquipment нажатый модуль.
     */
    public void onModuleClick(ConnectedEquipment connectedEquipment) {
        view.openConnectedEqipmentItem(connectedEquipment);
    }
}
