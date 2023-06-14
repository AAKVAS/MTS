package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseCompletableUseCase;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseCase-класс удаления записи о подключённом оборудовании.
 */
public class DeleteConnectedEquipmentUseCase extends BaseCompletableUseCase<ConnectedEquipmentRepository, DeleteConnectedEquipmentUseCase.Param> {
    /**
     * Конструктор класса DeleteConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public DeleteConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение удаления оборудования.
     * @return результат удаления.
     */
    public Completable execute(Param param) {
        return repository.deleteConnectedEquipment(param.id)
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Обёртка параметров для выполнения действия.
     */
    public static final class Param {
        private int id;

        public Param(int id) {
            this.id = id;
        }
    }
}
