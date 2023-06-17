package com.example.mts.modules.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Область активности "Модули".
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ModulesScope {
}
