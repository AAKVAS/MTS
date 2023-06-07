package com.example.mts.modules.data.repository;

import com.example.mts.modules.data.source.ModulesDataStore;
import com.example.mts.modules.domain.entity.Module;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Реализация репозитория работы с модулями.
 */
public class ModulesRepositoryImpl implements ModulesRepository {

    ModulesDataStore modulesDataStore;

    /**
     * Конструктор класса ModulesRepositoryImpl.
     * @param modulesDataStore Хранилище модулей.
     */
    @Inject
    public ModulesRepositoryImpl(ModulesDataStore modulesDataStore) {
        this.modulesDataStore = modulesDataStore;
    }

    /**
     * Возвращает список модулей из БД.
     * @return список модулей.
     */
    public Maybe<List<Module>> getModules() {
        return Maybe.create(emitter -> {
            try {
                List<Module> modules = modulesDataStore.getModules();
                emitter.onSuccess(modules);
            } catch(SQLException e) {
                emitter.onError(e);
            }
        });
    }
}
