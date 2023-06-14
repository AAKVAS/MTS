package com.example.mts.connectedEquipment.presentation.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mts.MTSApplication;
import com.example.mts.R;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentComponent;
import com.example.mts.connectedEquipment.di.ConnectedEquipmentModule;
import com.example.mts.connectedEquipment.domain.entity.ConnectedEquipment;
import com.example.mts.connectedEquipment.presentation.presenter.ConnectedEquipmentListPresenter;
import com.example.mts.connectedEquipment.presentation.view.adapters.ConnectedEquipmentAdapter;

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

    private static final int REQUEST_CODE_ITEM = 1;

    public static final String IS_VIEW_NEED_TO_RELOAD = "isViewNeedToReload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_equipment_list);
        inject();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ITEM) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra(IS_VIEW_NEED_TO_RELOAD)) {
                    presenter.setViewNeedToReload(data.getBooleanExtra(IS_VIEW_NEED_TO_RELOAD, false));
                }
            }
        }
    }


    /**
     * Внедряет зависимости.
     */
    private void inject() {
        connectedEquipmentComponent = ((MTSApplication)getApplication())
                .getAppComponent()
                .plusConnectedEquipmentListActivityComponent(ConnectedEquipmentModule.getInstance(this));
        connectedEquipmentComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fillConnectedEquipment();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void fillConnectedEquipmentList() {
        RecyclerView recyclerView = findViewById(R.id.rv_connected_equipment);
        ConnectedEquipmentAdapter adapter = new ConnectedEquipmentAdapter(presenter.getConnectedEquipment(), new ConnectedEquipmentAdapter.OnClickListener() {
            @Override
            public void onClick(ConnectedEquipment connectedEquipment, int position) {
                presenter.onEquipmentClick(connectedEquipment);
            }

            @Override
            public void onLongClick(ConnectedEquipment connectedEquipment, int position) {
                presenter.onEquipmentLongClick(connectedEquipment);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openConnectedEquipmentItem(ConnectedEquipment connectedEquipment) {
        Intent intent = new Intent(this, ConnectedEquipmentItemActivity.class);
        intent.putExtra(ConnectedEquipmentItemActivity.CONNECTED_EQUIPMENT, connectedEquipment);
        startActivityForResult(intent, REQUEST_CODE_ITEM);
    }

    @Override
    public void createConnectedEquipmentItem(ConnectedEquipment connectedEquipment) {

    }

    @Override
    public void openDeleteConnectedEquipmentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirm_delete)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteConnectedEquipment();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}