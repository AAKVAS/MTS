package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс создания записи подключённого оборудования.
 */
public class CreateConnectedEquipmentUseCase {
    /**
     * Репозиторий работы с оборудованием.
     */
    ConnectedEquipmentRepository connectedEquipmentRepository;

    /**
     * Планировщик для извлечения записей.
     */
    Scheduler executorScheduler;

    /**
     * Конструктор класса CreateConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public CreateConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        this.connectedEquipmentRepository = connectedEquipmentRepository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение добавления записи подключённого оборудования.
     * @return результат добавления.
     */
    public Completable execute(ConnectedEquipment connectedEquipment) {
        return connectedEquipmentRepository.createConnectedEquipment(connectedEquipment)
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
