package com.example.mts.modules.presentation;

import javax.inject.Inject;

public class ModulesActivityPresenter {
    ModuleView moduleView;

    public ModulesActivityPresenter() {}

    @Inject
    public ModulesActivityPresenter(ModuleView moduleView) {
        this.moduleView = moduleView;
    }
}
