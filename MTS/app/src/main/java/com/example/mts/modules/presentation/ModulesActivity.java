package com.example.mts.modules.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.modules.di.ModulesActivityComponent;
import com.example.mts.modules.di.ModulesActivityModule;

import javax.inject.Inject;

/**
 * Активность "Модули".
 */
public class ModulesActivity extends AppCompatActivity implements ModuleView {
    /**
     * Представитель активности.
     */
    @Inject
    ModulesActivityPresenter presenter;

    /**
     * Компонент для реализации внедрения зависимости.
     */
    private ModulesActivityComponent modulesActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        inject();
    }

    /**
     * Внедряет зависимости.
     */
    private void inject() {
        modulesActivityComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusModulesActivityComponent(new ModulesActivityModule(this));
        modulesActivityComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fillModules();
    }

    @Override
    public void fillModuleList() {
        RecyclerView recyclerView = findViewById(R.id.rv_module);
        ModulesAdapter adapter = new ModulesAdapter(presenter.getModules());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}