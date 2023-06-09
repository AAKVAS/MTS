package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseSingleUseCase;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс загрузки списка подключённого оборудования.
 */
public class GetConnectedEquipmentUseCase extends BaseSingleUseCase<ConnectedEquipmentRepository, List<ConnectedEquipment>> {

    /**
     * Конструктор класса GetConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение извлечения оборудования.
     * @return подключённое оборудование.
     */
    public Single<List<ConnectedEquipment>> execute() {
        return repository
                .getConnectedEquipment()
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
