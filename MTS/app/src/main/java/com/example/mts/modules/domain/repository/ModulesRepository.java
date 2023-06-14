package com.example.mts.modules.domain.repository;

import com.example.mts.modules.domain.entity.Module;

import java.util.List;

import io.reactivex.Single;

/**
 * Интерфейс-репозиторий работы с модулями.
 */
public interface ModulesRepository {
    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    Single<List<Module>> getModules();
}
