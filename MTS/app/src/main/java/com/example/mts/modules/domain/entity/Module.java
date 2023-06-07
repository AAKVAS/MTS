package com.example.mts.modules.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Модуль мобильной торговой системы.
 */
@DatabaseTable(tableName = "MODULES")
public class Module {
    /**
     * Название атрибута UID.
     */
    public static final String UID = "UID";
    /**
     * Название атрибута NAME.
     */
    public static final String NAME = "NAME";

    /**
     * Id модуля.
     */
    @DatabaseField(generatedId = true, columnName = UID)
    private int id;

    /**
     * Название модуля.
     */
    @DatabaseField(columnName = NAME)
    private String name;

    /**
     * Конструктор по умолчанию.
     */
    public Module() {}

    /**
     * Конструктор класса Module.
     * @param name название модуля.
     */
    public Module(String name) {
        this.name = name;
    }

    /**
     * Конструктор класса Module.
     * @param id идентификатор модуля.
     * @param name название модуля.
     */
    public Module(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает id модуля.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает id модуля.
     * @param id идентификатор модуля.
     */
    public void setId(int id) {
        this.id = id;
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
}
