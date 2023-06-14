package com.example.mts.base;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Базовый UseCase для действий, возвращающих в качестве результата Single
 * @param <T> репозиторий для выполнения действия.
 * @param <V> тип данных, который оборачивается в Single
 */
public abstract class BaseSingleUseCase<T, V> {

    /**
     * Репозиторий для выполнения действия.
     */
    protected T repository;

    /**
     * Планировщик для выполнения действия.
     */
    protected Scheduler executorScheduler;

    /**
     * Конструктор класса BaseSingleUseCase.
     * @param repository репозиторий для выполения действия.
     * @param executorScheduler планировщик для выполнения действия.
     */
    public BaseSingleUseCase(T repository, Scheduler executorScheduler) {
        this.repository = repository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение usecase.
     * @return результат выполнения действия.
     */
    public abstract Single<V> execute();

}
