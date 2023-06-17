package com.example.mts.connectedEquipment.presentation.presenter;

import com.example.mts.connectedEquipment.domain.entity.Building;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;
import com.example.mts.connectedEquipment.domain.interactor.CreateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetBuildingsUseCase;
import com.example.mts.connectedEquipment.domain.interactor.GetCablesUseCase;
import com.example.mts.connectedEquipment.domain.interactor.UpdateConnectedEquipmentUseCase;
import com.example.mts.connectedEquipment.presentation.view.ConnectedEquipmentItemView;
import com.example.mts.utils.Validator;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Представитель для работы с записью о подключённом оборудовании.
 */
public class ConnectedEquipmentItemPresenter {

    /**
     * Представление активности.
     */
    ConnectedEquipmentItemView view;

    /**
     * UseСase создания записи подключённого оборудования.
     */
    CreateConnectedEquipmentUseCase createConnectedEquipmentUseCase;

    /**
     * UseСase обновления записи подключённого оборудования.
     */
    UpdateConnectedEquipmentUseCase updateConnectedEquipmentUseCase;

    /**
     * UseСase получения моделей кабелей.
     */
    GetCablesUseCase getCablesUseCase;

    /**
     * UseСase получения зданий.
     */
    GetBuildingsUseCase getBuildingsUseCase;

    /**
     * Текущая запись.
     */
    private ConnectedEquipment currentConnectedEquipment;

    /**
     * Список моделей кабелей.
     */
    private List<Cable> cables;

    /**
     * Список зданий.
     */
    private List<Building> buildings;

    /**
     * Список ошибок в записи.
     */
    private String recordErrors;

    /**
     * Определяет, открыта ли запись на создание.
     */
    private boolean isConnectedEquipmentCreated = false;

    /**
     * Возвращает текущую запись.
     * @return текущая запись.
     */
    public ConnectedEquipment getCurrentConnectedEquipment() {
        return currentConnectedEquipment;
    }

    /**
     * Устанавливает текущую запись.
     * @param currentConnectedEquipment текущая запись.
     */
    public void setCurrentConnectedEquipment(ConnectedEquipment currentConnectedEquipment) {
        this.currentConnectedEquipment = currentConnectedEquipment;
    }

    /**
     * Возвращает список моделей кабелей.
     * @return список моделей кабелей.
     */
    public List<Cable> getCables() {
        return cables;
    }

    /**
     * Возвращает список зданий.
     * @return список зданий.
     */
    public List<Building> getBuildings() {
        return buildings;
    }

    /**
     * Возвращает истину, если представление записи открыто на добавление.
     * @return истина, если представление записи открыто на добавление.
     */
    public boolean isConnectedEquipmentCreated() {
        return isConnectedEquipmentCreated;
    }

    /**
     * Устанавливает значение, открыта ли запись на создание.
     * @param connectedEquipmentCreated открыта ли запись на создание.
     */
    public void setConnectedEquipmentCreated(boolean connectedEquipmentCreated) {
        isConnectedEquipmentCreated = connectedEquipmentCreated;
    }

    /**
     * Возвращает список ошибок в свойствах записи.
     * @return список ошибок в свойствах записи.
     */
    public String getRecordErrors() {
        return recordErrors;
    }

    /**
     * Конструктор по умолчанию.
     */
    public ConnectedEquipmentItemPresenter() {}

    /**
     * Конструктор класса ConnectedEquipmentItemPresenter.
     * @param view представление модуля.
     * @param createConnectedEquipmentUseCase UseCase создания подключённого оборудования.
     * @param updateConnectedEquipmentUseCase UseCase обновления подключённого оборудования.
     * @param getCablesUseCase UseCase загрузки списка моделей кабелей.
     * @param getBuildingsUseCase UseCase загрузки списка зданий.
     */
    @Inject
    public ConnectedEquipmentItemPresenter(ConnectedEquipmentItemView view,
                                           CreateConnectedEquipmentUseCase createConnectedEquipmentUseCase,
                                           UpdateConnectedEquipmentUseCase updateConnectedEquipmentUseCase,
                                           GetCablesUseCase getCablesUseCase,
                                           GetBuildingsUseCase getBuildingsUseCase
                                           ) {
        this.view = view;
        this.createConnectedEquipmentUseCase = createConnectedEquipmentUseCase;
        this.updateConnectedEquipmentUseCase = updateConnectedEquipmentUseCase;
        this.getCablesUseCase = getCablesUseCase;
        this.getBuildingsUseCase = getBuildingsUseCase;
    }

    public void setEmptyCurrentConnectedEquipment() {
        currentConnectedEquipment = new ConnectedEquipment();
        Cable newCable = new Cable();
        Switchboard newSwitchboard = new Switchboard();
        SwitchboardModel newSwitchboardModel = new SwitchboardModel();

        newSwitchboard.setModel(newSwitchboardModel);
        currentConnectedEquipment.setSwitchboard(newSwitchboard);
        currentConnectedEquipment.setCable(newCable);
    }

    /**
     * Используется при срабатывании события onResume() в представлении.
     */
    public void onResume() {
        getCablesUseCase.execute().subscribe(new LoadCablesObserver());
    }

    /**
     * Класс-наблюдатель, загружающий список моделей кабелей.
     */
    private class LoadCablesObserver implements SingleObserver<List<Cable>> {

        @Override
        public void onSubscribe(Disposable d) {}

        /**
         * Срабатывает при успешной загрузке список моделей кабелей. Начинает выгрузку зданий.
         * @param cables список моделей кабелей.
         */
        @Override
        public void onSuccess(List<Cable> cables) {
            ConnectedEquipmentItemPresenter.this.cables = cables;
            getBuildingsUseCase.execute().subscribe(new LoadBuildingsObserver());
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при получении списка кабелей.");
        }
    }

    /**
     * Класс-наблюдатель, загружающий список зданий.
     */
    private class LoadBuildingsObserver implements SingleObserver<List<Building>> {

        @Override
        public void onSubscribe(Disposable d) {}

        /**
         * Срабатывает при успешной загрузке зданий. Заполняет данными представление.
         * @param buildings список зданий.
         */
        @Override
        public void onSuccess(List<Building> buildings) {
            ConnectedEquipmentItemPresenter.this.buildings = buildings;
            view.fillConnectedEquipmentItemView();
        }

        @Override
        public void onError(Throwable e) {
            throw new RuntimeException("Возникла ошибка при получении списка зданий.");
        }
    }

    /**
     * Возвращает истину, если свойства записи корректны.
     * @return истина, если свойства записи корректны.
     */
    public boolean isRecordValid() {
        StringBuilder errorsBuilder = new StringBuilder();
        if (currentConnectedEquipment.getPortNumber() > currentConnectedEquipment.getSwitchboard().getModel().getMaxPortAmount()) {
            errorsBuilder.append("Номер порта больше позволенного. Максимальный порт модели щитка - " + currentConnectedEquipment.getSwitchboard().getModel().getMaxPortAmount() + ";\n");
        }
        if (currentConnectedEquipment.getPortNumber() <= 0) {
            errorsBuilder.append("Номер порта должен быть больше 0;\n");
        }
        if (!Validator.isIpValid(currentConnectedEquipment.getIp()))
        {
            errorsBuilder.append("Неверный IP-адрес;\n");
        }
        if (!Validator.isMacValid(currentConnectedEquipment.getMac()))
        {
            errorsBuilder.append("Неверный MAC-адрес;\n");
        }
        recordErrors = errorsBuilder.toString();
        return recordErrors.isEmpty();
    }

    /**
     * Сохраняет запись в БД.
     */
    public void saveConnectedEquipment() {
        if (isConnectedEquipmentCreated) {
            createConnectedEquipmentUseCase.execute(new CreateConnectedEquipmentUseCase.Param(currentConnectedEquipment)).subscribe(new CreateObserver());
        }
        else {
            updateConnectedEquipmentUseCase.execute(new UpdateConnectedEquipmentUseCase.Param(currentConnectedEquipment)).subscribe(new UpdateObserver());
        }
    }

    /**
     * Класс-наблюдатель, создающий новую запись.
     */
    private class CreateObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {}

        /**
         * Срабатывает при успешном сохранении. Закрывает представление.
         */
        @Override
        public void onComplete() {
            view.close();
        }

        @Override
        public void onError(Throwable e) {
            if (e.getClass() == SQLException.class) {
                view.showUniquenessError();
            } else {
                throw new RuntimeException("Произошла ошибка при добавлении записи.");
            }
        }
    }

    /**
     * Класс-наблюдатель, обновляющий запись.
     */
    private class UpdateObserver implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {}

        /**
         * Срабатывает при успешном сохранении. Закрывает представление.
         */
        @Override
        public void onComplete() {
            view.close();
        }

        @Override
        public void onError(Throwable e) {
            if (e.getClass() == SQLException.class) {
                view.showUniquenessError();
            } else {
                throw new RuntimeException("Произошла ошибка при изменении записи.");
            }
        }
    }
}
