package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseSingleUseCase;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс получения моделей кабелей.
 */
public class GetCablesUseCase extends BaseSingleUseCase<ConnectedEquipmentRepository, List<Cable>> {
    /**
     * Конструктор класса GetCablesUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetCablesUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение извлечения моделей кабелей.
     * @return модели кабелей.
     */
    public Single<List<Cable>> execute() {
        return repository
                .getCables()
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
