package com.example.mts.main.di;

import com.example.mts.main.presentation.MainActivity;
import com.example.mts.main.presentation.MainActivityPresenter;
import com.example.mts.main.presentation.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль главной активности приложения. Предоставляет реализации для внедрения.
 */
@Module
public class MainActivityModule {
    /**
     * Главная активность приложения.
     */
    private MainActivity mainActivity;

    /**
     * Конструктор класса MainActivityModule.
     * @param mainActivity главная активность приложения.
     */
    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Возвращает реализацию представителя главной активности приложения.
     * @return представитель главной активности приложения.
     */
    @Provides
    @MainActivityScope
    public MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter(mainActivity);
    }

    /**
     * Возвращает представление главной активности.
     * @return представление главной активности.
     */
    @Provides
    public MainView provideMainView() {
        return mainActivity;
    }
}
