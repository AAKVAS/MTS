package com.example.mts.modules.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.main.presentation.MainActivityPresenter;
import com.example.mts.modules.di.ModulesActivityComponent;
import com.example.mts.modules.di.ModulesActivityModule;

import javax.inject.Inject;

public class ModulesActivity extends AppCompatActivity implements ModuleView {

    @Inject
    ModulesActivityPresenter presenter;

    private ModulesActivityComponent modulesActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        inject();
    }

    private void inject() {
        modulesActivityComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusModulesActivityComponent(new ModulesActivityModule(this));
        modulesActivityComponent.inject(this);
    }
}