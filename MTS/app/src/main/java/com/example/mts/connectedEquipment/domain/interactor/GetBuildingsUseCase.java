package com.example.mts.connectedEquipment.domain.interactor;

import com.example.mts.base.BaseSingleUseCase;
import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.repository.ConnectedEquipmentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * UseСase-класс получения зданий.
 */
public class GetBuildingsUseCase extends BaseSingleUseCase<ConnectedEquipmentRepository, List<Building>> {
    /**
     * Конструктор класса GetBuildingsUseCase.
     * @param connectedEquipmentRepository репозиторий работы с оборудованием.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetBuildingsUseCase(ConnectedEquipmentRepository connectedEquipmentRepository, Scheduler executorScheduler) {
        super(connectedEquipmentRepository, executorScheduler);
    }

    /**
     * Выполнение извлечения зданий.
     * @return список зданий.
     */
    public Single<List<Building>> execute() {
        return repository
                .getBuildings()
                .subscribeOn(executorScheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
