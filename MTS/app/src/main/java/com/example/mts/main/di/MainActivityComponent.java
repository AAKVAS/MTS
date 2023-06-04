package com.example.mts.main.di;

import com.example.mts.main.presentation.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {MainActivityModule.class})
@MainActivityScope
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
