package com.example.mts.modules.di;

import com.example.mts.modules.presentation.ModuleView;
import com.example.mts.modules.presentation.ModulesActivity;
import com.example.mts.modules.presentation.ModulesActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModulesActivityModule {

    private ModulesActivity modulesActivity;

    public ModulesActivityModule(ModulesActivity modulesActivity) {
        this.modulesActivity = modulesActivity;
    }
    @Provides
    @ModulesScope
    public ModulesActivityPresenter provideModuleActivityPresenter() {
        return new ModulesActivityPresenter(modulesActivity);
    }

    @Provides
    public ModuleView provideModuleView() {
        return modulesActivity;
    }
}
