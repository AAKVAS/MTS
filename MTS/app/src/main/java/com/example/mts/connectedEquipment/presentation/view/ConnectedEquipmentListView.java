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

    /**
     * Открывает окно изменения записи о подключённом оборудовании.
     * @param connectedEquipment подключённое оборудование.
     */
    void openConnectedEquipmentItem(ConnectedEquipment connectedEquipment);

    /**
     * Открывает окно добавления записи о подключённом оборудовании.
     * @param connectedEquipment подключённое оборудование.
     */
    void createConnectedEquipmentItem(ConnectedEquipment connectedEquipment);

    /**
     * Открывает диалоговое окно подтверждения удаления подключённого оборудования.
     */
    void openDeleteConnectedEquipmentDialog();
}
