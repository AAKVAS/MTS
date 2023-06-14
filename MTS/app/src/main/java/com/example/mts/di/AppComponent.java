package com.example.mts.di;

import com.example.mts.connectedEquipment.di.ConnectedEquipmentComponent;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentModule;
import com.example.mts.main.di.MainActivityComponent;
import com.example.mts.main.di.MainActivityModule;
import com.example.mts.modules.di.ModulesActivityComponent;
import com.example.mts.modules.di.ModulesActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Компонент приложения, используемый для внедрения зависимостей.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    /**
     * Настраивает компонент главной активности приложения.
     * @param mainActivityModule модуль активности приложения.
     * @return компонент главной активности приложения.
     */
    MainActivityComponent plusMainActivityComponent(MainActivityModule mainActivityModule);

    /**
     * Настраивает компонент активности модулей приложения.
     * @param modulesActivityModule модуль активности модулей приложения.
     * @return компонент активности модулей приложения.
     */
    ModulesActivityComponent plusModulesActivityComponent(ModulesActivityModule modulesActivityModule);

    /**
     * Настраивает компонент подключённого оборудования.
     * @param connectedEquipmentModule модуль подключённого оборудования.
     * @return компонент подключённого оборудования.
     */
    ConnectedEquipmentComponent plusConnectedEquipmentListActivityComponent(ConnectedEquipmentModule connectedEquipmentModule);
}
