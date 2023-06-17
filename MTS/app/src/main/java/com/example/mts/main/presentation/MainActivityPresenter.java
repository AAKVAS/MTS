package com.example.mts.main.presentation;

import javax.inject.Inject;

/**
 * Представитель главной активности приложения.
 */
public class MainActivityPresenter {

    /**
     * Представление главной активности.
     */
    MainView mainView;

    /**
     * Конструктор класса MainActivityPresenter по умолчанию.
     */
    public MainActivityPresenter() {}

    /**
     * Конструктор класса MainActivityPresenter.
     * @param mainView представление главной активности.
     */
    @Inject
    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Возникает при нажатии на кнопку перехода к разделам.
     */
    public void btnLoginClick() {
        mainView.openModulesView();
    }
}
