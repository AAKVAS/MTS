package com.example.mts.main.di;

import com.example.mts.main.presentation.MainActivity;
import com.example.mts.main.presentation.MainActivityPresenter;
import com.example.mts.main.presentation.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Provides
    @MainActivityScope
    public MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter(mainActivity);
    }

    @Provides
    public MainView provideMainView() {
        return mainActivity;
    }
}
