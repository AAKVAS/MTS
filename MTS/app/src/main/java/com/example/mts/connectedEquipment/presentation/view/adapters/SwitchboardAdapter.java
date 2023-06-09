package com.example.mts.connectedEquipment.presentation.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mts.R;
import com.example.mts.connectedEquipment.domain.entity.Switchboard;

import java.util.List;

/**
 * Адаптер для выпадающего списка распределительных щитков.
 */
public class SwitchboardAdapter extends ArrayAdapter<Switchboard>  {

    /**
     * Конструктор адаптера SwitchboardAdapter.
     * @param context текущий контекст.
     * @param switchboard список распределительных щитков для заполнения.
     */
    public SwitchboardAdapter(Context context, List<Switchboard> switchboard) {
        super(context, 0, switchboard);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Switchboard switchboard = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText("Инв. номер: " + switchboard.getInventoryNumber() + ". Модель: " + switchboard.getModel().getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Switchboard switchboard = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText("Инв. номер: " + switchboard.getInventoryNumber() + ". Модель: " + switchboard.getModel().getName());

        return view;
    }
}
