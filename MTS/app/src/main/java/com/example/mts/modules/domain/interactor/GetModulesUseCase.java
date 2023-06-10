package com.example.mts.modules.domain.interactor;

import com.example.mts.modules.domain.repository.ModulesRepository;
import com.example.mts.modules.domain.entity.Module;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Класс-UseСase загрузки списка модулей.
 */
public class GetModulesUseCase {
    /**
     * Репозиторий работы с модулями.
     */
    ModulesRepository modulesRepository;

    /**
     * Планировщик для извлечения записей.
     */
    Scheduler executorScheduler;

    /**
     * Конструктор класса GetModulesUseCase.
     * @param repository репозиторий для работы с модулями.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetModulesUseCase(ModulesRepository repository, Scheduler executorScheduler) {
        this.modulesRepository = repository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение извлечения модулей.
     * @return
     */
    public Maybe<List<Module>> execute() {
        return modulesRepository.getModules().subscribeOn(executorScheduler).observeOn(AndroidSchedulers.mainThread());
    }
}
