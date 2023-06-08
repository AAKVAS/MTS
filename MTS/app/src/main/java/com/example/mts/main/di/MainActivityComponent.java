package com.example.mts.main.di;

import com.example.mts.main.presentation.MainActivity;

import dagger.Subcomponent;

/**
 * Компонент главной активности приложения. Используется для внедрения зависимостей.
 */
@Subcomponent(modules = {MainActivityModule.class})
@MainActivityScope
public interface MainActivityComponent {
    /**
     * Внедряет зависимости в главную активность.
     * @param activity главная активность.
     */
    void inject(MainActivity activity);
}
