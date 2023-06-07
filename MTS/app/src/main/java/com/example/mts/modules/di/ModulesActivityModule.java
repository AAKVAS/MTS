package com.example.mts.modules.di;

import com.example.mts.modules.data.DAO.ModuleDAO;
import com.example.mts.modules.data.repository.ModulesRepository;
import com.example.mts.modules.data.repository.ModulesRepositoryImpl;
import com.example.mts.modules.data.source.ModulesDataStore;
import com.example.mts.modules.domain.interactor.GetModulesUseCase;
import com.example.mts.modules.presentation.ModuleView;
import com.example.mts.modules.presentation.ModulesActivity;
import com.example.mts.modules.presentation.ModulesActivityPresenter;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModulesActivityModule {

    private ModulesActivity modulesActivity;

    public ModulesActivityModule(ModulesActivity modulesActivity) {
        this.modulesActivity = modulesActivity;
    }
    @Provides
    @ModulesScope
    public ModulesActivityPresenter provideModuleActivityPresenter(GetModulesUseCase useCase) {
        return new ModulesActivityPresenter(modulesActivity, useCase);
    }

    @Provides
    public ModuleView provideModuleView() {
        return modulesActivity;
    }

    @Provides
    @ModulesScope
    public GetModulesUseCase provideGetModulesUseCase(ModulesRepository repository) {
        return new GetModulesUseCase(repository, Schedulers.io());
    }

    @Provides
    @ModulesScope
    public ModulesRepository provideModulesRepository(ModulesDataStore modulesDataStore) {
        return new ModulesRepositoryImpl(modulesDataStore);
    }

    @Provides
    @ModulesScope
    public ModulesDataStore provideModulesDataStore(ModuleDAO moduleDAO) {
        return new ModulesDataStore(moduleDAO);
    }

    @Provides
    @ModulesScope
    public ModuleDAO provideModuleDAO(ConnectionSource connectionSource) {
        try {
            return new ModuleDAO(connectionSource, com.example.mts.modules.domain.entity.Module.class);
        }
        catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
