package com.example.mts.main.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.main.di.MainActivityComponent;
import com.example.mts.main.di.MainActivityModule;
import com.example.mts.modules.presentation.view.ModulesActivity;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainActivityPresenter presenter;

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();
    }

    private void inject() {
        mainActivityComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusMainActivityComponent(new MainActivityModule(this));
        mainActivityComponent.inject(this);
    }

    @Override
    public void openModulesView() {
        Intent intent = new Intent(this, ModulesActivity.class);
        startActivity(intent);
    }

    public void btnLoginClick(View view) {
        presenter.btnLoginClick();
    }
}