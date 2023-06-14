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
import com.example.mts.connectedEquipment.domain.entity.Building;

import java.util.List;

public class BuildingAdapter extends ArrayAdapter<Building> {

    public BuildingAdapter(Context context, List<Building> buildings) {
        super(context, 0, buildings);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Building building = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText(building.getAddress());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Building building = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.base_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.tv_spinner_item);
        textView.setText(building.getAddress());

        return view;
    }
}
