package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseCase-класс удаления записи о подключённом оборудовании.
 */
public class DeleteConnectedEquipmentUseCase {
    /**
     * Репозиторий работы с оборудованием.
     */
    ConnectedEquipmentRepository connectedEquipmentRepository;

    /**
     * Планировщик для извлечения записей.
     */
    Scheduler executorScheduler;

    /**
     * Конструктор класса DeleteConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public DeleteConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        this.connectedEquipmentRepository = connectedEquipmentRepository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение удаления оборудования.
     * @return результат удаления.
     */
    public Completable execute(int id) {
        return connectedEquipmentRepository.deleteConnectedEquipment(id)
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
