package com.example.mts.main.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Область главной активности.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainActivityScope {
}