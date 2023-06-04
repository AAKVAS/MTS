package com.example.mts.di;

import com.example.mts.main.di.MainActivityComponent;
import com.example.mts.main.di.MainActivityModule;
import com.example.mts.modules.di.ModulesActivityComponent;
import com.example.mts.modules.di.ModulesActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    MainActivityComponent plusMainActivityComponent(MainActivityModule mainActivityModule);
    ModulesActivityComponent plusModulesActivityComponent(ModulesActivityModule modulesActivityModule);
}
