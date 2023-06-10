package com.example.mts.connectedEquipment.presentation.presenter;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.interactor.DeleteConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
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
     * UseCase удаления подключённого оборудования.
     */
    DeleteConnectedEquipmentUseCase deleteConnectedEquipmentUseCase;

    /**
     * Список модулей приложения.
     */
    private List<ConnectedEquipment> connectedEquipments = new ArrayList<>();

    /**
     * Текущая запись.
     */
    private ConnectedEquipment currentConnectedEquipment;

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
     * Конструктор по умолчанию.
     */
    public ConnectedEquipmentListPresenter() {}

    /**
     * Конструктор класса ConnectedEquipmentListPresenter.
     * @param view представление модуля.
     * @param getConnectedEquipmentUseCase UseCase загрузки списка подключённого оборудования.
     */
    @Inject
    public ConnectedEquipmentListPresenter(ConnectedEquipmentListView view,
                                           GetConnectedEquipmentUseCase getConnectedEquipmentUseCase,
                                           DeleteConnectedEquipmentUseCase deleteConnectedEquipmentUseCase) {
        this.view = view;
        this.getConnectedEquipmentUseCase = getConnectedEquipmentUseCase;
        this.deleteConnectedEquipmentUseCase = deleteConnectedEquipmentUseCase;
    }

    public void onDestroy() {
        getConnectedEquipmentUseCase = null;
        deleteConnectedEquipmentUseCase = null;
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
     * Класс-наблюдатель, обновляющий список оборудования в представлении после успешной выгрузки.
     */
    private class LoadConnectedEquipmentObserver implements MaybeObserver<List<ConnectedEquipment>> {

        @Override
        public void onSubscribe(Disposable d) {}

        @Override
        public void onSuccess(List<ConnectedEquipment> connectedEquipments) {
            ConnectedEquipmentListPresenter.this.connectedEquipments.clear();
            ConnectedEquipmentListPresenter.this.connectedEquipments.addAll(connectedEquipments);
            updateView();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при получении списка модулей.");
        }

        @Override
        public void onComplete() {}
    }

    /**
     * Обновляет список подключённого оборудования в представлении.
     */
    private void updateView() {
        view.fillConnectedEquipmentList();
    }

    /**
     * Возникает при нажатии на элемент списка подключённого оборудования.
     * @param connectedEquipment нажатый элемент.
     */
    public void onEquipmentClick(ConnectedEquipment connectedEquipment) {
        view.openConnectedEquipmentItem(connectedEquipment);
    }

    /**
     * Возникает при долгом нажатии на элемент списка подключённого оборудования.
     * @param connectedEquipment нажатый элемент.
     */
    public void onEquipmentLongClick(ConnectedEquipment connectedEquipment) {
        currentConnectedEquipment = connectedEquipment;
        view.openDeleteConnectedEquipmentDialog();
    }

    /**
     * Удаляет текущую запись о подключённом оборудовании.
     */
    public void deleteConnectedEquipment() {
        deleteConnectedEquipmentUseCase.execute(currentConnectedEquipment.getId()).subscribe(new DeleteConnectedEquipmentObserver());
    }

    /**
     * Класс-наблюдатель, обновляющий список оборудования в представлении после удаления записи.
     */
    private class DeleteConnectedEquipmentObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {}

        @Override
        public void onComplete() {
            loadConnectedEquipment();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при удалении записи.");
        }
    }
}
