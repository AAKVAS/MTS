package com.example.mts.connectedEquipment.presentation.view;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;

/**
 * Представление списка модуля "Подключённое оборудование".
 */
public interface ConnectedEquipmentListView {
    /**
     * Заполняет список оборудования значениями из представителя.
     */
    void fillConnectedEquipmentList();
    void openConnectedEqipmentItem(ConnectedEquipment connectedEquipment);
}
