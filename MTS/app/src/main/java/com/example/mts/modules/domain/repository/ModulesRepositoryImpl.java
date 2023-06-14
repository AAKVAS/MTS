package com.example.mts.modules.domain.repository;

import com.example.mts.MTSApplication;
import com.example.mts.modules.domain.entity.Module;

import java.util.List;

import io.reactivex.Single;

/**
 * Реализация репозитория работы с модулями.
 */
public class ModulesRepositoryImpl implements ModulesRepository {

    /**
     * Конструктор класса ModulesRepositoryImpl.
     */
    public ModulesRepositoryImpl() {
    }

    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    public Single<List<Module>> getModules() {
        return Single.create(emitter -> {
            emitter.onSuccess(MTSApplication.getInstance().getAppModules());
        });
    }
}
