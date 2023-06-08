package com.example.mts;

import android.app.Application;

import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentListActivity;
import com.example.mts.database.DatabaseHelperFactory;
import com.example.mts.di.AppComponent;
import com.example.mts.di.AppModule;
import com.example.mts.di.DaggerAppComponent;
import com.example.mts.modules.domain.entity.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс приложения.
 */
public class MTSApplication extends Application {

    /**
     * Экземпляр запущенного приложения.
     */
    protected static MTSApplication instance;

    /**
     * Возвращает экземляр приложения.
     * @return экземпляр приложения.
     */
    public static MTSApplication getInstance() {
        return instance;
    }

    /**
     * Компонент приложения, используемый для внедрения зависимостей.
     */
    private AppComponent appComponent;

    private List<Module> appModules = new ArrayList<Module>(
            Arrays.asList(new Module("Подключённое оборудование", ConnectedEquipmentListActivity.class)
            ));

    public List<Module> getAppModules() {
        return appModules;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        DatabaseHelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        DatabaseHelperFactory.releaseHelper();
        super.onTerminate();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}