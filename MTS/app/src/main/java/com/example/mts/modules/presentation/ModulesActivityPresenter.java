package com.example.mts.modules.presentation;

import com.example.mts.modules.domain.entity.Module;
import com.example.mts.modules.domain.interactor.GetModulesUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

/**
 * Класс-представитель модуля "Модули".
 */
public class ModulesActivityPresenter {
    /**
     * Представление модуля.
     */
    ModuleView moduleView;
    /**
     * UseСase загрузки списка модулей из БД.
     */
    GetModulesUseCase getModulesUseCase;

    /**
     * Список модулей приложения.
     */
    private List<Module> modules = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     */
    public ModulesActivityPresenter() {}

    /**
     * Конструктор класса ModulesActivityPresenter.
     * @param moduleView представление модуля.
     * @param getModulesUseCase UseCase загрузки списка модулей.
     */
    @Inject
    public ModulesActivityPresenter(ModuleView moduleView, GetModulesUseCase getModulesUseCase) {
        this.moduleView = moduleView;
        this.getModulesUseCase = getModulesUseCase;
    }

    /**
     * Заполняет список модулей, если он пуст.
     */
    public void fillModules() {
        if (modules.isEmpty()) {
            loadModules();
        }
    }

    /**
     * Загружает список модулей из базы данных.
     */
    private void loadModules() {
        getModulesUseCase.execute().subscribe(new LoadModulesObserver());
    }

    /**
     * Класс-наблюдатель, обновляющий список модулей в представлении после успешной выгрузки из БД.
     */
    private class LoadModulesObserver implements MaybeObserver<List<Module>> {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(List<Module> modules) {
            ModulesActivityPresenter.this.modules.clear();
            ModulesActivityPresenter.this.modules.addAll(modules);
            updateView();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    /**
     * Обновляет список модулей в представлении.
     */
    private void updateView() {
        moduleView.fillModuleList();
    }

    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Устанавливает список модулей.
     * @param modules новый список модулей.
     */
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
