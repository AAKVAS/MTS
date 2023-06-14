package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseCompletableUseCase;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс обновления записи подключённого оборудования.
 */
public class UpdateConnectedEquipmentUseCase extends BaseCompletableUseCase<ConnectedEquipmentRepository, UpdateConnectedEquipmentUseCase.Param> {

    /**
     * Конструктор класса UpdateConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public UpdateConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение обновления записи подключённого оборудования.
     * @return результат обновления.
     */
    public Completable execute(Param param) {
        return repository.updateConnectedEquipment(param.connectedEquipment)
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Обёртка параметров для выполнения действия.
     */
    public static final class Param {
        private ConnectedEquipment connectedEquipment;

        public Param(ConnectedEquipment connectedEquipment) {
            this.connectedEquipment = connectedEquipment;
        }
    }
}
