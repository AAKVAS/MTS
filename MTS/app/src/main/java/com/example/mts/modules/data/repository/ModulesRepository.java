package com.example.mts.modules.data.repository;

import com.example.mts.modules.domain.entity.Module;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Интерфейс-репозиторий работы с модулями.
 */
public interface ModulesRepository {
    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    Maybe<List<Module>> getModules();
}
