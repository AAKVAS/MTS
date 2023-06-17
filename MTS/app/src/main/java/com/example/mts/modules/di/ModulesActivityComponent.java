package com.example.mts.modules.di;

import com.example.mts.modules.presentation.view.ModulesActivity;

import dagger.Subcomponent;

/**
 * Компонент активности "Модули". Используется для внедрения зависимостей.
 */
@Subcomponent(modules = {ModulesActivityModule.class})
@ModulesScope
public interface ModulesActivityComponent {
    /**
     * Внедряет зависимости в активность списка модулей.
     * @param modulesActivity активность списка модулей.
     */
    void inject(ModulesActivity modulesActivity);
}
