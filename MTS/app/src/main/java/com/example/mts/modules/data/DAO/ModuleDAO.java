package com.example.mts.modules.data.DAO;

import com.example.mts.modules.domain.entity.Module;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * DAO-класс для модулей.
 */
public class ModuleDAO extends BaseDaoImpl<Module, Integer> {

    /**
     * Конструктор класса ModuleDAO.
     * @param connectionSource источник подключения.
     * @param dataClass связанный с DAO класс.
     * @throws SQLException
     */
    public ModuleDAO(ConnectionSource connectionSource, Class<Module> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
