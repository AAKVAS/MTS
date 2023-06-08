package com.example.mts.connectedEquipment.data.repository;

import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import java.util.List;
import io.reactivex.Maybe;

/**
 * Интерфейс-репозиторий работы с подключённым оборудованием.
 */
public interface ConnectedEquipmentRepository {
    /**
     * Возвращает список модулей.
     * @return список модулей.
     */
    Maybe<List<ConnectedEquipment>> getConnectedEquipment();
}