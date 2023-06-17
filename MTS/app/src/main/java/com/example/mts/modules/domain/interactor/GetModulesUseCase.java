package com.example.mts.modules.domain.interactor;

import com.example.mts.base.BaseSingleUseCase;
import com.example.mts.modules.domain.entity.Module;
import com.example.mts.modules.domain.repository.ModulesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Класс-UseСase загрузки списка модулей.
 */
public class GetModulesUseCase extends BaseSingleUseCase<ModulesRepository, List<Module>> {
    /**
     * Конструктор класса GetModulesUseCase.
     * @param repository репозиторий для работы с модулями.
     * @param executorScheduler планировщик для извлечения записей.
     */
    @Inject
    public GetModulesUseCase(ModulesRepository repository, Scheduler executorScheduler) {
        super(repository, executorScheduler);
    }

    /**
     * Выполнение извлечения модулей.
     * @return список модулей.
     */
    @Override
    public Single<List<Module>> execute() {
        return repository.getModules().subscribeOn(executorScheduler).observeOn(AndroidSchedulers.mainThread());
    }
}
