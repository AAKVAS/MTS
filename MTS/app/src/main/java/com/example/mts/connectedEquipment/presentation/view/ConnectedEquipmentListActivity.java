package com.example.mts.connectedEquipment.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentComponent;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentModule;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentListPresenter;
import com.example.mts.modules.di.ModulesActivityComponent;
import com.example.mts.modules.di.ModulesActivityModule;
import com.example.mts.modules.domain.entity.Module;
import com.example.mts.modules.presentation.view.ModulesAdapter;

import javax.inject.Inject;

/**
 * Активность списка модуля "Подключённое оборудование".
 */
public class ConnectedEquipmentListActivity extends AppCompatActivity implements ConnectedEquipmentListView {

    /**
     * Представитель активности.
     */
    @Inject
    ConnectedEquipmentListPresenter presenter;

    /**
     * Компонент для реализации внедрения зависимости.
     */
    private ConnectedEquipmentComponent connectedEquipmentComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_equipment_list);
        inject();
    }

    /**
     * Внедряет зависимости.
     */
    private void inject() {
        connectedEquipmentComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusConnectedEquipmentListActivityComponent(new ConnectedEquipmentModule(this));
        connectedEquipmentComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fillConnectedEquipment();
    }


    @Override
    public void fillConnectedEquipmentList() {
        RecyclerView recyclerView = findViewById(R.id.rv_module);
        ConnectedEquipmentAdapter adapter = new ConnectedEquipmentAdapter(presenter.getConnectedEquipment(), new ConnectedEquipmentAdapter.OnClickListener() {
            @Override
            public void onClick(ConnectedEquipment connectedEquipment, int position) {
                presenter.onModuleClick(connectedEquipment);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openConnectedEqipmentItem(ConnectedEquipment connectedEquipment) {

    }
}