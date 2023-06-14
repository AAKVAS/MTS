package com.example.mts.base;

import io.reactivex.Completable;
import io.reactivex.Scheduler;

/**
 * Базовый UseCase для действий, возвращающих в качестве результата Completable
 * @param <T> репозиторий для выполнения действия.
 * @param <V> параметры для выполения действия.
 */
public abstract class BaseCompletableUseCase<T, V> {
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
    public BaseCompletableUseCase(T repository, Scheduler executorScheduler) {
        this.repository = repository;
        this.executorScheduler = executorScheduler;
    }

    /**
     * Выполнение usecase.
     * @param param параметр для выполнения действия.
     * @return результат выполнения действия.
     */
    public abstract Completable execute(V param);

}
