package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseCompletableUseCase;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс создания записи подключённого оборудования.
 */
public class CreateConnectedEquipmentUseCase extends BaseCompletableUseCase<ConnectedEquipmentRepository, CreateConnectedEquipmentUseCase.Param> {


    /**
     * Конструктор класса CreateConnectedEquipmentUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public CreateConnectedEquipmentUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение добавления записи подключённого оборудования.
     * @return результат добавления.
     */
    public Completable execute(Param param) {
        return repository.createConnectedEquipment(param.connectedEquipment)
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
