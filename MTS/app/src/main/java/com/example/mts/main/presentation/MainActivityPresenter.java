package com.example.mts.main.presentation;

import javax.inject.Inject;

public class MainActivityPresenter {
    MainView mainView;

    public MainActivityPresenter() {}

    @Inject
    public MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void btnLoginClick() {
        mainView.openModulesView();
    }
}
