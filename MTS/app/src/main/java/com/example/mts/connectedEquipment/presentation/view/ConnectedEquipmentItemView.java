package com.example.mts.connectedEquipment.presentation.view;

/**
 * Представление работы с записью о подключённом оборудовании.
 */
public interface ConnectedEquipmentItemView {
    /**
     * Заполняет данными представление.
     */
    void fillConnectedEquipmentItemView();

    /**
     * Закрывает представление.
     */
    void close();

    /**
     * Показывает сообщение об ошибках в свойствах записи.
     */
    void showUniquenessError();
}
