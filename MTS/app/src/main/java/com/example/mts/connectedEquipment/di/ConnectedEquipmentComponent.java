package com.example.mts.connectedEquipment.di;

import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentItemActivity;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListActivity;

import dagger.Subcomponent;

/**
 * Компонент подключённого оборудования.
 */
@Subcomponent(modules = {ConnectedEquipmentModule.class})
@ConnectedEquipmentScope
public interface ConnectedEquipmentComponent {
    /**
     * Внедряет зависимости в активность списка подключённого оборудования.
     * @param connectedEquipmentListActivity активность списка подключённого оборудования.
     */
    void inject(ConnectedEquipmentListActivity connectedEquipmentListActivity);

    /**
     * Внедряет зависимости в активность работы с записью подключённого оборудования.
     * @param connectedEquipmentItemActivity активность работы с записью подключённого оборудования.
     */
    void inject(ConnectedEquipmentItemActivity connectedEquipmentItemActivity);
}
