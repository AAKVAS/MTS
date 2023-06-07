package com.example.mts.modules.data.source;

import com.example.mts.modules.data.DAO.ModuleDAO;
import com.example.mts.modules.domain.entity.Module;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Класс-хранилище модулей.
 */
public class ModulesDataStore {

    /**
     * DAO модулей.
     */
    ModuleDAO moduleDAO;

    /**
     * Конструктор по умолчанию.
     */
    public ModulesDataStore() {}

    /**
     * Конструктор класса ModulesDataStore.
     * @param moduleDAO DAO модулей.
     */
    @Inject
    public ModulesDataStore(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

    /**
     * Возвращает список модулей из БД.
     * @return список модулей.
     * @throws SQLException
     */
    public List<Module> getModules() throws SQLException {
        return moduleDAO.queryForAll();
    }
}
