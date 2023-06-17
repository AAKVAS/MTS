package com.example.mts.connectedEquipment.presentation.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentComponent;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentModule;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.domain.entity.SwitchboardModel;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentItemPresenter;
import com.example.mts.connectedEquipment.presentation.view.adapters.BuildingSpinnerAdapter;
import com.example.mts.connectedEquipment.presentation.view.adapters.CableSpinnerAdapter;
import com.example.mts.connectedEquipment.presentation.view.adapters.SwitchboardAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Активность работы с записью модуля "Подключённое оборудование".
 */
public class ConnectedEquipmentItemActivity extends AppCompatActivity implements ConnectedEquipmentItemView {

    /**
     * Константное имя параметра подключённого оборудования для интента.
     */
    public static final String CONNECTED_EQUIPMENT = "connectedEquipment";

    /**
     * Константное имя параметра, определяющего режим открытия окна работы с записью.
     */
    public static final String IS_CREATING = "isCreating";

    /**
     * Представитель активности.
     */
    @Inject
    ConnectedEquipmentItemPresenter presenter;

    /**
     * Компонент для реализации внедрения зависимости.
     */
    private ConnectedEquipmentComponent connectedEquipmentComponent;

    /**
     * Выпадающий список зданий.
     */
    private Spinner sp_address;

    /**
     * Выпадающий список распределительных щитков.
     */
    private Spinner sp_switchboard;

    /**
     * Поле ввода порта.
     */
    private EditText et_port;

    /**
     * Выпадающий список моделей кабелей.
     */
    private Spinner sp_cable_model;

    /**
     * Поле ввода длины кабеля.
     */
    private EditText et_cable_length;

    /**
     * Поле ввода ip-адреса оборудования.
     */
    private EditText et_ip;

    /**
     * Поле ввода mac-адреса оборудования.
     */
    private EditText et_mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_equipment_item);
        inject();

        Intent intent = getIntent();
        if (intent.hasExtra(IS_CREATING)) {
            presenter.setConnectedEquipmentCreated(intent.getBooleanExtra(IS_CREATING, false));
            presenter.setEmptyCurrentConnectedEquipment();
        }
        if (intent.hasExtra(CONNECTED_EQUIPMENT) && !presenter.isConnectedEquipmentCreated()) {
            presenter.setCurrentConnectedEquipment((ConnectedEquipment) intent.getSerializableExtra(CONNECTED_EQUIPMENT));
        }

        sp_address = findViewById(R.id.sp_address);
        sp_switchboard = findViewById(R.id.sp_switchboard);
        et_port = findViewById(R.id.et_port);
        sp_cable_model = findViewById(R.id.sp_cables);
        et_cable_length = findViewById(R.id.et_cable_length);
        et_ip = findViewById(R.id.et_connected_equipment_ip);
        et_mac = findViewById(R.id.et_connected_equipment_mac);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    /**
     * Внедряет зависимости.
     */
    private void inject() {
        ConnectedEquipmentModule.setConnectedEquipmentItemActivity(this);
        connectedEquipmentComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusConnectedEquipmentListActivityComponent(ConnectedEquipmentModule.getInstance());
        connectedEquipmentComponent.inject(this);
    }

    @Override
    public void fillConnectedEquipmentItemView() {
        try {
            CableSpinnerAdapter cableSpinnerAdapter = new CableSpinnerAdapter(this, presenter.getCables());
            sp_cable_model.setAdapter(cableSpinnerAdapter);

            BuildingSpinnerAdapter buildingSpinnerAdapter = new BuildingSpinnerAdapter(this, presenter.getBuildings());
            sp_address.setAdapter(buildingSpinnerAdapter);
            sp_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    List<Switchboard> switchboards = new ArrayList(buildingSpinnerAdapter.getItem(position).getSwitchboardList());
                    SwitchboardAdapter switchboardAdapter = new SwitchboardAdapter(ConnectedEquipmentItemActivity.this, switchboards);
                    sp_switchboard.setAdapter(switchboardAdapter);
                    sp_switchboard.setSelection(switchboardAdapter.getPosition(presenter.getCurrentConnectedEquipment().getSwitchboard()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });

            if (!presenter.isConnectedEquipmentCreated()) {
                ConnectedEquipment connectedEquipment = presenter.getCurrentConnectedEquipment();
                sp_cable_model.setSelection(cableSpinnerAdapter.getPosition(connectedEquipment.getCable()));

                int buildingPos = buildingSpinnerAdapter.getPosition(connectedEquipment.getSwitchboard().getBuilding());
                sp_address.setSelection(buildingPos);

                et_port.setText(String.valueOf(connectedEquipment.getPortNumber()));
                et_cable_length.setText(String.valueOf(connectedEquipment.getCableLength()));
                et_ip.setText(connectedEquipment.getIp());
                et_mac.setText(connectedEquipment.getMac());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Срабатывает при нажатии на кнопку сохранения.
     * @param view представление, вызвавшее метод.
     */
    public void btnSaveClick(View view) {
        try {
            ConnectedEquipment connectedEquipment = presenter.getCurrentConnectedEquipment();

            Switchboard switchboard = ((Switchboard) sp_switchboard.getSelectedItem());
            connectedEquipment.setSwitchboard(switchboard);

            connectedEquipment.setPortNumber(Integer.parseInt("0" + et_port.getText().toString()));
            connectedEquipment.setCable((Cable) sp_cable_model.getSelectedItem());
            connectedEquipment.setCableLength(Integer.parseInt("0" + et_cable_length.getText().toString()));

            connectedEquipment.setIp(et_ip.getText().toString());
            connectedEquipment.setMac(et_mac.getText().toString());

            if (presenter.isRecordValid()) {
                presenter.saveConnectedEquipment();
            } else {
                showRecordErrors();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Вызывает окно с сообщениями об ошибках в корректности свойств записи.
     */
    public void showRecordErrors() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(presenter.getRecordErrors())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Вызывает окно с сообщением о занятом порте для данного распределительного щитка.
     */
    @Override
    public void showUniquenessError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.connected_equipment_uniqueness_error)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void close() {
        Intent intent = new Intent();
        intent.putExtra(ConnectedEquipmentListActivity.IS_VIEW_NEED_TO_RELOAD, true);
        setResult(RESULT_OK, intent);
        finish();
    }
}