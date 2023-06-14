package com.example.mts.modules.domain.entity;

/**
 * Модуль мобильной торговой системы.
 */
public class Module {

    /**
     * Название модуля.
     */
    private String name;

    /**
     * Класс активности модуля.
     */
    private Class moduleActivityClass;

    /**
     * Конструктор по умолчанию.
     */
    public Module() {}

    /**
     * Конструктор класса Module.
     * @param name название модуля.
     * @param moduleActivityClass класс активности модуля.
     */
    public Module(String name, Class moduleActivityClass) {
        this.name = name;
        this.moduleActivityClass = moduleActivityClass;
    }

    /**
     * Возвращает название модуля.
     * @return id.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название модуля.
     * @param name название модуля.
     */
    public void setName(String name) {
        this.name = name;
    }

    public Class getModuleActivityClass() {
        return moduleActivityClass;
    }
}
