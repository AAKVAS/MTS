package com.example.mts.modules.presentation.view;

import com.example.mts.modules.domain.entity.Module;

/**
 * Представление модуля "Модули".
 */
public interface ModulesView {

    /**
     * Заполняет список модулей значениями из представителя.
     */
    void fillModuleList();

    /**
     * Открывает активность для переданного модуля.
     * @param module открываемый модуль.
     */
    void openModule(Module module);
}
