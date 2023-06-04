package com.example.mts.modules.di;

import com.example.mts.modules.presentation.ModulesActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ModulesActivityModule.class})
@ModulesScope
public interface ModulesActivityComponent {
    void inject(ModulesActivity modulesActivity);
}
