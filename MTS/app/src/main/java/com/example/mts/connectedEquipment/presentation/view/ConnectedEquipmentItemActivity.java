package com.example.mts.connectedEquipment.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentComponent;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentModule;
import com.example.mts.connectedEquipment.domain.entity.Cable;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentItemPresenter;
import com.example.mts.connectedEquipment.presentation.view.adapters.BuildingAdapter;
import com.example.mts.connectedEquipment.presentation.view.adapters.CableSpinnerAdapter;
import com.example.mts.connectedEquipment.presentation.view.adapters.SwitchboardAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ConnectedEquipmentItemActivity extends AppCompatActivity implements ConnectedEquipmentItemView {

    public static final String CONNECTED_EQUIPMENT = "connectedEquipment";

    /**
     * Представитель активности.
     */
    @Inject
    ConnectedEquipmentItemPresenter presenter;

    /**
     * Компонент для реализации внедрения зависимости.
     */
    private ConnectedEquipmentComponent connectedEquipmentComponent;

    private Spinner sp_address;
    Spinner sp_switchboard;
    EditText et_port;
    Spinner sp_cable_model;
    EditText et_cable_length;
    EditText et_ip;
    EditText et_mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_equipment_item);
        inject();

        Intent intent = getIntent();
        if (intent.hasExtra(CONNECTED_EQUIPMENT)) {
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
            sp_cable_model.setSelection(cableSpinnerAdapter.getPosition(presenter.getCurrentConnectedEquipment().getCable()));

            BuildingAdapter buildingAdapter = new BuildingAdapter(this, presenter.getBuildings());
            sp_address.setAdapter(buildingAdapter);
            sp_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    List<Switchboard> switchboards = new ArrayList(buildingAdapter.getItem(position).getSwitchboardList());
                    SwitchboardAdapter switchboardAdapter = new SwitchboardAdapter(ConnectedEquipmentItemActivity.this, switchboards);
                    sp_switchboard.setAdapter(switchboardAdapter);
                    sp_switchboard.setSelection(switchboardAdapter.getPosition(presenter.getCurrentConnectedEquipment().getSwitchboard()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
            int buildingPos = buildingAdapter.getPosition(presenter.getCurrentConnectedEquipment().getSwitchboard().getBuilding());

            sp_address.setSelection(buildingPos);

            ConnectedEquipment connectedEquipment = presenter.getCurrentConnectedEquipment();
            et_port.setText(String.valueOf(connectedEquipment.getPortNumber()));
            et_cable_length.setText(String.valueOf(connectedEquipment.getCableLength()));
            et_ip.setText(connectedEquipment.getIp());
            et_mac.setText(connectedEquipment.getMac());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void btnSaveClick(View view) {
        try {
            ConnectedEquipment connectedEquipment = presenter.getCurrentConnectedEquipment();

            Switchboard switchboard = ((Switchboard) sp_switchboard.getSelectedItem());
            connectedEquipment.setSwitchboard(switchboard);

            connectedEquipment.setPortNumber(Integer.parseInt(et_port.getText().toString()));
            connectedEquipment.setCable((Cable) sp_cable_model.getSelectedItem());
            connectedEquipment.setCableLength(Integer.parseInt(et_cable_length.getText().toString()));

            connectedEquipment.setIp(et_ip.getText().toString());
            connectedEquipment.setMac(et_mac.getText().toString());

            presenter.saveConnectedEquipment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        Intent intent = new Intent();
        intent.putExtra(ConnectedEquipmentListActivity.IS_VIEW_NEED_TO_RELOAD, true);
        setResult(RESULT_OK, intent);
        finish();
    }
}