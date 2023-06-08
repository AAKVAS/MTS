package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.connectedEquipment.data.repository.ConnectedEquipmentRepository;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Класс-UseСase загрузки списка подключённого оборудования.
 */
public class GetConnectedEquipmentUseCase {
    /**
     * Репозиторий работы с оборудованием.
     */
    ConnectedEquipmentRepository connectedEquipmentRepository;

    /**
     * Планировщик для извлечения записей.
     */
    Scheduler executorScheduler;

    /**
     * Конструктор класса GetConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        this.connectedEquipmentRepository = connectedEquipmentRepository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение извлечения оборудования.
     * @return подключённое оборудование.
     */
    public Maybe<List<ConnectedEquipment>> execute() {
        return connectedEquipmentRepository.getConnectedEquipment().subscribeOn(executorScheduler).observeOn(AndroidSchedulers.mainThread());
    }
}
