package com.example.mts.connectedEquipment.di;

import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListActivity;

import dagger.Subcomponent;

/**
 * Компонент подключённого оборудования.
 */
@Subcomponent(modules = {ConnectedEquipmentModule.class})
@ConnectedEquipmentScope
public interface ConnectedEquipmentComponent {
    void inject(ConnectedEquipmentListActivity connectedEquipmentListActivity);
}
