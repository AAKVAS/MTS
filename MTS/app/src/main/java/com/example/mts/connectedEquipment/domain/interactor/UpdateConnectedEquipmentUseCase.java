package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс обновления записи подключённого оборудования.
 */
public class UpdateConnectedEquipmentUseCase {
    /**
     * Репозиторий работы с оборудованием.
     */
    ConnectedEquipmentRepository connectedEquipmentRepository;

    /**
     * Планировщик для извлечения записей.
     */
    Scheduler executorScheduler;

    /**
     * Конструктор класса UpdateConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public UpdateConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        this.connectedEquipmentRepository = connectedEquipmentRepository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение обновления записи подключённого оборудования.
     * @return результат обновления.
     */
    public Completable execute(ConnectedEquipment connectedEquipment) {
        return connectedEquipmentRepository.updateConnectedEquipment(connectedEquipment)
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
