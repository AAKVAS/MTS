package com.example.mts.modules.di;

import com.example.mts.modules.domain.interactor.GetModulesUseCase;
import com.example.mts.modules.domain.repository.ModulesRepository;
import com.example.mts.modules.domain.repository.ModulesRepositoryImpl;
import com.example.mts.modules.presentation.presenter.ModulesActivityPresenter;
import com.example.mts.modules.presentation.view.ModulesActivity;
import com.example.mts.modules.presentation.view.ModulesView;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

/**
 * Модуль активности "Модули". Предоставляет реализации для внедрения.
 */
@Module
public class ModulesActivityModule {

    /**
     * Активность "Модули".
     */
    private ModulesActivity modulesActivity;

    /**
     * Конструктор класса ModulesActivityModule.
     * @param modulesActivity активность "Модули".
     */
    public ModulesActivityModule(ModulesActivity modulesActivity) {
        this.modulesActivity = modulesActivity;
    }

    /**
     * Возвращает представителя активности "Модули".
     * @param useCase UseCase получения списка модулей.
     * @return представитель активности "Модули".
     */
    @Provides
    @ModulesScope
    public ModulesActivityPresenter provideModuleActivityPresenter(GetModulesUseCase useCase) {
        return new ModulesActivityPresenter(modulesActivity, useCase);
    }

    /**
     * Возвращает реализацию представления "Модули".
     * @return представление "Модули".
     */
    @Provides
    public ModulesView provideModuleView() {
        return modulesActivity;
    }

    /**
     * Возвращает UseCase получения списка модулей.
     * @param repository репозиторий, возвращающий список модулей.
     * @return UseCase получения списка модулей.
     */
    @Provides
    @ModulesScope
    public GetModulesUseCase provideGetModulesUseCase(ModulesRepository repository) {
        return new GetModulesUseCase(repository, Schedulers.io());
    }

    /**
     * Возвращает репозиторий списка модулей.
     * @return репозиторий списка модулей.
     */
    @Provides
    @ModulesScope
    public ModulesRepository provideModulesRepository() {
        return new ModulesRepositoryImpl();
    }
}
